package logica;

import java.util.ArrayList;

public class Circuitos {
    private ArrayList<Circuito> circuitos;
    private static Circuitos instancia;

    private Circuitos() {
        circuitos = new ArrayList<>();
    }

    public static Circuitos getInstance() {
        if (instancia == null) {
            instancia = new Circuitos();
        }
        return instancia;
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

    public void removeCircuito(Circuito circuito) {
        circuitos.remove(circuito);
    }
}