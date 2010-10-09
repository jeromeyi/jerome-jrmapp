<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%="This is Biz Exception Page." %>
<s:if test="hasActionErrors()">
<div class="show_info">
<span>
<s:iterator value="actionErrors"><s:property/></s:iterator>
</span>
</div>
</s:if>
</body>
</html>