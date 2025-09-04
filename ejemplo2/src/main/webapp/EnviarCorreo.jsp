<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email</title>
</head>
<body>
	<form action="EnviarCorreo" method=post>
		<h1 class="mb-4 text-center">enviar correo</h1>

		<div class="mb-3">
			<label class="form-label">Correo destinario:</label> <input
				type="text" name="destinatario" class="form-control" required>
		</div>

		<div class="mb-3">
			<label class="form-label">Asunto:</label> <input type="text"
				name="apellidos" class="form-control" required>
		</div>

		<div class="mb-3">
			<label class="form-label">Mensaje:</label> <input type="text"
				name="direccion" class="form-control" required>
		</div>
		
	</form>

</body>
</html>