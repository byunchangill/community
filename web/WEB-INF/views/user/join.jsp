<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1>회원가입</h1>
<div>${requestScope.msg}</div>
<form action="/user/join" method="post" id="join-frm">
    <div><label>아이디 : <input type="text" name="uid" required></label></div>
    <span id="id-Chk-msg"></span>
    <div><input type="button" value="아이디 중복 체크" id="id-btn-chk"></div>
    <div><label>비밀번호 : <input type="password" name="upw" required></label></div>
    <div><label>비밀번호 확인 : <input type="password" id="upw-chk" required></label></div>
    <div><label>이름 : <input type="text" name="nm" required></label></div>
    <div>
        <label>여성 <input type="radio" name="gender" value="2"></label>
        <label>남성 <input type="radio" name="gender" value="1" checked></label>
    </div>
    <div>
        <input type="submit" value="회원가입">
        <input type="reset" value="초기화">
    </div>
</form>