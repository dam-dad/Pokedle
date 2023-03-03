package teampoke.tests;

import java.util.List;

import teampoke.globalstats.GlobalApi;
import teampoke.globalstats.MarcadorPersonal;
import teampoke.model.Puntuacion;

public class Test01 {

	public static void main(String[] args) throws Exception {
//		ArrayList<String> listapokemons = new ArrayList<>();
//		
//		// globalApi
//		PokeApi pokeapi = new PokeApi();
//		Pokemon pokemon;
////		
//		pokemon = pokeapi.getPokemon("charmander");
//		System.out.println(pokemon);

		// listapokemons= pokeapi.getListPokemons();
		// for (String string : listapokemons) {
		// 	System.out.println(string);
		// }

//		Puntuacion po = new Puntuacion();
//		
//		System.out.println(po);
//		System.out.println(po.sumarPuntos(1));
//		po.setPuntuacion(40);
//		System.out.println(po);
//		
//		GlobalApi.addPuntuacion("Cacatua", po.getPuntuacion());
		List<MarcadorPersonal> leaderboard = GlobalApi.puntuaciones();
		System.out.println(leaderboard);

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
