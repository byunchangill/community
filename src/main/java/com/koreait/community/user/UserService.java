package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.MyFileUtils;
import com.koreait.community.UserUtils;
import com.koreait.community.model.UserDTO;
import com.koreait.community.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserService {
    @Autowired
    public UserMapper mapper;
    @Autowired
    public UserUtils userUtils;
    @Autowired
    private MyFileUtils myFileUtils;

    public int join(UserEntity entity) {
        UserEntity copyEntity = new UserEntity();
        BeanUtils.copyProperties(entity, copyEntity);

        // 비밀번호 암호화.
        String hashUpw = BCrypt.hashpw(copyEntity.getUpw(), BCrypt.gensalt());
        copyEntity.setUpw(hashUpw);
        return mapper.join(copyEntity);
    }

    public int login(UserEntity entity) {
        UserEntity dbUser = null;
        try {
            dbUser = mapper.selUser(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 알 수 없는 에러.
        }
        if(dbUser == null) {
            return 2; // 아이디 없음.
        } else if(!BCrypt.checkpw(entity.getUpw(), dbUser.getUpw())) { // 비밀번호 틀림.
            return 3;
        }
        dbUser.setUpw(null);
        userUtils.setLoginUser(dbUser);
        return 1; // 성공
    }
    public int idChk(String uid) {
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        UserEntity result = mapper.selUser(entity);
        if (result == null) { // 아이디가 없으면 리턴 1, 아이디가 있으면 리턴 0.
            return 1;
        }
        return 0;
    }

    // 이미지 업로드 처리 및 저장 파일명 리턴.
    public String uploadProfileImg(MultipartFile mf) {
        if(mf == null) {
            return null;
        }

        UserEntity loginUser = userUtils.getLoginUser();

        final String PATH = Const.UPLOAD_IMG_PATH + "/user/" + loginUser.getIuser();
        String fileNm = myFileUtils.saveFile(PATH, mf);
        System.out.println("fileNm : " +fileNm);
        if(fileNm == null) { return null; }

        UserEntity entity = new UserEntity();
        entity.setIuser(loginUser.getIuser());

        // 기존 파일명.
        String oldFilePath = PATH + "/" + loginUser.getProfileImg();
        myFileUtils.delFile(oldFilePath);

        // 파일명을 t_user 테이블에  update.
        entity.setProfileImg(fileNm);
        mapper.updUser(entity);

        // 세션 프로필 파일명을 수정해 준다.
        loginUser.setProfileImg(fileNm);

        return fileNm;
    }

    public int changePassword(UserDTO dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        UserEntity dbUser = mapper.selUser(dto);
        if(BCrypt.checkpw(dto.getCurrentUpw(), dbUser.getUpw())) {
            return 2; // 현재 비밀번호 다름.
        }
        String hashPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashPw);
        return mapper.updUser(dto);
    }
}
