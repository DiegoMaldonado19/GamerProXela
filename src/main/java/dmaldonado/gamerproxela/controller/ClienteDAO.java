/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.domain.Cliente;
import dmaldonado.gamerproxela.domain.Tarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author DiegoMaldonado
 */
public class ClienteDAO {
    private Connection conexion;
    private TarjetaDAO tarjetaDAO;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
        this.tarjetaDAO = new TarjetaDAO(conexion);
    }

    public Optional<List<Cliente>> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Tarjeta tarjeta = null;
        String sql = "SELECT * FROM cc.cliente";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nit = rs.getString("nit");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                int idTarjeta = rs.getInt("id_tarjeta");
                
                if( idTarjeta!=0 ){
                    tarjeta = tarjetaDAO.obtenerTarjetaPorId(idTarjeta);
                }

                Cliente cliente = new Cliente(nit, nombre, apellido, direccion, tarjeta);
                clientes.add(cliente);
            }
        }

        return clientes.isEmpty() ? Optional.empty() : Optional.of(clientes);
    }
    
    public void insertarProducto(Cliente cliente) throws SQLException{
        String sqlCliente = "INSERT INTO cc.cliente (nit, nombre, apellido, direccion, id_tarjeta) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmtCliente = conexion.prepareStatement(sqlCliente);
        
        pstmtCliente.setString(1, cliente.getNit());
        pstmtCliente.setString(2, cliente.getNombre());
        pstmtCliente.setString(3, cliente.getApellido());
        pstmtCliente.setString(4, cliente.getDireccion());
        pstmtCliente.setInt(5, cliente.getTarjeta().getId());
        
        pstmtCliente.executeUpdate();
        
        pstmtCliente.close();
    }
}
