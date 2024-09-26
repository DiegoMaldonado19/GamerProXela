/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.controller;

import dmaldonado.gamerproxela.database.Database;
import dmaldonado.gamerproxela.view.admin.JFAdmin;
import dmaldonado.gamerproxela.view.bodega.JFBodega;
import dmaldonado.gamerproxela.view.cajero.JFCajero;
import dmaldonado.gamerproxela.view.inventario.JFInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Maldonado
 */
public class LoginController {
    
    private Connection conexion;
    private Database database;
    private SucursalDAO sucursalDAO;
    private JFrame loginFrame;

    public LoginController(JFrame loginFrame) {
        this.database = new Database();
        this.conexion = this.database.establecerConexionAdmin();
        this.loginFrame = loginFrame;
    }
    
    public void login(String username, String password){
        
         try {
            // Hacemos el query
            String consultaUsuario = "SELECT * FROM ce.empleado WHERE usuario = ?";
            PreparedStatement pstmtUsuario = conexion.prepareStatement(consultaUsuario);
            pstmtUsuario.setString(1, username);
            ResultSet resultadoUsuario = pstmtUsuario.executeQuery();
            System.out.println("otro "+resultadoUsuario);

            if (resultadoUsuario.next()) {
                String contraseñaAlmacenada = resultadoUsuario.getString("contrasena");
                System.out.println("contraseña "+contraseñaAlmacenada );
                if (password.equals(contraseñaAlmacenada)) {
                    int rolId = resultadoUsuario.getInt("rol_id");
                    String nombre = resultadoUsuario.getString("nombre");
                    int id = resultadoUsuario.getInt("sucursal_id");
                    System.out.println(id);
                    
                    this.sucursalDAO = new SucursalDAO(this.conexion);
                    
                    if(rolId == 1){
                        this.conexion = database.establecerConexionPorRol("Cajero");
                        JFCajero cajeroFrame = new JFCajero(this.sucursalDAO.obtenerNombreSucursalPorId(id), "Cajero", nombre, this.conexion);
                        cajeroFrame.setVisible(true);
                        this.loginFrame.setVisible(false);
                    } else if(rolId == 2){
                        this.conexion = database.establecerConexionPorRol("Bodega");
                        JFBodega bodegaFrame = new JFBodega(id, "Bodega", nombre, this.conexion);
                        bodegaFrame.setVisible(true);
                        this.loginFrame.setVisible(false);
                    } else if(rolId == 3){
                        this.conexion = database.establecerConexionPorRol("Inventario");
                        JFInventario inventarioFrame = new JFInventario(id, this.sucursalDAO.obtenerNombreSucursalPorId(id), "Inventario", nombre, this.conexion);
                        inventarioFrame.setVisible(true);
                        this.loginFrame.setVisible(false);
                    } else if(rolId == 4){
                        this.conexion = database.establecerConexionPorRol("Administrador");
                        JFAdmin adminFrame = new JFAdmin(this.sucursalDAO.obtenerNombreSucursalPorId(id), "Administrador", nombre, this.conexion);
                        adminFrame.setVisible(true);
                        this.loginFrame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Rol desconocido");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nombre de usuario no encontrado");
            }

            resultadoUsuario.close();
            pstmtUsuario.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage());
        }

    }
}
