<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:importAttribute name="subMenuList"/>
<c:set var="currentPagePath" value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="splitURI" value="${fn:split(currentPagePath, '/')}"/>
<c:set var="lastPath" value="${splitURI[fn:length(splitURI) - 1]}"/>
<div class="submenu-section">
    <div class="p-ud-30">
        <ul>
            <c:forEach items="${subMenuList}" var="item">
                <c:set var="className" value="${item.href == lastPath ? 'submenu-selected' : ''}" />
                <li class="p-ud5-lr10 ${className}"><a href="/user/mypage/${item.href}">${item.nm}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="p-10">
        <tiles:insertAttribute name="content" />
    </div>
</div>