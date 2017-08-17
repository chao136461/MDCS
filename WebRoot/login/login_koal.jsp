<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%


	String path = request.getContextPath();

    Logger logger = Logger.getLogger(this.getClass());

	String username = request.getHeader("iv-user");
	
	
	String loginJsp ="/login/login.jsp";
	
	if (username != null) {
		
		String loginAction = path+ "/platform/user/caslogin?username_="+username;
		
		response.sendRedirect(response.encodeRedirectURL(loginAction));
		
	} else {
		
		request.getRequestDispatcher(loginJsp).forward(request, response);
		
	}
%>
