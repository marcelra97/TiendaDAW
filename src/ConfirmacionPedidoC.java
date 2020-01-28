import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class ConfirmacionPedidoC extends HttpServlet {

    public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
        
	HttpSession misesion;
    PrintWriter salida = respuesta.getWriter();
	String nombre = peticion.getParameter("nombre");
	
	Cookie cookie = new Cookie("cliente","");
	cookie.setValue(nombre);
	respuesta.addCookie(cookie);

	misesion=peticion.getSession(true);
	Integer productos ;
	Integer total ;

	productos = (Integer) misesion.getAttribute("productos");
	total = (Integer) misesion.getAttribute("total");

	salida.println("<!DOCTYPE html>");  // HTML 5
    salida.println("<html><head>");
    salida.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	salida.println("<title> DETALLES </title></head>");

	salida.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Mate+SC\" />");
	salida.println("<link rel=\"stylesheet\" href=\""+peticion.getContextPath()+"/css/style.css\" type=\"text/css\">");
    salida.println("<body>");
	salida.println("<header>");
    salida.println("<h1> GRACIAS POR SU COMPRA </h1>");  
	salida.println("<a class=\"enlaceDetalles\" href=\"listado\"> Seguir Comprando </a>");
	salida.println("</header");
	salida.println("</body></html>"); 


	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaCuchillos", "tomcat", "tomcat");
	    Statement update;

		update = conn.createStatement();
		update.executeUpdate("INSERT INTO pedidos VALUES (null,'" +nombre + "'," + productos + "," + total + ");");

	    }catch(ClassNotFoundException | SQLException e){
	    salida.println(e.toString());
	    }

    }
}
