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

@WebServlet("/UpdateCliente")
public class UpdateCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateCliente() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Obtener parámetros del formulario
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
            // Consulta SQL: NO actualizamos la cédula, solo usamos en WHERE
            String sql = "UPDATE tblclientes SET nombres=?, apellidos=?, direccion=?, telefono=? WHERE cedula=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, direccion);
            ps.setString(4, telefono);
            ps.setString(5, cedula);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                response.sendRedirect("index.jsp"); // Redirige a la lista de clientes
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
