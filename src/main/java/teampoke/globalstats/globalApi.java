package teampoke.globalstats;

import io.github.fvarrui.globalstats.GlobalStats;
import io.github.fvarrui.globalstats.*;


public class GlobalApi {
    
    private final static String C_SECRET = "nmNAhy6mUjGa1vrslmThFDOV7PUElzhIV4jdDqzx";
    private final static String C_ID = "jBVJuJ2uF7RH9dVLv4ldkgHOhrQUrVjKwfu1ZwwE";
    public static GlobalStats client = new GlobalStats(C_ID, C_SECRET);

    
    public void addPuntuacion(String nameUser, Number value){
        // Stats stats = client.create
    }


}
