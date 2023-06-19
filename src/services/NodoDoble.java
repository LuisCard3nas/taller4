package services;
import models.pokemon;

public final class NodoDoble {

    private final pokemon pokemon;

    private NodoDoble siguiente;

    private NodoDoble anterior;

    public NodoDoble(pokemon pokemon) throws IllegalAccessException {
        if(pokemon==null){
            throw new IllegalAccessException("Pokemon no valido");
        }
        this.pokemon=pokemon;
    }

    public models.pokemon getPokemon() {
        return pokemon;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
}
