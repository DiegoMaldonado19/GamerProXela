/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.domain.Empleado;
import dmaldonado.gamerproxela.domain.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Diego Maldonado
 */
public class EmpleadoDAO {
    
    private Connection conexion;

    public EmpleadoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void insertarEmpleado(Empleado empleado) throws SQLException{
        String sqlProducto = "INSERT INTO ce.empleado (cui, nombre, apellido, rol_id, sucursal_id, usuario, contrasena, caja) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmtEmpleado = conexion.prepareStatement(sqlProducto);
        
        pstmtEmpleado.setString(1, empleado.getCui());
        pstmtEmpleado.setString(2, empleado.getNombre());
        pstmtEmpleado.setString(3, empleado.getApellido());
        pstmtEmpleado.setInt(4, empleado.getRolId());
        pstmtEmpleado.setInt(5, empleado.getSucursalId());
        pstmtEmpleado.setString(6, empleado.getUsuario());
        pstmtEmpleado.setString(7, empleado.getContraseña());
        pstmtEmpleado.setInt(8, empleado.getCaja());
        
        pstmtEmpleado.executeUpdate();
        
        pstmtEmpleado.close();
    }
}
