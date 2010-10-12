<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>系统错误，请稍候...</title>
<style type="text/css">
<!--
body {
	background-color: #AFAFAF;
}
.STYLE2 {font-size: 14px}
.STYLE3 {color: #FF0000}
-->
</style></head>

<body>
<table border="0" align="center" cellpadding="0" cellspacing="0"">
  <tr>
    <td width="15" height="294" background="images/wrong/wrong_bg.gif"></td>
    <td width="500" align="center" background="images/wrong/wrong_bg.gif"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="200" align="center"><img src="images/wrong/wrong_cp.gif" alt="系统错误，请稍候..." width="457" height="123" /></td>
        </tr>
        <tr>
          <td height="40" align="center"></td>
        </tr>
      </table>
    </td>
    <td width="18" height="294" background="images/wrong/wrong_right.gif"></td>
  </tr>
</table>
</body>
</html>
<!-- 
<%exception.printStackTrace(new PrintWriter(out)); %>
 -->