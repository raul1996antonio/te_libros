<%@page import="com.emergentes.modelo.Libro"%>
<%
    Libro lib = (Libro)request.getAttribute("lib");
%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Libro</h1>
        <form action="MainControler" method="post">
            <imput type="MainControler" method="post"><!-- comment -->
            <table>
                <tr>
                    <td>ISBN</td>
                    <td> <input type="text" name="isbn" value="${lib.isbn}"> </td>          
                </tr>
                <tr>
                    <td>titulo</td>
                    <td> <input type="text" name="titulo" value="${lib.titulo}"> </td>          
                </tr>
                <tr>
                    <td>categoria</td>
                    <td> <input type="text" name="categoria" value="${lib.categoria}"> </td>          
                </tr>
                <tr>
                    <td></td>
                    <td> <input type="submit"> </td>          
                </tr>
            
            </table>
            
            
            
        </form>
    </body>
</html>
