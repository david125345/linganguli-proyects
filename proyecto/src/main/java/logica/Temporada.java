package logica;

public class Temporada {
    private int anoTemporada;
    private GrandPrix grandPrix;

    public Temporada(int anoTemporada, GrandPrix grandPrix) {
        this.anoTemporada = anoTemporada;
        this.grandPrix = grandPrix;
    }

    public int getAnoTemporada() {
        return anoTemporada;
    }

    public void setAnoTemporada(int anoTemporada) {
        this.anoTemporada = anoTemporada;
    }

    public GrandPrix getGrandPrix() {
        return grandPrix;
    }

    public void setTemporada(GrandPrix grandPrix) {
        this.grandPrix = grandPrix;
    }
}
