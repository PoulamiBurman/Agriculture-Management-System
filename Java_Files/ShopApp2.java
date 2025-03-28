package Top_Classes;

import Top_Classes.Farmer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;

public class ShopApp2 {
    private HashMap<String, Integer> cropsOwned = new HashMap<>();
    private HashMap<String, Double> priceSeed = new HashMap<>();
    private HashMap<String, Double> priceCrop = new HashMap<>();
    private Label stockLabel = new Label();
    private Label feedbackLabel = new Label();

    private Farmer farmer;

    public ShopApp2(Farmer farmer) {
        this.farmer = farmer;
        initializeShop();
    }

    private void initializeShop() {
        cropsOwned.put("Wheat", 5);
        cropsOwned.put("Rice", 5);
        cropsOwned.put("Maize", 5);

        priceSeed.put("Wheat", 25.0);
        priceSeed.put("Rice", 120.0);
        priceSeed.put("Maize", 250.0);

        priceCrop.put("Wheat", 34.0);
        priceCrop.put("Rice", 55.0);
        priceCrop.put("Maize", 22.8);
    }

    
    public Scene getShopScene(Stage primaryStage, Menus menus) {
        // Title and labels
        Label titleLabel = new Label("Seed and Crop Shop");
        Label seedLabel = new Label("Seed/Crop Name:");
        Label quantityLabel = new Label("Quantity:");
    
        // Input fields
        TextField seedNameField = new TextField();
        seedNameField.setPromptText("Enter seed or crop name");
        TextField seedQuantityField = new TextField();
        seedQuantityField.setPromptText("Enter quantity");
    
        // Buttons
        Button buySeedButton = new Button("Buy Seed");
        Button buyCropButton = new Button("Buy Crop");
        Button backButton = new Button("Back");
    
        // Event handlers
        buySeedButton.setOnAction(e -> {
            String seedName = seedNameField.getText();
            int quantity = Integer.parseInt(seedQuantityField.getText());
            boolean success = buySeed(seedName, quantity);
            feedbackLabel.setText(success ? "Purchase successful!" : "Purchase failed.");
            displayStock();
        });
    
        buyCropButton.setOnAction(e -> {
            String cropName = seedNameField.getText();
            int quantity = Integer.parseInt(seedQuantityField.getText());
            boolean success = buyCrop(cropName, quantity);
            feedbackLabel.setText(success ? "Purchase successful!" : "Purchase failed.");
            displayStock();
        });
    
        backButton.setOnAction(e -> primaryStage.setScene(menus.FarmerMenuScene()));
    
        // Display initial stock information
        displayStock();
    
        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
    
        // Adding components to the grid
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(seedLabel, 0, 1);
        grid.add(seedNameField, 1, 1);
        grid.add(quantityLabel, 0, 2);
        grid.add(seedQuantityField, 1, 2);
        grid.add(buySeedButton, 0, 3);
        grid.add(buyCropButton, 1, 3);
        grid.add(backButton, 0, 5, 2, 1);
        grid.add(stockLabel, 0, 6, 2, 1);
        grid.add(feedbackLabel, 0, 7, 2, 1);
    
        // Return the constructed scene
        return new Scene(grid, 400, 500);
    }
    

    private void displayStock() {
        stockLabel.setText("Stock:\n" +
                "Wheat - " + cropsOwned.get("Wheat") + "\n" +
                "Rice - " + cropsOwned.get("Rice") + "\n" +
                "Maize - " + cropsOwned.get("Maize") + "\n" +
                "Balance: ₹" + farmer.getBankBalance());
    }

    public boolean buySeed(String seedName, int quantity) {
        Double cost = priceSeed.get(seedName);
        if (cost == null)
            return false;
        cost *= quantity;

        if (cropsOwned.get(seedName) < quantity) {
            feedbackLabel.setText("Not enough stock of " + seedName + " seeds.");
            return false;
        }

        if (cost > farmer.getBankBalance()) {
            feedbackLabel.setText("Insufficient funds.");
            return false;
        }

        farmer.setBankBalance(farmer.getBankBalance() - cost);
        cropsOwned.put(seedName, cropsOwned.get(seedName) - quantity);
        return true;
    }

    public boolean buyCrop(String cropName, int quantity) {
        Double cost = priceCrop.get(cropName);
        if (cost == null)
            return false;
        cost *= quantity;

        if (cropsOwned.get(cropName) < quantity) {
            feedbackLabel.setText("Not enough stock of " + cropName + " crops.");
            return false;
        }

        if (cost > farmer.getBankBalance()) {
            feedbackLabel.setText("Insufficient funds.");
            return false;
        }

        farmer.setBankBalance(farmer.getBankBalance() - cost);
        cropsOwned.put(cropName, cropsOwned.get(cropName) - quantity);
        return true;
    }
}
