import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class GestionVendedorC extends HttpServlet {

    public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
        
    PrintWriter salida = respuesta.getWriter();
	

	salida.println("<!DOCTYPE html>");  // HTML 5
    salida.println("<html><head>");
    salida.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	salida.println("<title> DETALLES </title></head>");

	salida.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Mate+SC\" />");
	salida.println("<link rel=\"stylesheet\" href=\""+peticion.getContextPath()+"/css/estilo.css\" type=\"text/css\">");
    salida.println("<body>");
	salida.println("<header>");
    salida.println("<h1> GESTION VENDEDOR </h1>");  
	salida.println("</header");
	salida.println("<body>"); 


    salida.println("<table>");

	try{

	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaCuchillos", "tomcat", "tomcat");
        PreparedStatement pst = conn.prepareStatement("Select * from pedidos;");
	    ResultSet rs = pst.executeQuery();

        salida.println("<th>NOMBRE<th>CANTIDAD<th>TOTAL");

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
