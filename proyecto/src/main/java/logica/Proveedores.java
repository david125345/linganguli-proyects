package logica;

import java.util.ArrayList;

/**
 * @author Gabriel Correa
 */
public class Proveedores {

    private final ArrayList<Proveedor> proveedores;

    public Proveedores() {
        proveedores = new ArrayList<>();
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

    public void removeProveedor(int id) {
        Proveedor proveedorToRemove = getProveedor(id);
        if (proveedorToRemove != null) {
            proveedores.remove(proveedorToRemove);
        }
    }
}
