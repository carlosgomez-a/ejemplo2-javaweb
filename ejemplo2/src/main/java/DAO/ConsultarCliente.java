package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Controlador.Conexion;

@WebServlet("/ConsultarCliente")
public class ConsultarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultarCliente() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cedula = request.getParameter("cedula");

        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM tblclientes WHERE cedula=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("cedula", rs.getString("cedula"));
                request.setAttribute("nombres", rs.getString("nombres"));
                request.setAttribute("apellidos", rs.getString("apellidos"));
                request.setAttribute("direccion", rs.getString("direccion"));
                request.setAttribute("telefono", rs.getString("telefono"));
            } else {
                request.setAttribute("mensaje", "No se encontró un cliente con la cédula: " + cedula);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al consultar cliente: " + e.getMessage());
        }

        // Ir a la vista de consulta
        request.getRequestDispatcher("ConsultarCliente.jsp").forward(request, response);
    }
}
