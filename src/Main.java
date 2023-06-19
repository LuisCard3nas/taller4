import services.Pokedex;
import ucn.StdIn;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Pokedex pokedex = new Pokedex();
        pokedex.ReescribirTXT();
        pokedex.leerPokemons();
        pokedex.menuPrincipal();
    }
}