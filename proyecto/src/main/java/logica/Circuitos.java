package logica;

import java.util.ArrayList;

/**
 * @author Gabriel Correa
 */
public class Circuitos {
    private final ArrayList<Circuito> circuitos;

    public Circuitos() {
        circuitos = new ArrayList<>();
    }

    public Circuito getCircuito(int id) {
        for (Circuito circuito : circuitos) {
            if (circuito.getId() == id) {
                return circuito;
            }
        }
        return null;
    }

    public void addCircuito(Circuito circuito) {
        if (circuito != null) {
            circuitos.add(circuito);
        }
    }

    public void removeCircuito(int id) {
        Circuito circuitoToRemove = getCircuito(id);
        if (circuitoToRemove != null) {
            circuitos.remove(circuitoToRemove);
        }
    }
}