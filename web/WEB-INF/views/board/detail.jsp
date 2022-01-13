<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.loginUser.iuser == data.iuser}">
    <div>
        <button id="btnMod">수정</button>
        <button id="btnDel">삭제</button>
    </div>
</c:if>
<div id="data" data-icategory="${data.icategory}" data-iboard="${data.iboard}"></div>
<div>
    <div>카테고리 :${data.categoryNm}</div>
    <div>조회수: ${data.hits}</div>
    <div>등록일시: ${data.rdt}</div>
    <div>글쓴이: ${data.writerNm}</div>
    <div>제목: <c:out value="${data.title}" /></div>
    <div><c:out value="${data.ctnt}" /></div>
</div>