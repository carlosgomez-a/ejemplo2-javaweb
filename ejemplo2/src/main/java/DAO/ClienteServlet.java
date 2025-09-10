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
import DAO.Notificaicones;

/**
 * Servlet implementation class RegistrarCliente
 */
/**
 * Servlet ClienteServlet
 * Se encarga de registrar clientes en la base de datos y notificar vía correo.
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
	 *//**
	 * Método GET de prueba: retorna una página HTML simple.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<tittle> Registro completado </tittle> ");
		out.println("</head>");
		out.println("<body>");
		//out.println("<h1> Servle </h1>");
		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	/**
	 * Método POST: recibe datos del formulario, los inserta en la BD
	 * y genera una notificación al registrar un cliente.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Captura de parámetros enviados desde el formulario
		String cedula = request.getParameter("cedula");
		String nombres = request.getParameter("nombres");
		String apellidos = request.getParameter("apellidos");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");

		// 2. Conexión a la BD e  inserción de datos
		try (Connection con = Conexion.getConnection()) {

			String sql = "INSERT INTO tblclientes (cedula, nombres, apellidos, direccion, telefono) VALUES (?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			// asignamos los valores
			ps.setString(1, cedula);
			ps.setString(2, nombres);
			ps.setString(3, apellidos);
			ps.setString(4, direccion);
			ps.setString(5, telefono);
			ps.executeUpdate();
			
			// 3. Envío de notificación al registrar cliente
			Notificaicones not = new Notificaicones();

			not.SIM("Registro de cliente ", "se registró un nuevo usuario con cedula : " + cedula);

		} catch (Exception e) {
			// 4. Manejo de errores
			e.printStackTrace();
			response.getWriter().println("Cliente Registrado");

		}
		// 5. Llamada a doGet para mostrar respuesta básica en HTML
		doGet(request, response);

	}

}
