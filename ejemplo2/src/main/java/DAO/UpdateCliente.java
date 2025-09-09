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

import 	DAO.Notificaicones;

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

		if ("buscarRegistro".equals(action)) {
			// Buscar cliente por cédula y reenviar al JSP
			String cedula = request.getParameter("cedula");

			try (Connection dbConnection = Conexion.getConnection()) {
				String sql = "SELECT * FROM tblclientes WHERE cedula=?";
				PreparedStatement ps = dbConnection.prepareStatement(sql);
				ps.setString(1, cedula);
				ResultSet rs = ps.executeQuery();

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

			// Cargar el formulario de actualización
			request.getRequestDispatcher("UpdateCliente.jsp").forward(request, response);

		} else if ("actualizarRegistro".equals(action)) {
			// Procesar actualización
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			String cedula = request.getParameter("cedula");
			String nombres = request.getParameter("nombres");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");

			if (cedula == null || cedula.isEmpty()) {
				out.println("Cédula no proporcionada.");
				return;
			}

			try (Connection conn = Conexion.getConnection()) {
				String sql = "UPDATE tblclientes SET nombres=?, apellidos=?, direccion=?, telefono=? WHERE cedula=?";
				PreparedStatement ps = conn.prepareStatement(sql);

				ps.setString(1, nombres);
				ps.setString(2, apellidos);
				ps.setString(3, direccion);
				ps.setString(4, telefono);
				ps.setString(5, cedula);

				int filas = ps.executeUpdate();

			
				Notificaicones noti = new Notificaicones();
				
				noti.SIM("Actualizacion de registro ", "se actualizo el registro del usuario con cedula: " + cedula );
				


				if (filas > 0) {
					response.sendRedirect("index.jsp"); // Redirige a lista principal
				} else {
					out.println("No se encontró ningún cliente con esa cédula.");
				}

				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				out.println("Error al actualizar cliente: " + e.getMessage());
			}
		}
	}
}
