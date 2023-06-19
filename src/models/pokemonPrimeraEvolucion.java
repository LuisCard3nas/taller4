package models;

public class pokemonPrimeraEvolucion extends pokemon{

    private String siguienteEvolucion;
    private String evolucionPrevia;

    public pokemonPrimeraEvolucion(int id, String nombre, String etapa, String siguienteEvolucion ,String evolucionPrevia,String tipo1 , String tipo2) {
        super(id, nombre, etapa, tipo1,tipo2);
        this.evolucionPrevia=evolucionPrevia;
        this.siguienteEvolucion=siguienteEvolucion;
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
