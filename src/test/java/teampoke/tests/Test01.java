package teampoke.tests;

import java.util.ArrayList;

import teampoke.model.Pokemon;
import teampoke.pokeapi.PokeApi;

public class Test01 {

	public static void main(String[] args) throws Exception {
		ArrayList<String> listapokemons = new ArrayList<>();
		
		// globalApi
		PokeApi pokeapi = new PokeApi();
		Pokemon pokemon;
//		
		pokemon = pokeapi.getPokemon("charmander");
		System.out.println(pokemon);

		// listapokemons= pokeapi.getListPokemons();
		// for (String string : listapokemons) {
		// 	System.out.println(string);
		// }



	}
	
	
//	Pokemon pikachu = pokeapi.getPokemon("charmander");
//	System.out.println("Nombre :" + pikachu.getName());
//	System.out.println("Peso :" + pikachu.getWeight());
//	System.out.println("Altura :" + pikachu.getHeight());
//	System.out.println("Especies :" + pikachu.getSpecies().getName());
//	System.out.println("ID: " + pikachu.getId().toString());
//	System.out.println("Generacion :" + pikachu.);

//	System.out.println(pokeapi.getEvo(4));

//	System.out.println("tiene evo: " + pikachu.getPokemonSpecies().getEvolTo());
//	System.out.println("tiene pre-evo: " + pikachu.getPokemonSpecies().getEvolFrom());

//	int r = new Random().nextInt(151)+1;
//	Pokemon random = pokeapi.getPokemonById(2);
//	System.out.println("Nombre :" + random.getName());
//	
//	Pokemon r2 = pokeapi.getEvoId(r);
//	System.out.println(r2.getSprites().getFrontDefault());


}
