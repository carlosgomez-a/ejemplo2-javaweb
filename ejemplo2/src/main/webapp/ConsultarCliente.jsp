<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consulta de Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm rounded">
        <div class="card-header bg-info text-white">
            <h4 class="mb-0">Consultar de Cliente</h4>
        </div>
        <div class="card-body">
            <c:if test="${not empty mensaje}">
                <div class="alert alert-warning">${mensaje}</div>
            </c:if>

            <table class="table table-bordered">
                <tr><th>Cédula</th><td>${cedula}</td></tr>
                <tr><th>Nombres</th><td>${nombres}</td></tr>
                <tr><th>Apellidos</th><td>${apellidos}</td></tr>
                <tr><th>Dirección</th><td>${direccion}</td></tr>
                <tr><th>Teléfono</th><td>${telefono}</td></tr>
            </table>

            <a href="index.jsp" class="btn btn-secondary">Volver</a>
        </div>
    </div>
</div>

</body>
</html>
