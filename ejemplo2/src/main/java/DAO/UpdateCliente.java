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
import java.sql.ResultSet;
import java.sql.SQLException;

import Controlador.Conexion;

import DAO.Notificaicones;

@WebServlet("/UpdateCliente")
public class UpdateCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateCliente() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		
		//Buscar cliente

		if ("buscarRegistro".equals(action)) {
			// Buscar cliente por cédula y reenviar al JSP
			String cedula = request.getParameter("cedula");
			
			// Busca el  cliente en la base de datos por cédula

			try (Connection dbConnection = Conexion.getConnection()) {
				String sql = "SELECT * FROM tblclientes WHERE cedula=?";
				PreparedStatement ps = dbConnection.prepareStatement(sql);
				ps.setString(1, cedula);
				ResultSet rs = ps.executeQuery();

				// Si encuentra el registro, carga los datos en atributos
				if (rs.next()) {
					request.setAttribute("cedula", rs.getString("cedula"));
					request.setAttribute("nombres", rs.getString("nombres"));
					request.setAttribute("apellidos", rs.getString("apellidos"));
					request.setAttribute("direccion", rs.getString("direccion"));
					request.setAttribute("telefono", rs.getString("telefono"));
				}

				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}


			// Redirige al formulario JSP con los datos del cliente
			request.getRequestDispatcher("UpdateCliente.jsp").forward(request, response);

			//Actualizar cliente 
		} else if ("actualizarRegistro".equals(action)) {
			// Procesar actualización
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			// Lee los datos modificados desde el formulario
			String cedula = request.getParameter("cedula");
			String nombres = request.getParameter("nombres");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");

			// Validación básica: la cédula no puede estar vacía
			if (cedula == null || cedula.isEmpty()) {
				out.println("Cédula no proporcionada.");
				return;
			}

			// Actualiza los datos en la base de datos
			try (Connection conn = Conexion.getConnection()) {
				String sql = "UPDATE tblclientes SET nombres=?, apellidos=?, direccion=?, telefono=? WHERE cedula=?";
				PreparedStatement ps = conn.prepareStatement(sql);

				ps.setString(1, nombres);
				ps.setString(2, apellidos);
				ps.setString(3, direccion);
				ps.setString(4, telefono);
				ps.setString(5, cedula);

				int filas = ps.executeUpdate();
				
				// Genera una notificación usando la clase Notificaicones
				Notificaicones noti = new Notificaicones();

				noti.SIM("Actualizacion de registro ", "se actualizo el registro del usuario con cedula: " + cedula);

				// Si hubo cambios, redirige a la página principal
				if (filas > 0) {
					response.sendRedirect("index.jsp"); // Redirige a lista principal
				} else {
					out.println("No se encontró ningún cliente con esa cédula.");
				}

				ps.close();
			} catch (Exception e) {
				// Manejo de errores de SQL o conexión
				e.printStackTrace();
				out.println("Error al actualizar cliente: " + e.getMessage());
			}
		}
	}
}
