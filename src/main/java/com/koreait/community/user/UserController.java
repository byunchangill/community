package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.model.UserDTO;
import com.koreait.community.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService service;

    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public String loginProc(UserEntity entity, RedirectAttributes reAttr) {
        int result = service.login(entity);
        reAttr.addFlashAttribute(Const.TRY_LOGIN, entity);
        switch (result) {
            case 0:
                reAttr.addFlashAttribute(Const.MSG, Const.ERR_1);
                break;
            case 1:
                return "redirect:/board/list/1";
            case 2:
                reAttr.addFlashAttribute(Const.MSG, Const.ERR_2);
                break;
            case 3:
                reAttr.addFlashAttribute(Const.MSG, Const.ERR_3);
                break;
        }
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession hs) {
        hs.invalidate();
        return "/user/login";
    }

    @GetMapping("/join")
    public void join() {}

    @PostMapping("/join")
    public String joinProc(UserEntity entity, RedirectAttributes reAttr) {
        int result = service.join(entity);
        if(result == 0) {
            reAttr.addFlashAttribute(Const.MSG, Const.ERR_4);
            return "redirect:/user/login";
        }
        service.login(entity);
        return "redirect:/board/list";
    }

    @GetMapping("/idChk/{uid}")
    @ResponseBody
    public Map<String, Integer> idChk(@PathVariable String uid) {
        Map<String, Integer> res = new HashMap();
        res.put("result", service.idChk(uid));
        return res;
    }

    @GetMapping("/mypage/profile")
    public void myPageProfile() {}

    @ResponseBody
    @PostMapping("/mypage/profile")
    public Map<String, String> myPageProfileProc(MultipartFile profileImg) {
        String fileNm = service.uploadProfileImg(profileImg);
        Map<String, String> result = new HashMap<>();
        result.put("result", fileNm);
        return result;
    }

    @GetMapping("/mypage/password")
    public void password() {}

    // 비밀번호 변경 및 확인
    @PostMapping("/mypage/password")
    public String passwordProc(UserDTO dto, HttpSession session, RedirectAttributes rAttr) {
        int result = service.changePassword(dto);
    if(result != 1) {
        switch (result) {
            case 0:
                rAttr.addFlashAttribute(Const.MSG, "비밀번호 변경에 실패 하였습니다.");
                break;
            case 2:
                rAttr.addFlashAttribute(Const.MSG, "현재 비밀번호를 확인해 주세요.");
                break;
        }
        return "redirect:/user/mypage/password";
    }
    return "redirect:/user/login";
    }
}
