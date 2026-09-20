package logica;

public class Resultado {
    private Piloto piloto;
    private Auto auto;
    private int posicion;
    private int cantVueltas;
    private String tiempoVueltas;
    private int puntos;
    private GrandPrix grandPrix;

    public Resultado(Piloto piloto, Auto auto, GrandPrix grandPrix) {
        this.piloto = piloto;
        this.auto = auto;
        this.grandPrix = grandPrix;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public GrandPrix getGrandPrix() {
        return grandPrix;
    }

    public void setGrandPrix(GrandPrix grandPrix) {
        this.grandPrix = grandPrix;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getCantVueltas() {
        return cantVueltas;
    }

    public void setCantVueltas(int cantVueltas) {
        this.cantVueltas = cantVueltas;
    }

    public String getTiempoVueltas() {
        return tiempoVueltas;
    }

    public void setTiempoVueltas(String tiempoVueltas) {
        this.tiempoVueltas = tiempoVueltas;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
