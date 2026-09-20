package logica;

import java.util.ArrayList;

public class Pilotos {
    private ArrayList<Piloto> pilotos;
    private static Pilotos instancia;

    private Pilotos() {
        pilotos = new ArrayList<>();
    }

    public static Pilotos getInstance() {
        if (instancia == null) {
            instancia = new Pilotos();
        }
        return instancia;
    }

    public Piloto getPiloto(int id) {
        for (Piloto piloto : pilotos) {
            if (piloto.getId() == id) {
                return piloto;
            }
        }
        return null;
    }

    public void addPiloto(Piloto piloto) {
        if (piloto != null) {
            pilotos.add(piloto);
        }
    }

    public void removePiloto(Piloto piloto) {
        pilotos.remove(piloto);
    }
}