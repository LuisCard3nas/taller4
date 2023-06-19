package models;

public class pokemonBasico extends pokemon{
    private String siguienteEvolucion;
    private String evolucionPrevia;

    public pokemonBasico(int id, String nombre, String etapa, String siguienteEvolucion ,String evolucionPrevia,String tipo1 , String tipo2) {
        super(id, nombre, etapa, tipo1, tipo2);
        this.siguienteEvolucion=siguienteEvolucion;
        this.evolucionPrevia=evolucionPrevia;
    }

    public String getSiguienteEvolucion() {
        return siguienteEvolucion;
    }

    public void setSiguienteEvolucion(String siguienteEvolucion) {
        this.siguienteEvolucion = siguienteEvolucion;
    }

    public String getEvolucionPrevia() {
        return evolucionPrevia;
    }

    public void setEvolucionPrevia(String evolucionPrevia) {
        this.evolucionPrevia = evolucionPrevia;
    }
}
