<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1>로그인</h1>
<div>${requestScope.msg}</div>
<form action="/user/login" method="post">
    <div>아이디<input type="text" name="uid" placeholder="아이디" value="${requestScope.tryLogin.uid}"></div>
    <div>비밀번호<input type="password" name="upw" placeholder="비밀번호" value="${requestScope.tryLogin.upw}"></div>
    <div><input type="submit" value="로그인"></div>
</form>
<div>
    <a href="/user/join"><input type="button" value="회원가입"></a>
</div>
