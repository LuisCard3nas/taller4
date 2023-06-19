package services;

public interface Elemento {
    /**
     * Realiza la comparacion entre dos Elementos
     * @param elemento
     * @return
     */

    boolean esIgual(Elemento elemento);

    /**
     * Realiza la comparacion de tamanio entre dos Elementos
     * @param elemento
     * @return
     */

    int compararCon(Elemento elemento);
}
