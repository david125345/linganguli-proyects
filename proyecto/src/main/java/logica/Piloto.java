package logica;

import java.util.Date;
public class Piloto {
    private int id;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    private Proveedor proveedor;
    private Auto auto;

    public Piloto(int id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}
