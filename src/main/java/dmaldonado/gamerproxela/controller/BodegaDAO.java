/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.domain.Bodega;
import dmaldonado.gamerproxela.domain.BodegaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diego Maldonado
 */
public class BodegaDAO {

    private Connection conexion;

    public BodegaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarProductoEnBodega(Bodega bodega) throws SQLException {
        String sqlProducto = "INSERT INTO cb.bodega (sucursal_id, producto_id, cantidad) VALUES (?, ?, ?)";
        PreparedStatement pstmtBodega = conexion.prepareStatement(sqlProducto);

        pstmtBodega.setInt(1, bodega.getSucursalId());
        pstmtBodega.setInt(2, bodega.getProductoId());
        pstmtBodega.setInt(3, bodega.getCantidad());

        pstmtBodega.executeUpdate();

        pstmtBodega.close();
    }

    public Optional<List<BodegaDTO>> obtenerProductosEnBodega(String nombreSucursal) throws SQLException {
        List<BodegaDTO> productosEnBodega = new ArrayList<>();
        String sql = "SELECT \n"
                + "    s.nombre AS nombre_sucursal,\n"
                + "    p.nombre AS nombre_producto,\n"
                + "    b.cantidad\n"
                + "FROM \n"
                + "    cp.sucursal s\n"
                + "JOIN \n"
                + "    cb.bodega b ON s.id = b.sucursal_id\n"
                + "JOIN \n"
                + "    cp.producto p ON b.producto_id = p.id\n"
                + "WHERE \n"
                + "    s.nombre LIKE ?";

        try (PreparedStatement pstmt = this.conexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombreSucursal + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombreSucursalDTO = rs.getString("nombre_sucursal");
                String nombreProductoDTO = rs.getString("nombre_producto");
                int cantidadDTO = rs.getInt("cantidad");

                BodegaDTO bodegaDTO = new BodegaDTO(nombreSucursalDTO, nombreProductoDTO, cantidadDTO);
                productosEnBodega.add(bodegaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productosEnBodega.isEmpty() ? Optional.empty() : Optional.of(productosEnBodega);
    }
}
