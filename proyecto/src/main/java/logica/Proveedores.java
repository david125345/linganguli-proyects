package logica;

import java.util.ArrayList;

public class Proveedores {
    private ArrayList<Proveedor> proveedores;
    private static Proveedores instancia;

    private Proveedores() {
        proveedores = new ArrayList<>();
    }

    public static Proveedores getInstance() {
        if (instancia == null) {
            instancia = new Proveedores();
        }
        return instancia;
    }

    public Proveedor getProveedor(int id) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() == id) {
                return proveedor;
            }
        }
        return null;
    }

    public void addProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            proveedores.add(proveedor);
        }
    }

    public void removeProveedor(Proveedor proveedor) {
        proveedores.remove(proveedor);
    }
}