<%@page session="true" language="java" import="java.util.*" %>
<%@page import="sv.edu.udb.controlador.checkLoginClass"%>
<%
    String correo = request.getParameter("correo");
    String clave = request.getParameter("clave");
    
    int val = checkLoginClass.verifyUser(correo, clave);
    
    if (val == 1) {      
        //Se asignan parametros de sesión
        HttpSession sesionOk = request.getSession();
        sesionOk.setAttribute("usuario", correo);
%>
<jsp:forward page="menu.jsp" />
<%
} else {
%>
<jsp:forward page="Login.jsp">
    <jsp:param name="error" value="Usuario y/o clave Incorrecto. Vuelve a intentarlo" />
</jsp:forward>
<%
    }  
%>
