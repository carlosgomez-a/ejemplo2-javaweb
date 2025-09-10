<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Actualizar Cliente</title>

<!-- Bootstrap 5 para estilos y diseño responsivo -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="card shadow-sm rounded">
			<div class="card-header bg-warning text-dark">
				<h4 class="mb-0">Actualizar Cliente</h4>
			</div>
			<div class="card-body">

					<!-- Formulario para actualizar los datos de un cliente -->
				<form action="UpdateCliente" method="post" class="p-3">

					<input type="hidden" name="action" value="actualizarRegistro">

					<!-- Campo oculto: indica la acción que se ejecutará en el servlet -->
					<div class="mb-3">
						<label for="cedula" class="form-label">Cédula:</label> <input
							type="text" class="form-control" id="cedula" name="cedula"
							value="${cedula}" readonly>
					</div>

					<!-- Campos editables: nombres, apellidos, dirección, teléfono -->
					<div class="mb-3">
						<label for="nombres" class="form-label">Nombres:</label> <input
							type="text" class="form-control" id="nombres" name="nombres"
							value="${nombres}" required>
					</div>
					
					

					<div class="mb-3">
						<label for="apellidos" class="form-label">Apellidos:</label> <input
							type="text" class="form-control" id="apellidos" name="apellidos"
							value="${apellidos}" required>
					</div>

					<div class="mb-3">
						<label for="direccion" class="form-label">Dirección:</label> <input
							type="text" class="form-control" id="direccion" name="direccion"
							value="${direccion}">
					</div>

					<div class="mb-3">
						<label for="telefono" class="form-label">Teléfono:</label> <input
							type="text" class="form-control" id="telefono" name="telefono"
							value="${telefono}">
					</div>

						<!-- Botones: volver al inicio o guardar cambios -->
					<div class="d-flex justify-content-between">
						<a href="index.jsp" class="btn btn-secondary">Volver</a>
						<button type="submit" class="btn btn-success">Guardar Cambios</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>
