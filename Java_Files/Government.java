package Top_Classes;

import java.util.HashMap;
import Top_Classes.Login;


public class Government extends Login {
    // Resource, Hashmap
    HashMap<String, Integer> cropsOwned = new HashMap<String, Integer>();
    double BankBalance;
    

    Government() {
        addCredentials("Govt", "apple123");
        cropsOwned.put("Wheat", 0);   //Initializing amount to 0
        cropsOwned.put("Rice", 0);
        cropsOwned.put("Maize", 0);
    }

    public boolean authenticate(String username, String password) {
        if ("Govt".equals(username)) {
            return "apple123".equals(password); 
        }
        return false;
    }

    public String getCropProductionReport(Farmer[] Farmers) {
        int totalWheat = 0;
        int totalRice = 0;
        int totalMaize = 0;

        
        for (Farmer farmer : Farmers) {
            if(farmer==null) continue;
            totalWheat += farmer.cropsOwned.getOrDefault("Wheat", 0);
            totalRice += farmer.cropsOwned.getOrDefault("Rice", 0);
            totalMaize += farmer.cropsOwned.getOrDefault("Maize", 0);
        }
        return "Wheat: "+totalWheat+" units\n"+"Rice: "+totalRice+" units\n"+"Maize: "+totalMaize+" units\n";
    }

    public String getResourceDistributionReport() {
        return "Resource Distribution:\n" + "\nCrops Owned: " + cropsOwned.toString();
    }
}