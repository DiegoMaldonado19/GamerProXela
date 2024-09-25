/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.domain;

/**
 *
 * @author DiegoMaldonado
 */
public class Venta {

    private int id;
    private String fecha;
    private String clienteId;
    private String cajeroId;
    private Double totalSinDescuentos;
    private Double totalConDescuentos;

    public Venta(int id, String fecha, String clienteId, String cajeroId, Double totalSinDescuentos, Double totalConDescuentos) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.cajeroId = cajeroId;
        this.totalSinDescuentos = totalSinDescuentos;
        this.totalConDescuentos = totalConDescuentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getCajeroId() {
        return cajeroId;
    }

    public void setCajeroId(String cajeroId) {
        this.cajeroId = cajeroId;
    }

    public Double getTotalSinDescuentos() {
        return totalSinDescuentos;
    }

    public void setTotalSinDescuentos(Double totalSinDescuentos) {
        this.totalSinDescuentos = totalSinDescuentos;
    }

    public Double getTotalConDescuentos() {
        return totalConDescuentos;
    }

    public void setTotalConDescuentos(Double totalConDescuentos) {
        this.totalConDescuentos = totalConDescuentos;
    }

}
