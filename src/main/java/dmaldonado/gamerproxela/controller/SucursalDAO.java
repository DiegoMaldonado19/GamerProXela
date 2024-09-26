/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.domain.Tarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego Maldonado
 */
public class SucursalDAO {
    private Connection conexion; 

    public SucursalDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public String obtenerNombreSucursalPorId(int id) throws SQLException {
        
        String nombreSucursal = "";
        
        String sql = "SELECT nombre FROM cp.surcursal WHERE id = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nombreSucursal = rs.getString("nombre");
            }
        }

        return nombreSucursal;
    }
}
