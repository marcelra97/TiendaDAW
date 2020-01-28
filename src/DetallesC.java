import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class DetallesC extends HttpServlet {
    int id1;
    int id2 ;
    int id3 ;
	int total;
	int incremento;

    public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
        
    HttpSession misesion;
    PrintWriter salida = respuesta.getWriter();
	int idCerveza;
	ArrayList<Integer> lista;

	id1 = 0;
    id2 = 0;
	id3 = 0;
	total = 0;
	incremento = 0;

	misesion = peticion.getSession(true);
	lista =(ArrayList<Integer>) misesion.getAttribute("idproductos");
	

	salida.println("<!DOCTYPE html>");  // HTML 5
    salida.println("<html><head>");
    salida.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	salida.println("<title> DETALLES </title></head>");

	salida.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Mate+SC\" />");
	salida.println("<link rel=\"stylesheet\" href=\""+peticion.getContextPath()+"/css/style.css\" type=\"text/css\">");
    salida.println("<body>");
	salida.println("<header>");
    salida.println("<h1> CARRITO </h1>");  // Prints "Hello, world!"
	salida.println("<a class=\"enlaceDetalles\" href=\"listado\"> Seguir Comprando </a>");
	salida.println("</header");
	salida.println("<main>");

	//salida.println("<h1> Figuras del Tablero </h1>");
	salida.println("<table>");

	// Construccion de la tabla
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendaCuchillos", "tomcat", "tomcat");
        PreparedStatement pst = conn.prepareStatement("Select * from producto;");
	    ResultSet rs = pst.executeQuery();


		for(Integer id : (ArrayList<Integer>) misesion.getAttribute("idproductos")){

			switch(id){

				case(1): id1++;break;
				case(2): id2++;break;
				case(3): id3++;break;	
			}
		}

		total = (id1 * 3) + (id2 * 4) + (id3 * 2);
		incremento = id1 + id2 + id3;
		
			salida.println("<tr>");
			salida.println("<td>");
			salida.println("<img class=\"figuras\" src=\""+peticion.getContextPath()+"/imgs/cuchillo.png\">");
			salida.println("</td>");
			salida.println("<td>");
			salida.println("<h2> x " +id1 +"</h2>");
			salida.println("</td>");
			salida.println("</tr>");

			salida.println("<tr>");
			salida.println("<td>");
			salida.println("<img class=\"figuras\" src=\""+peticion.getContextPath()+"/imgs/cuchillo2.png\">");
			salida.println("</td>");
			salida.println("<td>");
			salida.println("<h2> x " +id2 +"</h2>");
			salida.println("</td>");
			salida.println("</tr>");

			salida.println("<tr>");
			salida.println("<td>");
			salida.println("<img class=\"figuras\" src=\""+peticion.getContextPath()+"/imgs/cuchillo3.png\">");
			salida.println("</td>");
			salida.println("<td>");
			salida.println("<h2> x " +id3 +"</h2>");
			salida.println("</td>");
			salida.println("</tr>");

			salida.println("<tr>");
			salida.println("<td>");
			salida.println("<h2>SUBTOTAL: "+ total + "&dollar;" + "</h2>");
			salida.println("<h2>ENVIO: 2&dollar;" + "</h2>");
			salida.println("<h2>INCREMENTO POR PEDIDO: " +incremento +"2&dollar;" + "</h2>");
			total = total + incremento;
			salida.println("<h2>TOTAL: "+ total + "&dollar;" + "</h2>");
			salida.println("</td>");
			salida.println("</tr>");

			misesion.setAttribute("total", total);
			misesion.setAttribute("productos", incremento);
	    }catch(ClassNotFoundException | SQLException e){
	    salida.println(e.toString());
	    }

	salida.println("</table>");
	salida.println("<a class=\"enlaceDetalles\" href=\"datos\"> Enviar pedido </a>");
	salida.println("</main>");
	salida.println("<div class=\"debug\"");
    salida.println(misesion.toString());
	salida.println("</div>");
	salida.println("<script src=\""+peticion.getContextPath()+"/js/gestion.js\"></script>");
	salida.println("</body></html>"); 

    }
}
