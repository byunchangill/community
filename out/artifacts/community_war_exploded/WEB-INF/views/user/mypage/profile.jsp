<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>

<div id="data" data-iuser="${sessionScope.loginUser.iuser}"></div>
<div class="flex-container flex-direction-column flex-align-center">
    <my:profileImg idVal="profile-view" classVal="pointer circular--img wh-300" iuser="${sessionScope.loginUser.iuser}" profileImgVal="${sessionScope.loginUser.profileImg}"/>
    <input type="file" id="profile-file" class="hidden" accept="image/*">
    <div>아이디: ${sessionScope.loginUser.uid}</div>
    <div>이름: ${sessionScope.loginUser.nm}</div>
    <div>성별: ${sessionScope.loginUser.gender == 1 ? '남성' : '여성'}</div>
</div>