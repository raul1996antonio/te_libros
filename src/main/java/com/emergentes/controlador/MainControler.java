package com.emergentes.controlador;

import com.emergentes.modelo.Libro;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainControler", urlPatterns = {"/MainControler"})
public class MainControler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";

            @SuppressWarnings("Convert2Diamond")
            ArrayList<Libro> lista = new ArrayList<Libro>();
            ConexionDB canal = new ConexionDB();

            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (op.equals("list")) {
                try {
                    //obtener la lista de registros
                    String sql = "select * from libros";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        Libro lib = new Libro();

                        lib.setId(rs.getInt("id"));
                        lib.setIsbn(rs.getString("isbn"));
                        lib.setTitulo(rs.getString("titulo"));
                        lib.setCategoria(rs.getString("categoria"));

                        lista.add(lib);

                    }
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (op.equals("nuevo")) {
                //Insertar  nuevos registros
                Libro li = new Libro();

                request.setAttribute("lib", li);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if (op.equals("eliminar")) {
                //elimar 
                int id = Integer.parseInt(request.getParameter("id"));

                String sql = "delete from libros where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();

                response.sendRedirect("MainControler");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String isbn = request.getParameter("isbn");
            String titulo = request.getParameter("titulo");
            String categoria = request.getParameter("categoria");

            Libro lib = new Libro();

            lib.setId(id);
            lib.setTitulo(titulo);
            lib.setCategoria(categoria);

            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();

            PreparedStatement ps;

            if (id == 0) {
                //Nuevo registro
                String sql = "insert into libro (isbn, titulo, categoria) values (?,?,?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, lib.getIsbn());
                ps.setString(2, lib.getTitulo());
                ps.setString(3, lib.getCategoria());

                ps.executeUpdate();
                response.sendRedirect("MainControler");
            }

        } catch (SQLException e) {
            System.out.println("Error en SQL " + e.getMessage());
        }

    }

}
