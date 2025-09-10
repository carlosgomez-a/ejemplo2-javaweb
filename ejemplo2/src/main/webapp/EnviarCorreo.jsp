<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email</title>

<!-- Bootstrap 5 CSS: estilos modernos y adaptables -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow-lg rounded-3">
					<div class="card-body p-4">
						<h2 class="text-center mb-4">Enviar correo</h2>

						<!--Campos requeridos para poder enviar el correo al destinatario final  -->
						<form action="EnviarCorreo" method="post">
							<div class="mb-3">
								<label class="form-label">Correo destinatario:</label> <input
									type="email" name="destinatario" class="form-control"
									placeholder="ejemplo@correo.com" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Asunto:</label> <input type="text"
									name="asunto" class="form-control"
									placeholder="Asunto del correo" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Mensaje:</label>
								<textarea name="mensaje" class="form-control" rows="4"
									placeholder="Escribe tu mensaje aquí..." required></textarea>
							</div>

							<!--  Botón enviar -->			
							<button type="submit" class="btn btn-primary w-100">Enviar</button>
						</form>

							<!-- Botón volver al inicio -->	
						<div class="text-center mt-3">
							<a href="index.jsp" class="btn btn-secondary w-100">Volver</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap 5 JS (opcional para interactividad) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
