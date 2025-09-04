package Reportes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import Controlador.Conexion;

// Librerías de iText
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/ReportePDF")
public class ReportePDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReportePDF() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //  prepara la descarga del documento PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_clientes.pdf");

        //conecta a la base de datos
        try (Connection connection = Conexion.getConnection()) {

            if (connection == null) {
                throw new ServletException("No se pudo establecer la conexión con la base de datos.");
            }

            //Crear el documento PDF
            Document document = new Document(); //lo conecta al OutputStream de la respuesta y todo lo que se agregue al
            PdfWriter.getInstance(document, response.getOutputStream()); //se va enviando al navegador.
            document.open(); //inicia la escritura.

           
            document.add(new Paragraph(" Lista de Clientes")); 
            document.add(new Paragraph("Fecha de generación: " + new java.util.Date()));
            document.add(new Paragraph(" "));

            //  Consulta a la BD
            String sql = "SELECT cedula, nombres, apellidos, direccion, telefono FROM tblclientes";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                // Crear tabla con 5 columnas
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);

                // Encabezados de la tabla
                table.addCell("Cedula");
                table.addCell("Nombres");
                table.addCell("Apellidos");
                table.addCell("Dirección");
                table.addCell("Telefono");

                // Llena la tabla con  los datos de la bd
                while (rs.next()) {
                    table.addCell(String.valueOf(rs.getInt("cedula")));
                    table.addCell(rs.getString("nombres"));
                    table.addCell(rs.getString("apellidos"));
                    table.addCell(rs.getString("direccion"));
                    table.addCell(rs.getString("telefono"));
                }

                // Agregar tabla al documento
                //La tabla completa se coloca en el documento.
                document.add(table);
            }

            document.close();

        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            throw new ServletException("Error al generar el PDF", e);
        }
    }
}

