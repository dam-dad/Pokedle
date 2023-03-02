package teampoke.pokeapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javafx.scene.image.Image;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teampoke.model.Pokemon;
import teampoke.pokeapi.model.EvolutionChain;
import teampoke.pokeapi.model.EvolutionDetail;
import teampoke.pokeapi.model.ListPokemon;
import teampoke.pokeapi.model.PokemonApi;
import teampoke.pokeapi.model.PokemonSpecies;
import teampoke.pokeapi.model.Species;

public class PokeApi {
	private PokeApiInterface service;

	public PokeApi() {

		ConnectionPool pool = new ConnectionPool(1, 5, TimeUnit.SECONDS);

		OkHttpClient client = new OkHttpClient.Builder().connectionPool(pool).build();

		Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl("https://pokeapi.co/api/v2/")
				.addConverterFactory(GsonConverterFactory.create()).build();

		service = retrofit.create(PokeApiInterface.class);

	}

	/**
	 * Funcion que obtiene el pokemon a traves del nombre por la api
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Pokemon getPokemon(String name) throws Exception {
		Pokemon pokemon = new Pokemon();
		String tipoPrimario, tipoSecundario;
		int peso, altura, numPokedex, vida;
		boolean evoluciona = false, preevoluciona = false;
		Image img;
		String[] parts;

		Response<PokemonApi> response = service.getPokemonInfo(name).execute();
		if (response.code() != 200)
			throw new Exception(response.errorBody().string());
		// getEvo(response.body().getId());
		PokemonApi pokemonapi = response.body();

		pokemon.setNombrePokemon(name);

		tipoPrimario = tipoEnEspañol(pokemonapi.getTypes().get(0).getType().getName());
		pokemon.setTipoPrimPokemon(tipoPrimario);

		if (pokemonapi.getTypes().size() > 1) {
			tipoSecundario = tipoEnEspañol(pokemonapi.getTypes().get(1).getType().getName());
			pokemon.setTipoSecPokemon(tipoSecundario);
		} else {
			tipoSecundario = null;
			pokemon.setTipoSecPokemon("No tiene");
		}

		peso = pokemonapi.getWeight();
		pokemon.setPesoPokemon(peso);

		altura = pokemonapi.getHeight();
		pokemon.setAlturaPokemon(altura);

		numPokedex = pokemonapi.getNumPokedex();
		pokemon.setNumPokemon(numPokedex);
		
		vida = pokemonapi.getStat().get(0).getBaseStat();
		pokemon.setVidaBasePokemon(vida);

		img = new Image(pokemonapi.getSprites().getFrontDefault().toString());
		pokemon.setImagenPokemon(img);

		parts = pokemonapi.getSpecies().getUrl().split("/");

		int id = Integer.parseInt(parts[parts.length - 1]);

		/*
		 * Tiene preevolucion 
		 */
		Response<PokemonSpecies> response2 = service.getSpecies(id).execute();
		PokemonSpecies poSpecies = response2.body();
		if (poSpecies.getEvolFrom() != null) {
			preevoluciona = true;
			pokemon.setPreevoPokemon(preevoluciona);
		}
		

		/*
		 * Evolucion
		 */
		String s = poSpecies.getEvolTo().toString();

		String url = s.substring(s.indexOf("=") + 1, s.length() - 1);

		parts = url.split("/");
		id = Integer.parseInt(parts[parts.length - 1]);
		Response<EvolutionChain> response3 = service.getChain(id).execute();
		EvolutionChain evo = response3.body();
		ArrayList<String> evoluciones = new ArrayList<>();
		ArrayList<Integer> evolucionesID = new ArrayList<>();

		if (evo != null && evo.getChain().getEvolvesTo().size() > 0){ 

			
			
			if(evo.getChain().getEvolvesTo().get(0).getEvolvesTo().isEmpty()) { // es una cadena de 2 evoluciones	
				
				for (int i = 0; i < evo.getChain().getEvolvesTo().size(); i++) {
					evoluciones.add(evo.getChain().getEvolvesTo().get(i).getSpecies().getName());
				}
				
				if(!evoluciones.contains(pokemon.getNombrePokemon()) ) {
					evoluciona = true;
					pokemon.setEvoPokemon(evoluciona);
				} else {
					evoluciona = false;
					pokemon.setEvoPokemon(evoluciona);
				}
			} else { // Cadena de 3 evoluciones
				
				for (int i = 0; i < evo.getChain().getEvolvesTo().get(0).getEvolvesTo().size(); i++) {
					evoluciones.add(evo.getChain().getEvolvesTo().get(0).getEvolvesTo().get(i).getSpecies().getName());					
				}
				
				if(!evoluciones.contains(pokemon.getNombrePokemon()) ) {
					evoluciona = true;
					pokemon.setEvoPokemon(evoluciona);
				} else {
					evoluciona = false;
					pokemon.setEvoPokemon(evoluciona);
				}
			}
			
		} else {
			evoluciona = false;
			pokemon.setEvoPokemon(evoluciona);
		}
		
		//AQUI TIENES LA VIDA BASE
		pokemonapi.getStat().get(0).getBaseStat();

		return pokemon;
	}

	public PokemonApi getPokemonById(int pokemonId) throws IOException {
		Response<PokemonApi> response = service.getPokemonById(pokemonId).execute();
		return response.body();
	}

	public ArrayList<String> getListPokemons() throws IOException {
		int maxPokemons = 1008;
		ListPokemon listPokemon;
		ArrayList<String> arrayListaPokemons = new ArrayList<>();

		// Para automatizar coger el listado completo
		// Response<ListPokemon> response = service.getListPokemon(1).execute();

		Response<ListPokemon> response = service.getListPokemon(maxPokemons).execute();
		listPokemon = response.body();
		for (int i = 0; i < maxPokemons; i++) {
			arrayListaPokemons.add(listPokemon.getPokemons().get(i).getName());
		}
		return arrayListaPokemons;
	}

	private String tipoEnEspañol(String type) {
		Map<String, String> tipoEnEspañol = new HashMap<>();
		tipoEnEspañol.put("normal", "normal");
		tipoEnEspañol.put("fighting", "lucha");
		tipoEnEspañol.put("flying", "volador");
		tipoEnEspañol.put("poison", "veneno");
		tipoEnEspañol.put("ground", "tierra");
		tipoEnEspañol.put("rock", "roca");
		tipoEnEspañol.put("bug", "bicho");
		tipoEnEspañol.put("ghost", "fantasma");
		tipoEnEspañol.put("steel", "acero");
		tipoEnEspañol.put("fire", "fuego");
		tipoEnEspañol.put("water", "agua");
		tipoEnEspañol.put("grass", "planta");
		tipoEnEspañol.put("electric", "eléctrico");
		tipoEnEspañol.put("psychic", "psíquico");
		tipoEnEspañol.put("ice", "hielo");
		tipoEnEspañol.put("dragon", "dragón");
		tipoEnEspañol.put("dark", "siniestro");
		tipoEnEspañol.put("fairy", "hada");

		return tipoEnEspañol.get(type);
	}

	private String maneraDeEvolucion(EvolutionChain evoChain) {
		EvolutionDetail ed = evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0);

		if (!(ed.getGender() == null))
			ed.getGender();
		ed.getHeldItem();
		ed.getItem();
		ed.getKnownMove();
		ed.getKnownMoveType();
		ed.getLocation();
		ed.getMinAffection();
		ed.getMinBeauty();
		ed.getMinHappiness();
		ed.getMinLevel();
		ed.getNeedsOverworldRain();
		ed.getPartySpecies();
		ed.getPartyType();
		ed.getRelativePhysicalStats();
		ed.getTimeOfDay();
		ed.getTradeSpecies();
		ed.getTrigger();
		ed.getTurnUpsideDown();

		return evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).toString();
	}

}