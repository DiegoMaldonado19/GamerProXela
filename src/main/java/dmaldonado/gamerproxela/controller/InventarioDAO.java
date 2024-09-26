/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.domain.Inventario;
import dmaldonado.gamerproxela.domain.InventarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Maldonado
 */
public class InventarioDAO {

    private Connection conexion;

    public InventarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarProductoEnBodega(Inventario inventario) throws SQLException {
        String sqlProducto = "INSERT INTO ci.inventario (sucursal_id, producto_id, cantidad, pasillo) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmtInventario = conexion.prepareStatement(sqlProducto);

        pstmtInventario.setInt(1, inventario.getSucursalId());
        pstmtInventario.setInt(2, inventario.getProductoId());
        pstmtInventario.setInt(3, inventario.getCantidad());
        pstmtInventario.setString(4, inventario.getPasillo());

        pstmtInventario.executeUpdate();

        pstmtInventario.close();
    }

    public Optional<List<InventarioDTO>> obtenerProductosEnBodega(String nombreSucursal) throws SQLException {
        List<InventarioDTO> productosEnInventario = new ArrayList<>();
        String sql = "SELECT \n"
                + "    p.nombre AS nombre_producto,\n"
                + "    i.cantidad,\n"
                + "    i.pasillo,\n"
                + "    p.precio AS precio_producto\n"
                + "FROM \n"
                + "    cp.sucursal s\n"
                + "JOIN \n"
                + "    ci.inventario i ON s.id = i.sucursal_id\n"
                + "JOIN \n"
                + "    cp.producto p ON i.producto_id = p.id\n"
                + "WHERE \n"
                + "    s.nombre LIKE ?";

        try (PreparedStatement pstmt = this.conexion.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nombreSucursal + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String nombreProductoDTO = rs.getString("nombre_producto");
                int cantidadDTO = rs.getInt("cantidad");
                String pasilloDTO = rs.getString("pasillo");
                Double precioDTO = rs.getDouble("precio_producto");

                InventarioDTO inventarioDTO = new InventarioDTO(nombreSucursal, nombreProductoDTO, cantidadDTO, pasilloDTO, precioDTO);
                productosEnInventario.add(inventarioDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productosEnInventario.isEmpty() ? Optional.empty() : Optional.of(productosEnInventario);
    }
}
