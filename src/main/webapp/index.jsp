<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Libro"%>
<%@page import="java.util.List"%>
<%
    List<Libro> lista = (List<Libro>) request.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Libros!</h1>
        <p> <a href="MainControler?op=nuevo">NUevo</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>ISBN</th>
                <th>Titulo</th>
                <th>Categoria</th>
            </tr>
            <c:forEach var="item" items="${lista}">
            
            <tr>
                <td>${item.id}</td>
                <td>${item.isbn}</td>
                <td>${item.titulo}</td>
                <td>${item.categoria}</td>
                <td>
                    <a href="MainControler?op=eliminar&id=${item.id}" onclick="return(confirm('esta seguro?'))">Eliminar</a>
                </td>
            </tr>
            </c:forEach>
        </table>
            
    </body>
</html>
