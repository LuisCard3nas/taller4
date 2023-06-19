package models;

public abstract class pokemon {

    private int id;
    private String nombre;
    private String etapa;
    private String tipo1;
    private String tipo2;

    public pokemon(int id, String nombre,String etapa, String tipo1,String tipo2) {
        this.id = id;
        this.nombre = nombre;
        this.etapa=etapa;
        this.tipo1=tipo1;
        this.tipo2=tipo2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }
}
