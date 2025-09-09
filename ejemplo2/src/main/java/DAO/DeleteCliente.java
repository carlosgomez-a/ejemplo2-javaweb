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

@WebServlet("/DeleteCliente")
public class DeleteCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 {

		String cedulaParam = request.getParameter("cedula");

		if (cedulaParam == null || cedulaParam.isEmpty()) {
			response.getWriter().println("Código de cliente no proporcionado.");
			return;
		}

		try {
			int cedula = Integer.parseInt(cedulaParam);

			try (Connection conn = Conexion.getConnection();
					PreparedStatement stmt = conn.prepareStatement("DELETE FROM tblclientes WHERE cedula = ?")) {

				stmt.setInt(1, cedula);
				int filas = stmt.executeUpdate();

				if (filas > 0) {
					response.sendRedirect("index.jsp");
				} else {
					response.getWriter().println("No se encontró ningún cliente con ese código.");
				}
			}
			
			Notificaicones envio = new Notificaicones();
			
			envio.SIM("Eliminación de registro ", "se eliminó el registro del usuario con cedula: " + cedula );
		

		} catch (NumberFormatException e) {
			response.getWriter().println("Formato de cédula inválido.");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error al eliminar el cliente.");
		}
	}
}

}
