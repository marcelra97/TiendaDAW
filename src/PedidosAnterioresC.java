import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class PedidosAnterioresC extends HttpServlet {

    public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
        
    PrintWriter salida = respuesta.getWriter();
	Cookie[] cookies = peticion.getCookies();
    Cookie cookie = cookies[1];
    String nombre = cookie.getValue();
	
	salida.println("<!DOCTYPE html>");  
    salida.println("<html><head>");
    salida.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	salida.println("<title> DETALLES </title></head>");

	salida.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Mate+SC\" />");
	salida.println("<link rel=\"stylesheet\" href=\""+peticion.getContextPath()+"/css/style.css\" type=\"text/css\">");
    salida.println("<body>");
	salida.println("<header>");
    salida.println("<h1> PEDIDOS </h1>");  // Prints "Hello, world!"
	salida.println("<a class=\"enlaceDetalles\" href=\"listado\"> Seguir Comprando </a>");
	salida.println("</header");
	salida.println("<body>");
    salida.println("<h2> Pedidos realizados por " + nombre +"</h2>");
    salida.println("<table>");

    salida.println("<th> Nombre <th> Cantidad <th> Precio Total");
	try{
	   Class.forName("com.mysql.jdbc.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaCuchillos", "tomcat", "tomcat");
        PreparedStatement pst = conn.prepareStatement("Select * from pedidos where nombre like '" + nombre + "';");
	    ResultSet rs = pst.executeQuery();
	    

        while(rs.next()){

            salida.println("<tr>");
			salida.println("<td>");
            salida.println(rs.getString("nombre"));
			salida.println("</td>");
            salida.println("<td>");
            salida.println(rs.getString("cantidad"));
			salida.println("</td>");
            salida.println("<td>");
            salida.println(rs.getString("total"));
			salida.println("</td>");
			salida.println("</tr>");

        }

	    }catch(ClassNotFoundException | SQLException e){
	    salida.println(e.toString());
	    }
    salida.println("</table>");
    salida.println("</body></html>"); 

    }
}