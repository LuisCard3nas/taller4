package services;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import models.pokemonBasico;
import models.pokemonPrimeraEvolucion;
import models.pokemonSegundaEvolucion;
import ucn.StdIn;
import ucn.StdOut;
import models.pokemon;


public class Pokedex implements Sistema {
    public listaNexoDoble listaPokemons;
    public Pokedex (){
        listaPokemons = new listaNexoDoble();

    }
    ArrayList<pokemon> ListaA = new ArrayList<pokemon>();
    
    @Override
    public void menuPrincipal(){
        //Se inicia el menu preguntando por una opcion, luego se llamaran a sus respectivos procedimientos para dicha opcion.
        String opcion="";
        while(!opcion.equals("6")){
            System.out.println(" [*] POKEDEX [*]\n"+
                    " [1] Desplegar Pokemons Segun Rango\n" +
                    " [2] Deplegar Pokemons En Orden Alfabetico\n" +
                    " [3] Desplegar Pokemons Dependiendo De Un Tipo\n" +
                    " [4] Desplegar Primeras Evoluciones En Orden Decreciente \n" +
                    " [5] Busqueda Personalizada\n" +
                    " [6] Salir");
            System.out.println("Ingrese una opcion:  ");
            opcion= StdIn.readLine();

            switch (opcion) {
                //Segun la opcion se dirige a su procedimiento.
                case "1" -> DesplegarPokemonRango();
                case "2" -> DesplegarPokemonOrdenAlfabetico();
                case "3" -> DesplegarPokemonDependiendoTipo();
                case "4" -> DesplegarPrimerasEvolucionesDescreciente();
                case "5" -> BusquedaPersonalizada();
                case "6" -> StdOut.println("Hasta Pronto!");
                default -> StdOut.println("Ingrese una opcion valida");

            }
        }
    }
    @Override
    public void DesplegarPokemonRango(){
        //Procedimiento encargado de desplegar los pokemons en un rango.
        int rangoMinimo = 0;
        int rangoMaximo = 0;
        StdOut.println("Ingrese el rango minimo (1 minimo):");
        rangoMinimo = StdIn.readInt();
        StdOut.println("Ingrese el rango maximo (30 maximo):");
        rangoMaximo = StdIn.readInt();
        //Se valida que no sean negativos ni mayores al rango maximo de pokemones en la lista
        //OJO: Si prueba un .txt que tenga los 150 pokemons, cambiar el valor de rangoMaximo a 151, ya que este se hizo con el .txt de ejemplo.
        if (rangoMinimo<=0 || rangoMaximo>=31 || rangoMinimo>rangoMaximo){
            System.out.println("Por favor que los intervalos se encuentren en los rangos 1-30");
            return;
        }
        //Se crean ArrayList para almacenar el contenido del Nodo(Pokemon).
        ArrayList<pokemon> rangoPokemon =new ArrayList<>();
        //Se crea un iterador para recorre la lista e ir agregando el pokemons en cada espacio del ArrayList.
        Iterator<pokemon> iterator = this.listaPokemons.iterator();
        //Se agrega en el ArrayList.
        while(iterator.hasNext()){
            pokemon pokemon = iterator.next();
            rangoPokemon.add(pokemon);
        }
        //Se crea un comparador para ir ordenando el Arraylist segun su ID en orden creciente.
        Comparator<pokemon> comparator = new Comparator<pokemon>() {
            @Override
            public int compare(pokemon p1, pokemon p2) {
                return Integer.compare(p1.getId(), p2.getId());
            }
        };
        Collections.sort(rangoPokemon, comparator);
        StdOut.println("/// LOS POKEMONS QUE SE ENCUENTRAN ENTRE "+rangoMinimo+ " Y "+rangoMaximo+" SON :");
        //For usado para desplegar los pokemons en el rango solicitado, ademas de construir al pokemons segun su etapa, basico,primeraEvolucion o segundaEvolucion para finalmente mostrarlo.
        for(int i = rangoMinimo - 1; i  <= rangoMaximo - 1 && i <= rangoPokemon.size(); i++) {
            pokemon pokemon = rangoPokemon.get(i);
            switch (pokemon.getEtapa()){
                case "Basico"->{
                    //Se arma el basico.
                    pokemonBasico auxPokemon= (pokemonBasico)pokemon;
                    if (auxPokemon.getSiguienteEvolucion()==null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                    if ((auxPokemon.getSiguienteEvolucion() == null && (auxPokemon.getEvolucionPrevia() != null))){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonBasico) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                    if (auxPokemon.getSiguienteEvolucion()!=null && auxPokemon.getEvolucionPrevia()!=null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonBasico) pokemon).getSiguienteEvolucion() + "," + ((pokemonBasico) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                }
                case "PrimeraEvolucion"->{
                    //Se arma PrimeraEvolucion.
                    pokemonPrimeraEvolucion auxpokemon1=(pokemonPrimeraEvolucion) pokemon;
                    if(auxpokemon1.getSiguienteEvolucion()==null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                    if(auxpokemon1.getSiguienteEvolucion()!=null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getSiguienteEvolucion() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                }
                case "SegundaEvolucion"->{
                    //Se arma SegundaEvolucion.
                    pokemonSegundaEvolucion auxpokemon2 = (pokemonSegundaEvolucion) pokemon;
                    StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonSegundaEvolucion) pokemon).getSiguienteEvolucion() + "," + ((pokemonSegundaEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }
    @Override
    public void DesplegarPokemonOrdenAlfabetico(){
        //Se crea el ArrayList y se agregan los pokemons
        ArrayList<pokemon> rangoPokemon =new ArrayList<>();
        Iterator<pokemon> iterator = this.listaPokemons.iterator();
        while(iterator.hasNext()){
            pokemon pokemon = iterator.next();
            rangoPokemon.add(pokemon);
        }
        //Se ordena el ArrayList segun el nombre, de manera alfabetica.
        Collections.sort(rangoPokemon, (pokemon p1,pokemon p2)->p1.getNombre().compareTo(p2.getNombre()));
        //Se despliegan todos los pokemons dentro del ArrayList, ya ordenado, con un Iterador.
        Iterator<pokemon> iterator1 = rangoPokemon.iterator();
        while (iterator1.hasNext()){
            pokemon pokemon = iterator1.next();

            switch (pokemon.getEtapa()){
                case "Basico"->{
                    pokemonBasico auxPokemon= (pokemonBasico)pokemon;
                    if (auxPokemon.getSiguienteEvolucion()==null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                    if ((auxPokemon.getSiguienteEvolucion() == null && (auxPokemon.getEvolucionPrevia() != null))){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonBasico) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                    if (auxPokemon.getSiguienteEvolucion()!=null && auxPokemon.getEvolucionPrevia()!=null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonBasico) pokemon).getSiguienteEvolucion() + "," + ((pokemonBasico) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                }
                case "PrimeraEvolucion"->{
                    pokemonPrimeraEvolucion auxpokemon1=(pokemonPrimeraEvolucion) pokemon;
                    if(auxpokemon1.getSiguienteEvolucion()==null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                    if(auxpokemon1.getSiguienteEvolucion()!=null){
                        StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getSiguienteEvolucion() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                    }
                }
                case "SegundaEvolucion"->{
                    pokemonSegundaEvolucion auxpokemon2 = (pokemonSegundaEvolucion) pokemon;
                    StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonSegundaEvolucion) pokemon).getSiguienteEvolucion() + "," + ((pokemonSegundaEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
        }

    }
    @Override
    public void DesplegarPokemonDependiendoTipo(){
        //se le pide al usuario ingresar el tipo de pokemon que desea buscar
        System.out.println("Ingrese el tipo de pokemon que desea buscar:");
        String tipoABuscar=StdIn.readLine();
        //se verifica que el tipo ingresado corresponda a los tipos de pokemons presentes en el archivo.txt
        if (tipoABuscar.equalsIgnoreCase("normal") || tipoABuscar.equalsIgnoreCase("fantasma")|| tipoABuscar.equalsIgnoreCase("veneno") || tipoABuscar.equalsIgnoreCase("fuego")|| tipoABuscar.equalsIgnoreCase("electrico") || tipoABuscar.equalsIgnoreCase("planta")|| tipoABuscar.equalsIgnoreCase("insecto")||tipoABuscar.equalsIgnoreCase("volador")||tipoABuscar.equalsIgnoreCase("hierba")||tipoABuscar.equalsIgnoreCase("agua")){
            //creacion listas y metodos utlizados para los despliegues de informacion
            ArrayList<pokemon> rangoPokemon =new ArrayList<>();
            Iterator<pokemon> iterator = this.listaPokemons.iterator();

            while(iterator.hasNext()){
                pokemon pokemon = iterator.next();
                rangoPokemon.add(pokemon);
            }
            Iterator<pokemon> iterator1 = rangoPokemon.iterator();
            while (iterator1.hasNext()){
                pokemon pokemon = iterator1.next();
                if (pokemon.getTipo1().equalsIgnoreCase(tipoABuscar) || pokemon.getTipo2().equalsIgnoreCase(tipoABuscar)){
                    switch (pokemon.getEtapa()){
                        case "Basico"->{
                            pokemonBasico auxPokemon= (pokemonBasico)pokemon;
                            if (auxPokemon.getSiguienteEvolucion()==null){
                                StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                            }
                            if ((auxPokemon.getSiguienteEvolucion() == null && (auxPokemon.getEvolucionPrevia() != null))){
                                StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonBasico) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                            }
                            if (auxPokemon.getSiguienteEvolucion()!=null && auxPokemon.getEvolucionPrevia()!=null){
                                StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonBasico) pokemon).getSiguienteEvolucion() + "," + ((pokemonBasico) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                            }
                        }
                        case "PrimeraEvolucion"->{
                            pokemonPrimeraEvolucion auxpokemon1=(pokemonPrimeraEvolucion) pokemon;
                            if(auxpokemon1.getSiguienteEvolucion()==null){
                                StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                            }
                            if(auxpokemon1.getSiguienteEvolucion()!=null){
                                StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getSiguienteEvolucion() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                            }
                        }
                        case "SegundaEvolucion"->{
                            pokemonSegundaEvolucion auxpokemon2 = (pokemonSegundaEvolucion) pokemon;
                            StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonSegundaEvolucion) pokemon).getSiguienteEvolucion() + "," + ((pokemonSegundaEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                        }
                    }
                    System.out.println("-----------------------------------------------------------------------------------------------");
                }

            }
        }else{
            System.out.println("El tipo ingresado no existe!");
        }
    }
    @Override
    public void DesplegarPrimerasEvolucionesDescreciente(){
        //creacion de listas y metodos utlizados para el despliegue
        ArrayList<pokemon> rangoPokemon =new ArrayList<>();
        Iterator<pokemon> iterator = this.listaPokemons.iterator();

        while(iterator.hasNext()){
            pokemon pokemon = iterator.next();
            rangoPokemon.add(pokemon);
        }
        Comparator<pokemon> comparator = new Comparator<pokemon>() {
            @Override
            public int compare(pokemon p1, pokemon p2) {
                return Integer.compare(p1.getId(), p2.getId());
            }
        };
        //logica del arraylist para realizar el ordemiento de los id y el reordenamiento para el despliegue de la lista en forma Decresiente
        Collections.sort(rangoPokemon, comparator);
        Collections.reverse(rangoPokemon);


        Iterator<pokemon> iterator1 = rangoPokemon.iterator();
        while (iterator1.hasNext()){
            pokemon pokemon = iterator1.next();
            // se compara a que tipo de primera evolucion corresponde el pokemon
            if (pokemon.getEtapa().equalsIgnoreCase("PrimeraEvolucion")){
                pokemonPrimeraEvolucion auxpokemon1=(pokemonPrimeraEvolucion) pokemon;
                if(auxpokemon1.getSiguienteEvolucion()==null){
                    StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                }
                if(auxpokemon1.getSiguienteEvolucion()!=null){
                    StdOut.println(pokemon.getId() + "," + pokemon.getNombre() + "," + pokemon.getEtapa() + "," + ((pokemonPrimeraEvolucion) pokemon).getSiguienteEvolucion() + "," + ((pokemonPrimeraEvolucion) pokemon).getEvolucionPrevia() + "," + pokemon.getTipo1() + "," + pokemon.getTipo2());
                }
                System.out.println("-----------------------------------------------------------------------------------------------");
            }
        }
    }
    @Override
    public void BusquedaPersonalizada(){
        //menu de opciones
        int opcion=0;
        while (opcion!= 3){
            System.out.println("Desea buscar el pokemon por Nombre o ID ");
            System.out.println("[1] Por Nombre");
            System.out.println("[2] Por ID");
            System.out.println("[3] Menu principal");
            System.out.println("Ingrese una opcion:");
            opcion=StdIn.readInt();

            switch (opcion){
                case 1->busquedaPorNombre();
                case 2->busquedaPorID();
                case 3->menuPrincipal();
                default -> System.out.println("Ingrese una opcion valida");
            }
        }
    }
    @Override
    public void busquedaPorNombre(){
        String pokemonABuscar;
        System.out.println("Ingrese el Nombre del pokemon que desea buscar  :");
        pokemonABuscar=StdIn.readLine();
        //se llama al subprograma encargado de buscar el nombre del pokemon ingresado
        listaPokemons.buscarPokemonPorNombre(pokemonABuscar);
    }
    @Override
    public void busquedaPorID(){
        int pokemonABuscar;
        System.out.println("Ingrese el Nombre del pokemon que desea buscar  :");
        pokemonABuscar=StdIn.readInt();
        // se llama al subprograma encargado de buscar al pokemon en relacion al ID ingresado
        listaPokemons.buscarPokemonPorID(pokemonABuscar);

    }
    @Override
    public void ReescribirTXT() {
        //logica de la reeinscripcion del archivo .txt
        try (BufferedReader br = new BufferedReader(new FileReader("kanto.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("kantoOrdenado.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replaceAll("\\s+", "");
                if (!linea.isEmpty()) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void leerPokemons() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader((new FileReader("kantoOrdenado.txt")));
            String linea = bufferedReader.readLine();
            while (linea != null) {
                String[] pokemons = linea.split(",");
                //if para la comparacion de la linea obtenida y creando el pokemon en cada caso correspodiente , en este caso si pertenece a un pokemon basico , primera evolucion o segunda evolucion
                if (pokemons[2].equalsIgnoreCase("basico") && pokemons.length == 5) {
                    int id = Integer.parseInt(pokemons[0]);
                    String nombre = pokemons[1];
                    String etapa = pokemons[2];
                    String tipo1 = pokemons[3];
                    String tipo2 = pokemons[4];
                    pokemonBasico basico5 = new pokemonBasico(id, nombre, etapa, "", "", tipo1, tipo2);
                    this.listaPokemons.agregar(basico5);

                }
                if (pokemons[2].equalsIgnoreCase("basico") && pokemons.length == 6) {
                    int id = Integer.parseInt(pokemons[0]);
                    String nombre = pokemons[1];
                    String etapa = pokemons[2];
                    String siguienteEvolucion = pokemons[3];
                    String tipo1 = pokemons[4];
                    String tipo2 = pokemons[5];
                    pokemonBasico basico6 = new pokemonBasico(id, nombre, etapa, siguienteEvolucion, "", tipo1, tipo2);
                    this.listaPokemons.agregar(basico6);

                }
                if (pokemons[2].equalsIgnoreCase("basico") && pokemons.length == 7) {
                    int id = Integer.parseInt(pokemons[0]);
                    String nombre = pokemons[1];
                    String etapa = pokemons[2];
                    String siguienteEvolucion = pokemons[3];
                    String evolucionPrevia = pokemons[4];
                    String tipo1 = pokemons[5];
                    String tipo2 = pokemons[6];
                    pokemonBasico basico7 = new pokemonBasico(id, nombre, etapa, siguienteEvolucion, evolucionPrevia, tipo1, tipo2);
                    this.listaPokemons.agregar(basico7);
                }
                if (pokemons[1].equalsIgnoreCase("Eevee")) {
                    int id = Integer.parseInt(pokemons[0]);
                    String nombre = pokemons[1];
                    String etapa = pokemons[2];
                    String evolucion1 = pokemons[3];
                    String evolucion2 = pokemons[4];
                    String evolucion3 = pokemons[5];
                    String tipo1 = pokemons[6];
                    String tipo2 = pokemons[7];
                    pokemonBasico basicoEevee = new pokemonBasico(id, nombre, etapa, evolucion1 + "," + evolucion2, evolucion3, tipo1, tipo2);
                    this.listaPokemons.agregar(basicoEevee);
                }
                if (pokemons[2].equalsIgnoreCase("primeraevolucion") && pokemons.length == 6) {
                    int id = Integer.parseInt(pokemons[0]);
                    String nombre = pokemons[1];
                    String etapa = pokemons[2];
                    String evolucionPrevia = pokemons[3];
                    String tipo1 = pokemons[4];
                    String tipo2 = pokemons[5];
                    pokemonPrimeraEvolucion primeraEv6 = new pokemonPrimeraEvolucion(id, nombre, etapa, "", evolucionPrevia, tipo1, tipo2);
                    this.listaPokemons.agregar(primeraEv6);
                }
                if (pokemons[2].equalsIgnoreCase("primeraevolucion") && pokemons.length == 7) {
                    int id = Integer.parseInt(pokemons[0]);
                    String nombre = pokemons[1];
                    String etapa = pokemons[2];
                    String siguienteEvolucion = pokemons[3];
                    String evolucionPrevia = pokemons[4];
                    String tipo1 = pokemons[5];
                    String tipo2 = pokemons[6];
                    pokemonPrimeraEvolucion primeraEv7 = new pokemonPrimeraEvolucion(id, nombre, etapa, siguienteEvolucion, evolucionPrevia, tipo1, tipo2);
                    this.listaPokemons.agregar(primeraEv7);
                }
                if (pokemons[2].equalsIgnoreCase("segundaevolucion")) {
                    int id = Integer.parseInt(pokemons[0]);
                    String nombre = pokemons[1];
                    String etapa = pokemons[2];
                    String siguienteEvolucion = pokemons[3];
                    String evolucionPrevia = pokemons[4];
                    String tipo1 = pokemons[5];
                    String tipo2 = pokemons[6];
                    pokemonSegundaEvolucion segundaEv = new pokemonSegundaEvolucion(id, nombre, etapa, siguienteEvolucion, evolucionPrevia, tipo1, tipo2);
                    this.listaPokemons.agregar(segundaEv);
                }
                linea = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}



