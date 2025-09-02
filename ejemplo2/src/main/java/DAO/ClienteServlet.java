package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Controlador.Conexion;

/**
 * Servlet implementation class RegistrarCliente
 */
@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<tittle> Hola Servlet </tittle> ");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> Servle </h1>");
		out.println("</body>");
		out.println("</html>");

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cedula = request.getParameter("cedula");
		String nombres = request.getParameter("nombres");
		String apellidos = request.getParameter("apellidos");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");

		try (Connection con = Conexion.getConnection()) {

			String sql = "INSERT INTO tblclientes (cedula, nombres, apellidos, direccion, telefono) VALUES (?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, cedula);
			ps.setString(2, nombres);
			ps.setString(3, apellidos);
			ps.setString(4, direccion);
			ps.setString(5, telefono);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Cliente Registrado");

		}

		doGet(request, response);


	}

}
