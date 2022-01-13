package com.koreait.community;

public class MyCustomTag {
    public static String profileImg(String idval, String classVal, String iuser, String profileImgVal) {
        String fixProfileImgVal = "/res/img/defailtProfile.png";
        if(profileImgVal == null) {
            fixProfileImgVal = String.format("/images/user/%s/%s", iuser, profileImgVal);
        }
        return String.format("<div id=\"%s\"><img class=\"%s\"src=\"%s\"><div>", idval, classVal, profileImgVal);
    }
}
