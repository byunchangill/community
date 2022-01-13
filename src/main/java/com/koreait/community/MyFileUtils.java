package com.koreait.community;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class MyFileUtils {

    //폴더 만들기
    public void makeFolders(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // 폴더삭제.
    public void delFolderFiles(String path, boolean isDelFolder) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File[] fileArr = file.listFiles();
            if (fileArr != null) {
                for (File f : fileArr) {
                    if (f.isDirectory()) { // 재귀함수처리.
                        delFolderFiles(f.getPath(), true);
                    } else {
                        f.delete();
                    }
                }
            }
        }
        if (isDelFolder) {
            file.delete();
        }
    }

    // 파일 삭제.
    public void delFile(String path) {
        File f = new File(path);
        if(f.exists()) {
            f.delete();
        }
    }

    //랜덤 파일명 만들기.
    public String getRandomFileNm() {
        return UUID.randomUUID().toString();
    }

    public String getRandomFileNm(String fileNm) {
        return getRandomFileNm() + getExt(fileNm);
    }

    // 확장자 구하기.
    public String getExt(String fileNm) {
        int lastIdx = fileNm.lastIndexOf(".");
        return fileNm.substring(lastIdx);
//        return fileNm.substring(fileNm.lastIndexOf('.'));
    }

    // 파일 저장 > 저장된 랜덤 파일명 리턴.
    public String  saveFile(String path, MultipartFile mf) {
        makeFolders(path);
        String randomFileNm = getRandomFileNm(mf.getOriginalFilename());
        File targetFile = new File(path, randomFileNm);
        try {
            mf.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return randomFileNm;
    }
}


