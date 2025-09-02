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

      <button type="submit" class="btn btn-primary">Registrar</button>
    </form>

    <!-- Formulario de Borrado -->
    <form action="DeleteCliente" method="get" class="mb-5 p-4 bg-white rounded shadow-sm">
      <h5 class="mb-3">Eliminar Cliente</h5>
      <div class="mb-3">
        <label for="cedulaDelete" class="form-label">Cedula del Cliente:</label> 
        <input type="number" name="cedula" id="cedulaDelete" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-danger">Borrar</button>
    </form>

    <!-- Formulario de Actualización -->
    <form action="UpdateCliente" method="post"
      class="mb-5 p-4 bg-white rounded shadow-sm">
      <h5 class="mb-3">Actualizar Cliente</h5>
      <div class="mb-3">
        <label for="cedulaUpdate" class="form-label">Cedula del Cliente:</label> 
        <input type="number" name="cedula" id="cedulaUpdate" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-warning">Actualizar</button>
    </form>

    <!-- Botón para Consultar Clientes -->
    <div class="text-center mb-3">
      <form action="ConsultarCliente" method="get">
        <button type="submit" class="btn btn-success">Consultar Clientes</button>
      </form>
    </div>

    <!-- Botón para Generar Reporte PDF -->
    <div class="text-center">
      <form action="ReportePDF" method="get">
        <button type="submit" class="btn btn-secondary"> Generar Reporte PDF</button>
      </form>
    </div>
  </div>

  <!-- Bootstrap 5 JS Bundle -->
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
</body>
</html>
