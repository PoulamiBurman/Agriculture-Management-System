package Top_Classes;

import java.util.HashMap;

public class Shop {
    HashMap<String, Integer> cropsOwned = new HashMap<String, Integer>();
    HashMap<String, Double> PriceSeed = new HashMap<String, Double>();
    HashMap<String, Double> PriceCrop = new HashMap<String, Double>();

    Shop() {
        cropsOwned.put("Wheat", 5); // Initializing amount to 0
        cropsOwned.put("Rice", 5);
        cropsOwned.put("Maize", 5);

        PriceSeed.put("Wheat", 25.0); // Initializing price to
        PriceSeed.put("Rice", 120.0);
        PriceSeed.put("Maize", 250.0);

        PriceCrop.put("Wheat", 34.0); // Initializing price to
        PriceCrop.put("Rice", 55.0);
        PriceCrop.put("Maize", 22.8);
    }

    public void displayStock() {

        System.out.println("Wheat - " + cropsOwned.get("Wheat"));
        System.out.println("Rice - " + cropsOwned.get("Rice"));
        System.out.println("Maize - " + cropsOwned.get("Maize"));

    }

    public boolean buySeed(String seedName, int quantity, double balance) {

        double cost = PriceSeed.get(seedName) * quantity;
        // checking if seed is there
        if (cropsOwned.get(seedName) < quantity) {
            System.out.println("Not enough stock of " + seedName + " seeds.");
            return false;
        }
        // check balance of farmer
        if (cost > balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        cropsOwned.put(seedName, cropsOwned.get(seedName) - quantity);
        System.out.println("Purchased " + quantity + " units of " + seedName + " seeds for Rupees" + cost);
        return true;
    }

    public boolean buyCrop(String cropName, int quantity, double balance) {
        double cost = PriceCrop.get(cropName) * quantity;

        if (cropsOwned.get(cropName) < quantity) {
            System.out.println("Not enough stock of " + cropName + " crops.");
            return false;
        }

        if (cost > balance) {
            System.out.println("Insufficient funds.");
            return false;
        }

        cropsOwned.put(cropName, cropsOwned.get(cropName) - quantity);
        System.out.println("Purchased " + quantity + " units of " + cropName + " seeds for Rupees " + cost);
        return true;
    }

}

/*
 * 
 * // if is seed is put as false it is considered as a crop
 * public boolean buyFromShop(Shop shop, String itemName, int quantity, boolean
 * isSeed) {
 * double cost;
 * 
 * 
 * if (isSeed) {
 * cost = shop.PriceSeed.get(itemName) * quantity;
 * } else {
 * cost = shop.PriceCrop.get(itemName) * quantity;
 * }
 * 
 * 
 * boolean purchaseSuccessful;
 * if (isSeed) {
 * purchaseSuccessful = shop.buySeed(itemName, quantity, BankBalance);
 * } else {
 * purchaseSuccessful = shop.buyCrop(itemName, quantity, BankBalance);
 * }
 * 
 * if (purchaseSuccessful) {
 * BankBalance -= cost; // Deduct cost from Farmer's balance
 * receiveCrops(itemName, quantity);
 * System.out.println("Purchased " + quantity + " units of " + itemName +
 * ". Remaining balance: $" + BankBalance);
 * } else {
 * System.out.println("Purchase failed for " + quantity + " units of " +
 * itemName);
 * }
 * 
 * return purchaseSuccessful;
 * }
 */