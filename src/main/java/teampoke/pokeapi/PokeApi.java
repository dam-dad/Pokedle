package teampoke.pokeapi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teampoke.model.Pokemon;
import teampoke.model.PokemonApi;

public class PokeApi {
private PokeApiInterface service;
	
	public PokeApi() {
		
		ConnectionPool pool = new ConnectionPool(1,5,TimeUnit.SECONDS);
		
		OkHttpClient client = new OkHttpClient.Builder()
				.connectionPool(pool)
				.build();

		Retrofit retrofit = new Retrofit.Builder()
				.client(client)
				.baseUrl("https://pokeapi.co/api/v2/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		service = retrofit.create(PokeApiInterface.class);
		
	}
	
	
	public Pokemon getPokemon(String name) throws Exception {
		Pokemon pokemon = new Pokemon();
		Response<PokemonApi> response = service.getPokemonInfo(name).execute();
		if (response.code() != 200)
			throw new Exception(response.errorBody().string());
//		getEvo(response.body().getId());
		PokemonApi pokemonapi = response.body();
//		pokemonapi.ge
		
		
		return pokemon;
	}
	
	public PokemonApi getPokemonById(int pokemonId) throws IOException {
		Response<PokemonApi> response = service.getPokemonById(pokemonId).execute();
		return response.body();
	}

//	public PokemonSpecies getEvo(int pokemonId) throws IOException {
//		
//		Response<PokemonApi> response = service.getPokemonById(pokemonId).execute();
//		String[] parts = response.body().getSpecies().getUrl().split("/");
//		System.out.println(Arrays.asList(parts));
//		int id = Integer.parseInt(parts[parts.length-1]);
//		Response<PokemonSpecies> response2 = service.getEvolId(id).execute();
//		return response2.body();
//	}
	
	
	
}