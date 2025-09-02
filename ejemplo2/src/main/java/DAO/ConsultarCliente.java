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

import Controlador.Conexion;

/**
 * Servlet implementation class ConsultarCliente
 */
@WebServlet("/ConsultarCliente")
public class ConsultarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultarCliente() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection con = Conexion.getConnection()) {

            String sql = "SELECT cedula, nombres, apellidos, direccion, telefono FROM clientes WHERE cedula = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // HTML
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Lista de Clientes</title></head><body>");
            out.println("<h1> Lista de Clientes</h1>");
            out.println("<table border='1' cellpadding='5'>");
            out.println("<tr><th>Cédula</th><th>Nombres</th><th>Apellidos</th><th>Dirección</th><th>Teléfono</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("cedula") + "</td>");
                out.println("<td>" + rs.getString("nombres") + "</td>");
                out.println("<td>" + rs.getString("apellidos") + "</td>");
                out.println("<td>" + rs.getString("direccion") + "</td>");
                out.println("<td>" + rs.getString("telefono") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println(" Error al consultar clientes: " + e.getMessage());
        }
    }
}
