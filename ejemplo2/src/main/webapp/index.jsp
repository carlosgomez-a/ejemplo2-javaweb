<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<!-- Meta etiquetas requeridas -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap 5 CSS (CDN) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Registro de Clientes</title>

<!-- Estilos personalizados -->
<style>
	body {
		background: linear-gradient(135deg, #f8f9fa, #e9ecef);
		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	}

	h1 {
		color: #343a40;
		font-weight: bold;
	}

	form {
		transition: transform 0.2s ease, box-shadow 0.2s ease;
	}
	form:hover {
		transform: translateY(-3px);
		box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
	}

	/* Botones */
	.btn {
		border-radius: 25px;
		padding: 10px 20px;
		font-weight: 500;
		transition: transform 0.2s ease, opacity 0.2s ease;
	}
	.btn:hover {
		transform: scale(1.05);
		opacity: 0.9;
	}

	/* Colores personalizados */
	.btn-success { background-color: #28a745; border: none; }
	.btn-danger { background-color: #dc3545; border: none; }
	.btn-warning { background-color: #ffc107; border: none; color: #212529; }
	.btn-primary { background-color: #007bff; border: none; }
	.btn-info { background-color: #17a2b8; border: none; }
	.btn-secondary { background-color: #6c757d; border: none; }

	/* Inputs */
	.form-control {
		border-radius: 12px;
	}
	.form-control:focus {
		border-color: #007bff;
		box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, .25);
	}
</style>
</head>
<body class="bg-light">

	<div class="container py-5">
		<h1 class="mb-4 text-center">Registrar Cliente</h1>

		<!-- Formulario de Registro -->
		<form action="ClienteServlet" method="post"
			class="mb-5 p-4 bg-white rounded shadow-sm">
			<div class="mb-3">
				<label class="form-label">Cedula:</label>
				<input type="number" name="cedula" class="form-control" required>
			</div>

			<div class="mb-3">
				<label class="form-label">Nombres:</label>
				<input type="text" name="nombres" class="form-control" required>
			</div>

			<div class="mb-3">
				<label class="form-label">Apellidos:</label>
				<input type="text" name="apellidos" class="form-control" required>
			</div>

			<div class="mb-3">
				<label class="form-label">Direccion:</label>
				<input type="text" name="direccion" class="form-control" required>
			</div>

			<div class="mb-3">
				<label class="form-label">Telefono:</label>
				<input type="text" name="telefono" class="form-control" required>
			</div>

			<button type="submit" class="btn btn-success">Registrar</button>
		</form>

<!-- Formulario de eliminar -->
<form method="post" class="mb-5 p-4 bg-white rounded shadow-sm">
  <h5 class="mb-3">Gestión de Cliente</h5>
  <div class="mb-3">
    <label for="cedulaCliente" class="form-label">Cédula del Cliente:</label>
    <input type="number" name="cedula" id="cedulaCliente"
      class="form-control" required>
  </div>

  <!-- Botones alineados a la izquierda -->
  <div class="d-flex gap-3">
    <!-- Botón eliminar -->
    <button type="submit" formaction="DeleteCliente"
      class="btn btn-danger">Borrar</button>

    <!-- Botón actualizar -->
    <button type="submit"
      formaction="UpdateCliente?action=buscarRegistro"
      class="btn btn-warning">Actualizar</button>

    <!-- Botón consultar -->
    
  </div>
</form>




		<!-- Boton para Generar Reporte PDF -->
		<div class="text-center">
			<form action="ReportePDF" method="get">
				<button type="submit" class="btn btn-secondary">Generar Reporte PDF</button>
				
					<div class="text-center mt-3">
				<a href="EnviarCorreo.jsp" class="btn btn-info">Enviar Email</a>
				
				<a href="ConsultarCliente.jsp" class="btn btn-primary">Consultar Cliente</a>
			</div>
			</form>
		

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
