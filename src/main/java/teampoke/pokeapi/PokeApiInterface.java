package teampoke.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import teampoke.pokeapi.model.EvolutionChain;
import teampoke.pokeapi.model.PokemonApi;
import teampoke.pokeapi.model.PokemonSpecies;

public interface PokeApiInterface {

	@GET("pokemon/{name}")
	public Call<PokemonApi> getPokemonInfo(@Path("name") String name);

	@GET("pokemon/{pokemonId}")
	public Call<PokemonApi> getPokemonById(@Path("pokemonId") int id);

	@GET("pokemon-species/{speId}")
	public Call<PokemonSpecies> getSpecies(@Path("speId") int id);

	@GET("evolution-chain/{chaId}")
	public Call<EvolutionChain> getChain(@Path("chaId") int id);
}
