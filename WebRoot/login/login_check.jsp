<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="avicit.platform6.api.sysuser.dto.SysUser"%>
<%@page import="avicit.platform6.api.sysuser.SysUserAPI"%>

<%
	String path = request.getContextPath();

    Logger logger = Logger.getLogger(this.getClass());

	String username = request.getHeader("iv-user");
	
	username = request.getParameter("user");
	
	
// 	SysUserAPI sysUserAPI = SpringFactory.getBean("sysUserAPI");
// 	SysUser sysUser = sysUserAPI.getSysUserByLoginName(username);
	
	String loginJsp ="/login/login.jsp";

	if(null==username){
		out.print("<h2><font color=#ff0000>对不起，用户名&nbsp;"+username+" 不存在.</font><br></h2>");
		return;
	}
	if (username != null) {
		String loginAction = path+ "/avicit/platform6/modules/system/sysdashboard/index.jsp?username_="+username;
		response.sendRedirect(response.encodeRedirectURL(loginAction));
	} else {
		request.getRequestDispatcher(loginJsp).forward(request, response);
		
	}
%>
