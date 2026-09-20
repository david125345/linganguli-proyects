package logica;

import java.util.Date;

public class Auto {
    private int id;
    private String modelo;
    private Date anoFabricacion;

    public Auto(int id, String modelo, Date anoFabricacion) {
        this.id = id;
        this.modelo = modelo;
        this.anoFabricacion = anoFabricacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(Date anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

}
