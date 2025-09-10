<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Consultar Cliente</title>

<!-- Página JSP para consultar clientes en la base de datos -->
<!-- Muestra un formulario para ingresar la cédula del cliente y busca sus datos -->

<!-- Librería Bootstrap 5 para estilos  y diseño responsive -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="card shadow-sm rounded">
			<div class="card-header bg-primary text-white">
				<h4 class="mb-0">Consultar Cliente</h4>
			</div>
			<div class="card-body">

                <!-- Mostrar mensaje si existe (ejemplo: cliente no encontrado o error) -->
				<c:if test="${not empty mensaje}">
					<div class="alert alert-warning">${mensaje}</div>
				</c:if>
				
				<!-- Formulario para ingresar la cédula -->
				<form action="ConsultarCliente" method="post" class="mb-4">
					<div class="mb-3">
						<label for="cedula" class="form-label">Cédula del cliente:</label>
						<input type="text" class="form-control" id="cedula" name="cedula"
							required>
					</div>
					
					<!-- Formulario para ingresar la cédula -->
					<button type="submit" class="btn btn-primary">Buscar</button>
					<a href="index.jsp" class="btn btn-secondary">Volver</a>
				</form>

				  <!-- Mostrar resultados solo si se encontró el cliente -->
				<c:if test="${not empty cedula}">
					<ul class="list-group">
						<li class="list-group-item"><strong>Cédula:</strong>
							${cedula}</li>
						<li class="list-group-item"><strong>Nombres:</strong>
							${nombres}</li>
						<li class="list-group-item"><strong>Apellidos:</strong>
							${apellidos}</li>
						<li class="list-group-item"><strong>Dirección:</strong>
							${direccion}</li>
						<li class="list-group-item"><strong>Teléfono:</strong>
							${telefono}</li>
					</ul>
				</c:if>

			</div>
		</div>
	</div>

</body>
</html>
