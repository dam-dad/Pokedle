package teampoke.pokeapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javafx.scene.image.Image;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teampoke.model.Pokemon;
import teampoke.pokeapi.model.EvolutionChain;
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
		int peso, altura, numPokedex;
		boolean evoluciona = false, preevoluciona = false;
		Image img;
		String[] parts;

		Response<PokemonApi> response = service.getPokemonInfo(name).execute();
		if (response.code() != 200)
			throw new Exception(response.errorBody().string());
		// getEvo(response.body().getId());
		PokemonApi pokemonapi = response.body();

		pokemon.setNombrePokemon(name);

		tipoPrimario = pokemonapi.getTypes().get(0).getType().getName();
		pokemon.setTipoPrimPokemon(tipoPrimario);

		if (pokemonapi.getTypes().size() > 1) {
			tipoSecundario = pokemonapi.getTypes().get(1).getType().getName();
			pokemon.setTipoSecPokemon(tipoSecundario);
		} else {
			tipoSecundario = null;
			pokemon.setTipoSecPokemon("NO");
		}

		peso = pokemonapi.getWeight();
		pokemon.setPesoPokemon(peso);

		altura = pokemonapi.getHeight();
		pokemon.setAlturaPokemon(altura);

		numPokedex = pokemonapi.getNumPokedex();
		pokemon.setNumPokemon(numPokedex);

		img = new Image(pokemonapi.getSprites().getFrontDefault().toString());
		pokemon.setImagenPokemon(img);

		parts = pokemonapi.getSpecies().getUrl().split("/");

		int id = Integer.parseInt(parts[parts.length - 1]);

		/*
		 * Tiene preevolucion y/o evolucion?
		 */
		Response<PokemonSpecies> response2 = service.getSpecies(id).execute();
		PokemonSpecies poSpecies = response2.body();
		if (poSpecies.getEvolFrom() != null) {
			preevoluciona = true;
			pokemon.setPreevoPokemon(preevoluciona);
		}

		if (poSpecies.getEvolTo() != null) {
			evoluciona = true;
			pokemon.setEvoPokemon(evoluciona);
		}

		/*
		 * Manera de evolucionar
		 */
		String s = poSpecies.getEvolTo().toString();

		String url = s.substring(s.indexOf("=") + 1, s.length() - 1);

		parts = url.split("/");
		id = Integer.parseInt(parts[parts.length - 1]);
		Response<EvolutionChain> response3 = service.getChain(id).execute();
		EvolutionChain evo = response3.body();

		System.out.println(maneraDeEvolucion(evo));

		// for (int i = 0; i <
		// evo.getChain().getEvolvesTo().get(0).getEvolutionDetails().size(); i++) {
		// System.out.println(evo.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getMinLevel());
		// }

		// chain.getEvolvesTo().get(0).getEvolutionDetails();

		return pokemon;
	}

	private String maneraDeEvolucion(EvolutionChain evoChain) {
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getGender();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getHeldItem();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getItem();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getKnownMove();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getKnownMoveType();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getLocation();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getMinAffection();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getMinBeauty();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getMinHappiness();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getMinLevel();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getNeedsOverworldRain();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getPartySpecies();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getPartyType();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getRelativePhysicalStats();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getTimeOfDay();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getTradeSpecies();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getTrigger();
		evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).getTurnUpsideDown();

		return evoChain.getChain().getEvolvesTo().get(0).getEvolutionDetails().get(0).toString();
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

}