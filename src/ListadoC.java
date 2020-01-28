import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


public class ListadoC extends HttpServlet {

    public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
        
        HttpSession misesion;
        PrintWriter salida = respuesta.getWriter();
		int idCerveza;
		ArrayList<Integer> lista = new ArrayList<Integer>();
	
	misesion = peticion.getSession(true);

	if(misesion.isNew()==true){
		lista = new ArrayList<Integer>();
		misesion.setAttribute("idproductos",lista);		
		
	}else{

			try{

				idCerveza = Integer.parseInt(peticion.getParameter("producto"));
				lista =(ArrayList<Integer>) misesion.getAttribute("idproductos");
			lista.add(idCerveza);
			misesion.setAttribute("idproductos",lista);

			}catch(NumberFormatException e){


			}
			
			


	}

	salida.println("<!DOCTYPE html>");  // HTML 5
    salida.println("<html><head>");
    salida.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");

	salida.println("<title> LISTADO </title></head>");

	salida.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Mate+SC\" />");
	salida.println("<link rel=\"stylesheet\" href=\""+peticion.getContextPath()+"/css/style.css\" type=\"text/css\">");
	
        salida.println("<body>");

	salida.println("<header>");
        salida.println("<h1> TIENDA DE CUCHILLO </h1>");  // Prints "Hello, world!"
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
	    
	    while(rs.next()){
		salida.println("<tr>");

		salida.println("<td>");
		salida.println("<img class=\"figuras\" src=\""+peticion.getContextPath()+"/imgs/"+rs.getString(4)+"\">");
		salida.println("</td>");
			       
		
		salida.println("<td>");
		salida.println(rs.getString(2));
		salida.println("</td>");

		salida.println("<td>");
		salida.println(rs.getString(3));
		salida.println("</td>");

		salida.println("<td>");
		salida.println("<form action=http://localhost:8080/gestion/listado method=get>");
		salida.println("<input class=\"carrito\" type=\"submit\" name=\"producto\" value=\"" + rs.getString(1)+"\"</input>");
		salida.println("</form>");
		salida.println("</td>");
		salida.println("</tr>");

	    }

		salida.println("<a class=\"enlaceDetalles\" href=\"detalles\"> Elementos en la Cesta ("+lista.size()+") </a>");
	    
	    }catch(ClassNotFoundException | SQLException e){
	    salida.println(e.toString());
	    }

	salida.println("</table>");
	salida.println("<a class=\"enlaceDetalles\" href=\"pedidosAnteriores\"> Visite sus anteriores pedidos </a>");
	salida.println("</main>");

	salida.println("<div class=\"debug\"");
    salida.println(misesion.toString());
	salida.println("</div>");

	// salida.println("<script src=\""+peticion.getContextPath()+"/js/gestion.js\"></script>");
	salida.println("</body></html>");     

    }
}
