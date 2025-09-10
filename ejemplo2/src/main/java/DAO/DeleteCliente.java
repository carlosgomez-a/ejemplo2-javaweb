package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import DAO.Notificaicones;

import Controlador.Conexion;
/**
 * Servlet encargado de eliminar un cliente de la base de datos.
 * Se ejecuta a través de solicitudes POST enviadas desde un formulario.
@WebServlet("/DeleteCliente")*/
public class DeleteCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			// Obtener parámetro desde el formulario
			String cedulaParam = request.getParameter("cedula");
			//Validar que la cédula haya sido enviada
			if (cedulaParam == null || cedulaParam.isEmpty()) {
				response.getWriter().println("Código de cliente no proporcionado.");
				return;
			}

			try {
				// Convertir cédula a número entero
				int cedula = Integer.parseInt(cedulaParam);

				// Conexión a la BD y eliminación del cliente
				try (Connection conn = Conexion.getConnection();
						PreparedStatement stmt = conn.prepareStatement("DELETE FROM tblclientes WHERE cedula = ?")) {

					stmt.setInt(1, cedula);
					int filas = stmt.executeUpdate();

					if (filas > 0) {
						// Si se eliminó, redirige a la página principal
						response.sendRedirect("index.jsp");
					} else {
						// Si no encontró registros
						response.getWriter().println("No se encontró ningún cliente con ese código.");
					}
				}
				// Enviar notificación por correo
				Notificaicones envio = new Notificaicones();

				envio.SIM("Eliminación de registro ", "se eliminó el registro del usuario con cedula: " + cedula);

			} catch (NumberFormatException e) {
				// Error si la cédula no es un número
				response.getWriter().println("Formato de cédula inválido.");
			} catch (Exception e) {
				// Otros errores (SQL o generales)
				e.printStackTrace();
				response.getWriter().println("Error al eliminar el cliente.");
			}
		}
	}

}
