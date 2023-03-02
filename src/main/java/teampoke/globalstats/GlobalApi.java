package teampoke.globalstats;

import java.util.HashMap;

import io.github.fvarrui.globalstats.GlobalStats;
import io.github.fvarrui.globalstats.model.Stats;

public class GlobalApi {

	private final static String C_SECRET = "nmNAhy6mUjGa1vrslmThFDOV7PUElzhIV4jdDqzx";
	private final static String C_ID = "jBVJuJ2uF7RH9dVLv4ldkgHOhrQUrVjKwfu1ZwwE";
	private static GlobalStats client = new GlobalStats(C_ID, C_SECRET);

//	public static void addPuntuacion(String nameUser, Number value) {
//		try {
//			Stats stats = client.createStats(nameUser, new HashMap<String, Number>() {
//				{
//					put("score", value);
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
