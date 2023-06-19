package services;
import models.pokemon;

public interface Lista {
    /**
     * En esta lista se agrega un pokemon
     * @param pokemon
     * @return
     * @throws IllegalAccessException
     */

    boolean agregar(pokemon pokemon) throws IllegalAccessException;
}
