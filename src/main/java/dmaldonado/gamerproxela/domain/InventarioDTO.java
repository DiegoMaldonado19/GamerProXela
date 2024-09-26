/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.domain;

/**
 *
 * @author Diego Maldonado
 */
public class InventarioDTO {

    private String nombreSucursal;
    private String nombreProducto;
    private int cantidad;
    private String pasillo;
    private Double precio;

    public InventarioDTO(String nombreSucursal, String nombreProducto,int cantidad, String pasillo, Double precio) {
        this.nombreSucursal = nombreSucursal;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.pasillo = pasillo;
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        this.pasillo = pasillo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
