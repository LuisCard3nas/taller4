package services;

public interface Sistema {
    /**
     * Menu que muestra al usuario las distintas opciones que existen dentro del programa "pokedex"
     */
    void menuPrincipal();

    /**
     * Opcion que le permite al usuario ingresar un rango personalizado de ID's para asi desplegar los pokemons que se encuentren dentro del mismo
     */
    void DesplegarPokemonRango();

    /**
     * Opcion que despliega toda la lista de pokemons , ordenada alfabeticamente
     */
    void DesplegarPokemonOrdenAlfabetico();

    /**
     * Opcion que le permite al usuario ingresar un tipo de pokemon determinado , para asi desplegar los pokemons que cumplan con dicha condicion
     */
    void DesplegarPokemonDependiendoTipo();

    /**
     * Opcion que despliega toda las primeras evoluciones de los pokemons, en un orden decreciente en relacion a sus correspondientes ID´s
     */
    void DesplegarPrimerasEvolucionesDescreciente();

    /**
     * Opcion que le permite al usuario buscar un pokemon de forma personalizada , ya sea por id o nombre, opciones que se le dan a eligir dentro del mismo menu
     */
    void BusquedaPersonalizada();

    /**
     * Metodo utilizado en la opcion "busquedaPersonalizada" , para encontrar el pokemon ingresado desde teclado el al lista de pokemons
     */
    void busquedaPorNombre();

    /**
     * Metodo utilizado en la opcion "busquedaPersonalizada" , para encontrar el pokemon ingresado desde teclado el al lista de pokemons
     */
    void busquedaPorID();

    /**
     * Metodo utilizado para reescribir el archivo "kanto.txt" para asi poder eliminar espacios y erros que se encuentran dentro del mismo , Asi creando un nuevo archivo llamado "kantoOrdenado.txt" con el cual se lleva a cabo la ejecucion del programa
     */
    void ReescribirTXT();

}
