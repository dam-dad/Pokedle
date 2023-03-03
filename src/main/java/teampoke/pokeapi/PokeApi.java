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
	 * Funcion que obtiene los atributos de un pokemon a traves del nombre por la PokeApi
	 * Obtendremos TipoPrimario, TipoSecundario, Peso, Altura, Num Pokédex, Si evoluciona, Si tiene preEvolucion
	 * y la Vida Base del Pokemon
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Pokemon getPokemon(String name) throws Exception {
		Pokemon pokemon = new Pokemon();
		String tipoPrimario, tipoSecundario, url, s;
		int peso, altura, numPokedex, vida;
		Image img;
		String[] parts;

		Response<PokemonApi> response = service.getPokemonInfo(name).execute();
		if (response.code() != 200)
			throw new Exception(response.errorBody().string());
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

		// Preevolucion
		url = pokemonapi.getSpecies().getUrl();
		pokemon.setPreevoPokemon(getPreEvo(getIdUrl(url)));

		// Evolucion
		PokemonSpecies poSpecies = getPokemonSpecies(getIdUrl(url));
		s = poSpecies.getEvolTo().toString();
		url = s.substring(s.indexOf("=") + 1, s.length() - 1);

		getEvoluciones(getIdUrl(url), pokemon);

		pokemonapi.getStat().get(0).getBaseStat();

		return pokemon;
	}
	
	/**
	 * Le pasamos una url, y obtenemos la última parte sacada con split("/") que contiene
	 * un número entero necesario para hacer determinadas llamadas a la PokeApi
	 * 
	 * @param url
	 * @return int
	 */
	private int getIdUrl(String url) {
		int id;
		String[] parts;
		parts = url.split("/");
		id = Integer.parseInt(parts[parts.length - 1]);
		
		return id;
	}

	/**
	 * Funcion privada para obtener si el Pokemon tiene una evolución, a traves de la Api haremos una llamada
	 * y evaluamos las posibilidades, viendo el JSON se aprecia que devuelve la cadena completa de evoluciones de un Pokemon
	 * por lo que tendremos que descartar de lo que no estemos interesados
	 * 
	 * @param id
	 * @param pokemon
	 * @throws IOException
	 */
	private void getEvoluciones(int id, Pokemon pokemon) throws IOException {
		boolean evoluciona = false;
		Response<EvolutionChain> response = service.getChain(id).execute();
		EvolutionChain evolutionChain = response.body();
		ArrayList<String> evoluciones = new ArrayList<>();
		ArrayList<Integer> evolucionesID = new ArrayList<>();

		if (evolutionChain != null && evolutionChain.getChain().getEvolvesTo().size() > 0) {

			if (evolutionChain.getChain().getEvolvesTo().get(0).getEvolvesTo().isEmpty()) { // es una cadena de 2
																							// evoluciones

				for (int i = 0; i < evolutionChain.getChain().getEvolvesTo().size(); i++) {
					evoluciones.add(evolutionChain.getChain().getEvolvesTo().get(i).getSpecies().getName());
				}

				if (!evoluciones.contains(pokemon.getNombrePokemon())) {
					evoluciona = true;
					pokemon.setEvoPokemon(evoluciona);
				} else {
					evoluciona = false;
					pokemon.setEvoPokemon(evoluciona);
				}
			} else { // Cadena de 3 evoluciones

				for (int i = 0; i < evolutionChain.getChain().getEvolvesTo().get(0).getEvolvesTo().size(); i++) {
					evoluciones.add(evolutionChain.getChain().getEvolvesTo().get(0).getEvolvesTo().get(i).getSpecies()
							.getName());
				}

				if (!evoluciones.contains(pokemon.getNombrePokemon())) {
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
	}

	/**
	 * Devuelve un objeto PokemonSpecies necesario para averiguar las evoluciones del Pokemon
	 * 
	 * @param id
	 * @return PokemonSpecies
	 * @throws IOException
	 */
	private PokemonSpecies getPokemonSpecies(int id) throws IOException {
		Response<PokemonSpecies> response = service.getSpecies(id).execute();
		PokemonSpecies pokSpecies = response.body();
		return pokSpecies;
	}

	/**
	 * Devuelve si el Pokemon tiene preEvolución o no
	 * 
	 * @param id
	 * @return boolean
	 * @throws IOException
	 */
	private boolean getPreEvo(int id) throws IOException {
		boolean preevoluciona = false;
		Response<PokemonSpecies> response = service.getSpecies(id).execute();
		PokemonSpecies poSpecies = response.body();
		if (poSpecies.getEvolFrom() != null) {
			preevoluciona = true;
		}

		return preevoluciona;

	}

	/**
	 * Obtenemos las características de un Pokemon a través de su ID
	 * 
	 * @param pokemonId
	 * @return PokemonApi
	 * @throws IOException
	 */
	public PokemonApi getPokemonById(int pokemonId) throws IOException {
		Response<PokemonApi> response = service.getPokemonById(pokemonId).execute();
		return response.body();
	}

	/**
	 * Devuelve una lista con todos los Pokemons, actualmente está marcado solo hasta 3ª Generacion,
	 * pero se encuentra comentado la manera de hacerlo genérico con todos los Pokmeons que estén en la Api
	 * 
	 * @return ArrayList
	 * @throws IOException
	 */
	public ArrayList<String> getListPokemons() throws IOException {
		int maxPokemons = 386;
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

	/**
	 * Diccionario de los Tipo de Pokemon de Ingles a Español
	 * 
	 * @param type
	 * @return
	 */
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

	
	/**
	 * Hay que pulir esto, vía Api era muy confuso y por mas intentos que hicimos no conseguimos que funcionarse siempre
	 * por lo que no se considera su uso.
	 * Posibilidad de actualizar esta seccion
	 * 
	 * @param evoChain
	 * @return
	 * @deprecated
	 */
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