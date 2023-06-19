package services;
import models.pokemon;
import models.pokemonPrimeraEvolucion;
import models.pokemonSegundaEvolucion;
import models.pokemonBasico;
import ucn.StdOut;

import java.util.Iterator;

public final class listaNexoDoble implements Lista {

    private NodoDoble cabeza;

    private NodoDoble cola;


    public listaNexoDoble() {
        this.cabeza = null;
        this.cola = null;
    }

    @Override
    public boolean agregar(pokemon pokemon) throws IllegalAccessException {

        NodoDoble nuevo = new NodoDoble(pokemon);
        if (this.cabeza == null) {
            this.cabeza = nuevo;
            this.cola = nuevo;
            return true;
        }
        this.cabeza.setAnterior(nuevo);
        nuevo.setSiguiente(this.cabeza);

        this.cabeza = nuevo;
        return true;
    }

    public Iterator<pokemon> iterator() {
        return new Iterator<pokemon>() {
            private NodoDoble current = cabeza;

            public boolean hasNext() {
                return current != null;
            }

            @Override
            public pokemon next() {
                if (!hasNext()) {
                    throw new IllegalArgumentException("No hay mas elementos en la lista");
                }
                pokemon pokemon = current.getPokemon();
                current = current.getSiguiente();
                return pokemon;
            }

        };
    }

    public void buscarPokemonPorNombre(String nombrePokemon) {
        NodoDoble aux = this.cabeza;
        pokemon auxPokemon = aux.getPokemon();
        while (!auxPokemon.getNombre().equalsIgnoreCase(nombrePokemon)) {
            auxPokemon = aux.getPokemon();
            aux = aux.getSiguiente();
            if (aux == null) {
                StdOut.println("No se encontro el Pokemon Ingresado :(");
                return;
            }
        }
        System.out.println(auxPokemon.getId() + "," + auxPokemon.getNombre() + "," + auxPokemon.getEtapa() + "," + auxPokemon.getTipo1() + "," + auxPokemon.getTipo2());
    }
    public void buscarPokemonPorID(int idPokemon) {
        NodoDoble aux = this.cabeza;
        pokemon auxPokemon = aux.getPokemon();
        while (auxPokemon.getId() != idPokemon) {
            auxPokemon = aux.getPokemon();
            aux = aux.getSiguiente();
            if (aux == null) {
                System.out.println("No se encontró el Pokémon con el ID ingresado :(");
                return;
            }
        }
        System.out.println(auxPokemon.getId() + "," + auxPokemon.getNombre() + "," + auxPokemon.getEtapa() + "," + auxPokemon.getTipo1() + "," + auxPokemon.getTipo2());
    }
}





