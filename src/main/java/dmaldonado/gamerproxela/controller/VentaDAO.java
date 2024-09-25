/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.domain.DetalleVenta;
import dmaldonado.gamerproxela.domain.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego Maldonado
 */
public class VentaDAO {
    private Connection conexion; 

    public VentaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private int obtenerIdProductoPorNombre(String nombreProducto) throws SQLException {
        int idProducto = -1;
        String sql = "SELECT id FROM cp.producto WHERE nombre = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombreProducto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idProducto = rs.getInt("id");
            }
        }

        return idProducto;
    }

    public void insertarVenta(Venta venta, List<DetalleVenta> detalles) throws SQLException {
        
        String sqlVenta = "INSERT INTO cv.venta (fecha, cliente_id, cajero_id, total_sin_descuentos, total_con_descuentos ) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmtVenta = conexion.prepareStatement(sqlVenta);
        
        pstmtVenta.setString(1, venta.getFecha());
        pstmtVenta.setString(2, venta.getClienteId());
        pstmtVenta.setString(3, venta.getCajeroId());
        pstmtVenta.setDouble(4, venta.getTotalSinDescuentos());
        pstmtVenta.setDouble(5, venta.getTotalConDescuentos());
        pstmtVenta.executeUpdate();

        int idVentaGenerado = obtenerUltimoIdGenerado();

        
        String sqlDetalleVenta = "INSERT INTO cv.detalle_venta (venta_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmtDetalleVenta = conexion.prepareStatement(sqlDetalleVenta);

        for (DetalleVenta detalle : detalles) {
            
            detalle.setVentaId(idVentaGenerado);
            
            pstmtDetalleVenta.setInt(1, detalle.getVentaId());
            pstmtDetalleVenta.setInt(2, detalle.getProductoId());
            pstmtDetalleVenta.setInt(3, detalle.getCantidad());
            pstmtDetalleVenta.setDouble(4, detalle.getPrecioUnitario());
            pstmtDetalleVenta.executeUpdate();
            
        }

        pstmtVenta.close();
        pstmtDetalleVenta.close();
    }

    private int obtenerUltimoIdGenerado() throws SQLException {
        int idGenerado = -1;
        String sql = "SELECT MAX(id) as id FROM cv.venta";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            idGenerado = rs.getInt("id");
        }

        rs.close();
        pstmt.close();

        return idGenerado;
    }
}
