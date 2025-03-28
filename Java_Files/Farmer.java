package Top_Classes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import Top_Classes.Login;
import Top_Classes.Crops;

public class Farmer extends Login {
    int farmerId;
    public String name;
    String region;
    String phoneNumber;
    LocalDate registrationDate;
    private double BankBalance;
    HashMap<String, Integer> cropsOwned = new HashMap<String, Integer>();
    public Crops farmCrop;
    // Resources resources;

    // Constructor
    public Farmer() {
    }

    public Farmer(String Username, String Password, String name, String region, String phoneNumber/*
                                                                                                   * , Resources
                                                                                                   * resources
                                                                                                   */) {
        addCredentials(Username, Password);
        Random r = new Random();
        this.farmerId = r.nextInt(10000, 4000000);
        this.name = name;
        this.region = region;
        this.phoneNumber = phoneNumber;
        this.registrationDate = LocalDate.now();
        // this.resources = resources;
        cropsOwned.put("Wheat", 0);
        cropsOwned.put("Rice", 0);
        cropsOwned.put("Maize", 0);
    }

    public void plant(String n) {
        farmCrop = new Crops(n);
        farmCrop.plant();
        cropsOwned.put(n, cropsOwned.get(n) + 1);
    }

    public boolean plantStat() {
        return farmCrop.Growing;
    }

    // multithreading to plant crops - check if this is how you wanteed to
    // implemented it

    // Plant crops
    // public void plantCrop(String cropName) {
    // if (resources.Quantity.get("Fertilizers") > 0) {
    // int amount = cropsOwned.getOrDefault(cropName, 0);
    // cropsOwned.put(cropName, amount + 1);
    // resources.Quantity.put("Fertilizers", resources.Quantity.get("Fertilizers") -
    // 1);
    // System.out.println(cropName + " has been planted. Total " + cropName + ": " +
    // cropsOwned.get(cropName));
    // } else {
    // System.out.println("Not enough resources to plant " + cropName);
    // }
    // }

    // public void waterCrops() {
    // if (resources.irrigationRating > 0) {
    // for (String cropName : cropsOwned.keySet()) {
    // if (cropsOwned.get(cropName) > 0) {
    // System.out.println("Watering " + cropName);
    // resources.irrigationRating--;
    // }
    // }
    // } else {
    // System.out.println("Not enough water to irrigate crops.");
    // }
    // }

    public void harvestCrops() {
        for (String cropName : cropsOwned.keySet()) {
            if (cropsOwned.get(cropName) > 0) {
                System.out.println("Harvesting " + cropName);
                cropsOwned.put(cropName, 0); // Reset crop count after harvest
            } else {
                System.out.println(cropName + " is not ready for harvest.");
            }
        }
    }

    public void receiveCrops(String cropName, int quantity) {
        int currentAmount = cropsOwned.getOrDefault(cropName, 0);
        cropsOwned.put(cropName, currentAmount + quantity);
        System.out.println(quantity + " units of " + cropName + " received. Total: " + cropsOwned.get(cropName));
    }

    public void setBankBalance(double bankBalance) {
        this.BankBalance = bankBalance;
    }

    public double getBankBalance() {
        return BankBalance;
    }

    // Method to check resources
    // public void checkResources() {
    // System.out.println("Resources that are Available:");
    // System.out.println("Machines: " + resources.Quantity.get("Machines"));
    // System.out.println("Fertilizers: " + resources.Quantity.get("Fertilizers"));
    // System.out.println("Irrigation Rating: " + resources.irrigationRating);
    // System.out.println("Resource Score: " + resources.Score);
    // }

    // Method to display farmer details
    public void displayFarmerDetails() {
        System.out.println("Farmer ID: " + farmerId);
        System.out.println("Name: " + name);
        System.out.println("Region: " + region);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Registration Date: " + registrationDate);
    }
}

// Use main for debugging
/*
 * public static void main(String[] args) {
 * Farmer f1 = new Farmer("Ashita", "Bangalore", "7019659332");
 * f1.displayFarmerDetails();
 * }
 */
/*
 *
 * 
 * cropsOwned.put("Wheat", 0); // Initializing crop amounts to 0
 * cropsOwned.put("Rice", 0);
 * cropsOwned.put("Maize", 0);
 * }
 * 
 * // Plant crops
 * public void plantCrop(String cropName) {
 * if (resources.Quantity.get("Fertilizers") > 0) {
 * int amount = cropsOwned.getOrDefault(cropName, 0);
 * cropsOwned.put(cropName, amount + 1);
 * resources.Quantity.put("Fertilizers", resources.Quantity.get("Fertilizers") -
 * 1); // Use fertilizer
 * System.out.println(cropName + " has been planted. Total " + cropName + ": " +
 * cropsOwned.get(cropName));
 * } else {
 * System.out.println("Not enough resources to plant " + cropName);
 * }
 * }
 * 
 * // Water crops
 * public void waterCrops() {
 * if (resources.irrigationRating > 0) {
 * for (String cropName : cropsOwned.keySet()) {
 * if (cropsOwned.get(cropName) > 0) {
 * System.out.println("Watering " + cropName);
 * resources.irrigationRating--;
 * }
 * }
 * } else {
 * System.out.println("Not enough water to irrigate crops.");
 * }
 * }
 * 
 * // Harvest crops
 * public void harvestCrops() {
 * for (String cropName : cropsOwned.keySet()) {
 * if (cropsOwned.get(cropName) > 0) {
 * System.out.println("Harvesting " + cropName);
 * cropsOwned.put(cropName, 0); // Reset crop count after harvest
 * } else {
 * System.out.println(cropName + " is not ready for harvest.");
 * }
 * }
 * }
 * 
 * // Farmer receives crops (new method)
 * public void receiveCrops(String cropName, int quantity) {
 * int currentAmount = cropsOwned.getOrDefault(cropName, 0);
 * cropsOwned.put(cropName, currentAmount + quantity);
 * System.out.println(quantity + " units of " + cropName + " received. Total: "
 * + cropsOwned.get(cropName));
 * }
 * 
 * // Check resources
 * public void checkResources() {
 * System.out.println("Resources that are Available:");
 * System.out.println("Machines: " + resources.Quantity.get("Machines"));
 * System.out.println("Fertilizers: " + resources.Quantity.get("Fertilizers"));
 * System.out.println("Irrigation Rating: " + resources.irrigationRating);
 * System.out.println("Resource Score: " + resources.Score);
 * }
 * 
 * // Display farmer details
 * public void displayFarmerDetails() {
 * System.out.println("Farmer ID: " + farmerId);
 * System.out.println("Name: " + name);
 * System.out.println("Region: " + region);
 * System.out.println("Phone Number: " + phoneNumber);
 * System.out.println("Registration Date: " + registrationDate);
 * }
 * 
 * // Use main for debugging
 * /*
 * public static void main(String[] args) {
 * Farmer f1 = new Farmer("Ashita", "Bangalore", "7019659332", new Resources());
 * f1.displayFarmerDetails();
 * f1.plantCrop("Wheat");
 * f1.receiveCrops("Rice", 5);
 * f1.waterCrops();
 * f1.harvestCrops();
 * f1.checkResources();
 * }
 */
