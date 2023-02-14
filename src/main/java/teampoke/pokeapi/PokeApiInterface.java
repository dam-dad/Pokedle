package teampoke.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import teampoke.model.PokemonApi;

public interface PokeApiInterface {

	@GET("pokemon/{name}")
	public Call<PokemonApi> getPokemonInfo(@Path("name") String name);

	@GET("pokemon/{pokemonId}")
	public Call<PokemonApi> getPokemonById(@Path("pokemonId") int id);

//		@GET("pokemon-species/{evoId}")
//		public Call<PokemonSpecies> getEvolId(@Path("evoId") int id);
}
