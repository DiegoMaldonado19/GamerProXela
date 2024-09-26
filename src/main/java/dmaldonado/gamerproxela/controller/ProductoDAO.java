/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.domain.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DiegoMaldonado
 */
public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Optional<List<Producto>> obtenerProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM cp.producto";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");

                Producto producto = new Producto(id, nombre, descripcion, precio);
                productos.add(producto);
            }
        }

        return productos.isEmpty() ? Optional.empty() : Optional.of(productos);
    }

    public void insertarProducto(Producto producto) throws SQLException {
        String sqlProducto = "INSERT INTO cp.producto (nombre, descripcion, precio) VALUES (?, ?, ?)";
        PreparedStatement pstmtProducto = conexion.prepareStatement(sqlProducto);

        pstmtProducto.setString(1, producto.getNombre());
        pstmtProducto.setString(2, producto.getDescripcion());
        pstmtProducto.setDouble(3, producto.getPrecio());

        pstmtProducto.executeUpdate();

        pstmtProducto.close();
    }

    public int obtenerIdProductoPorNombre(String nombreProducto) throws SQLException {
        int idProducto = -1;
        String sql = "SELECT id FROM cp.producto WHERE nombre = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombreProducto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idProducto = rs.getInt("id");
                System.out.println(idProducto);
            }
        }

        return idProducto;
    }
}
