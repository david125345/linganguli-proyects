package logica;

import java.util.ArrayList;

public class Temporadas {
    private ArrayList<Temporada> temporadas;
    private static Temporadas instancia;

    private Temporadas() {
        temporadas = new ArrayList<>();
    }

    public static Temporadas getInstance() {
        if (instancia == null) {
            instancia = new Temporadas();
        }
        return instancia;
    }

    public Temporada getTemporada(int anoTemporada) {
        for (Temporada temporada : temporadas) {
            if (temporada.getAnoTemporada() == anoTemporada) {
                return temporada;
            }
        }
        return null;
    }

    public void addTemporada(Temporada temporada) {
        if (temporada != null) {
            temporadas.add(temporada);
        }
    }

    public void removeTemporada(Temporada temporada) {
        temporadas.remove(temporada);
    }
}