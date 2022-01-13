package com.koreait.community;

import com.koreait.community.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UserUtils {
    @Autowired
    private HttpSession hs;

    public void setLoginUser(UserEntity entity) { // 세션에 로그인 정보 담기.
        hs.setAttribute(Const.LOGIN_USER, entity);
    }

    public UserEntity getLoginUser() { // 담겨있는 정보 가져오기.
        return (UserEntity) hs.getAttribute(Const.LOGIN_USER);
    }

    public int getLoginUserPk() {
        return getLoginUser() == null ? 0 : getLoginUser().getIuser();
    }

}
