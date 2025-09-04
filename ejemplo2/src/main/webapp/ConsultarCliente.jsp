<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Consultar Cliente</title>

<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container py-5">
		<h1 class="mb-4 text-center">Consultar Cliente</h1>

		<!-- Formulario de Consulta -->
		<form action="ConsultarCliente" method="get"
			class="p-4 bg-white rounded shadow-sm">

			<div class="mb-3">
				<label for="cedula" class="form-label">Cédula del Cliente:</label> <input
					type="number" id="cedula" name="cedula" class="form-control"
					required>
			</div>

			<button type="submit" class="btn btn-info">Consultar</button>
		</form>

		<!-- Mostrar resultado de la consulta -->
		<%
            String nombres = (String) request.getAttribute("nombres");
            String apellidos = (String) request.getAttribute("apellidos");
            String direccion = (String) request.getAttribute("direccion");
            String telefono = (String) request.getAttribute("telefono");

            if (nombres != null) {
        %>
		<div class="mt-4 p-4 bg-light border rounded">
			<h5>Resultado de la consulta:</h5>
			<p>
				<strong>Nombres:</strong>
				<%= nombres %></p>
			<p>
				<strong>Apellidos:</strong>
				<%= apellidos %></p>
			<p>
				<strong>Dirección:</strong>
				<%= direccion %></p>
			<p>
				<strong>Teléfono:</strong>
				<%= telefono %></p>
		</div>
		<% 
            } else if (request.getAttribute("error") != null) { 
        %>
		<div class="alert alert-danger mt-4">
			<%= request.getAttribute("error") %>
		</div>
		<% } %>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
