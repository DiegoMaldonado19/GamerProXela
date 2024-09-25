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
 * @author DiegoMaldonado
 */
public class TarjetaDAO {
    
    private Connection conexion; 

    public TarjetaDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public Tarjeta obtenerTarjetaPorId(int id) throws SQLException {
        
        Tarjeta tarjetaCliente = null;
        
        String sql = "SELECT * FROM cd.tarjeta WHERE id = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int tarjetaId = rs.getInt("id");
                String tipoTarjeta = rs.getString("tipo");
                Double puntosPorCada200 = rs.getDouble("puntos_por_cada_200");
                
                tarjetaCliente = new Tarjeta(tarjetaId, tipoTarjeta, puntosPorCada200);
            }
        }

        return tarjetaCliente;
    }
    
}
