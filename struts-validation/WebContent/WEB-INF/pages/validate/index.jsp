<%@ page contentType="text/html;charset=UTF-8" language="Java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<body>
		登录
		<s:form action="login" validate="true" method="post" namespace="/">
			<s:textfield name="a" id="a" label="a"></s:textfield>
			<s:textfield name="b" id="b" label="b"></s:textfield>
			<s:submit value="提交"></s:submit>
		</s:form>
		<s:actionerror />
	</body>
</html>