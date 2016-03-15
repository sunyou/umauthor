<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="<%=com.ai.common.util.WebUtils.getBasePath(request)%>"/>
<c:set var="resourcePath" value="${pageContext.request.contextPath}/static/resources" />
<c:set var="businessPath" value="${pageContext.request.contextPath}/static/business" />
<c:set var="selfurl" value="${pageContext.request.requestURL}"/>

