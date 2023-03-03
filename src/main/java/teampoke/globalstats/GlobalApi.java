package teampoke.globalstats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.fvarrui.globalstats.GlobalStats;
import io.github.fvarrui.globalstats.model.Rank;
import io.github.fvarrui.globalstats.model.Stats;

public class GlobalApi {

	private final static String C_SECRET = "nmNAhy6mUjGa1vrslmThFDOV7PUElzhIV4jdDqzx";
	private final static String C_ID = "jBVJuJ2uF7RH9dVLv4ldkgHOhrQUrVjKwfu1ZwwE";
	public static GlobalStats client = new GlobalStats(C_ID, C_SECRET);

	public static void addPuntuacion(String nameUser, Number value) {
		
		try {
			Stats stats = client.createStats(nameUser, new HashMap<String, Object>() {
				{
					put("score", value);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<MarcadorPersonal> puntuaciones() throws Exception{
		List<Rank> ranking = client.getLeaderboard("score", 10); 
		List<MarcadorPersonal> rankingFiltrado = new ArrayList<>();
		for (int i = 0; i < ranking.size(); i++) {
			rankingFiltrado.add(new MarcadorPersonal(ranking.get(i).getName(), ranking.get(i).getValue()));			
		}
		return rankingFiltrado;
	}
	
}