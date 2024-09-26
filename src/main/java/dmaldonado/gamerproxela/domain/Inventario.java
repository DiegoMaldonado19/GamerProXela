/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.domain;

/**
 *
 * @author DiegoMaldonado
 */
public class Inventario {

    private int sucursalId;
    private int productoId;
    private int cantidad;
    private String pasillo;

    public Inventario(int sucursalId, int productoId, int cantidad, String pasillo) {
        this.sucursalId = sucursalId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.pasillo = pasillo;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        this.pasillo = pasillo;
    }

}
