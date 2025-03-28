
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;

class Farmer {
    private String username;
    private String password;
    private String name;
    private String location;
    private String phone;
    private double bankBalance;

    public Farmer(String username, String password, String name, String location, String phone, double bankBalance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.bankBalance = bankBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", bankBalance=" + bankBalance +
                '}';
    }
}

public class ShopApp extends Application {
    private HashMap<String, Integer> cropsOwned = new HashMap<>();
    private HashMap<String, Double> priceSeed = new HashMap<>();
    private HashMap<String, Double> priceCrop = new HashMap<>();
    private Label stockLabel = new Label();
    private Label feedbackLabel = new Label();

    private Farmer farmer;

    public ShopApp(Farmer farmer) {
        this.farmer = farmer;

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

    public ShopApp() {
        this.farmer = new Farmer("john_doe", "password123", "John Doe", "Punjab", "1234567890", 1000.0);

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

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Shop Application");

        Scene scene = createShopScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene createShopScene() {
        TextField seedNameField = new TextField();
        seedNameField.setPromptText("Enter seed name");
        TextField seedQuantityField = new TextField();
        seedQuantityField.setPromptText("Enter quantity");

        Button buySeedButton = new Button("Buy Seed");
        buySeedButton.setOnAction(e -> {
            String seedName = seedNameField.getText();
            int quantity = Integer.parseInt(seedQuantityField.getText());
            boolean success = buySeed(seedName, quantity);
            feedbackLabel.setText(success ? "Purchase successful!" : "Purchase failed.");
            displayStock();
        });

        Button buyCropButton = new Button("Buy Crop");
        buyCropButton.setOnAction(e -> {
            String cropName = seedNameField.getText();
            int quantity = Integer.parseInt(seedQuantityField.getText());
            boolean success = buyCrop(cropName, quantity);
            feedbackLabel.setText(success ? "Purchase successful!" : "Purchase failed.");
            displayStock();
        });

        displayStock();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(
                new Label("Seed/Crop Name:"), seedNameField,
                new Label("Quantity:"), seedQuantityField,
                buySeedButton, buyCropButton,
                stockLabel, feedbackLabel);

        return new Scene(layout, 300, 400);
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

    public static void main(String[] args) {
        Farmer farmer = new Farmer("john_doe", "password123", "John Doe", "Punjab", "1234567890", 1000.0);
        ShopApp shop = new ShopApp(farmer);
        launch(args);
    }
}

// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.stage.Stage;
// import java.util.HashMap;

// class Farmer {
// private String username;
// private String password;
// private String name;
// private String location;
// private String phone;
// private double bankBalance;

// public Farmer(String username, String password, String name, String location,
// String phone, double bankBalance) {
// this.username = username;
// this.password = password;
// this.name = name;
// this.location = location;
// this.phone = phone;
// this.bankBalance = bankBalance;
// }

// public String getUsername() {
// return username;
// }

// public void setUsername(String username) {
// this.username = username;
// }

// public String getPassword() {
// return password;
// }

// public void setPassword(String password) {
// this.password = password;
// }

// public String getName() {
// return name;
// }

// public void setName(String name) {
// this.name = name;
// }

// public String getLocation() {
// return location;
// }

// public void setLocation(String location) {
// this.location = location;
// }

// public String getPhone() {
// return phone;
// }

// public void setPhone(String phone) {
// this.phone = phone;
// }

// public double getBankBalance() {
// return bankBalance;
// }

// public void setBankBalance(double bankBalance) {
// this.bankBalance = bankBalance;
// }

// @Override
// public String toString() {
// return "Farmer{" +
// "username='" + username + '\'' +
// ", name='" + name + '\'' +
// ", location='" + location + '\'' +
// ", phone='" + phone + '\'' +
// ", bankBalance=" + bankBalance +
// '}';
// }
// }

// public class ShopApp extends Application {
// private HashMap<String, Integer> cropsOwned = new HashMap<>();
// private HashMap<String, Double> priceSeed = new HashMap<>();
// private HashMap<String, Double> priceCrop = new HashMap<>();
// private Label stockLabel = new Label();
// private Label feedbackLabel = new Label();

// private Farmer farmer;

// public ShopApp(Farmer farmer) {
// this.farmer = farmer;

// cropsOwned.put("Wheat", 5);
// cropsOwned.put("Rice", 5);
// cropsOwned.put("Maize", 5);

// priceSeed.put("Wheat", 25.0);
// priceSeed.put("Rice", 120.0);
// priceSeed.put("Maize", 250.0);

// priceCrop.put("Wheat", 34.0);
// priceCrop.put("Rice", 55.0);
// priceCrop.put("Maize", 22.8);
// }

// public ShopApp() {

// this.farmer = new Farmer("john_doe", "password123", "John Doe", "Punjab",
// "1234567890", 1000.0);

// cropsOwned.put("Wheat", 5);
// cropsOwned.put("Rice", 5);
// cropsOwned.put("Maize", 5);

// priceSeed.put("Wheat", 25.0);
// priceSeed.put("Rice", 120.0);
// priceSeed.put("Maize", 250.0);

// priceCrop.put("Wheat", 34.0);
// priceCrop.put("Rice", 55.0);
// priceCrop.put("Maize", 22.8);
// }

// @Override
// public void start(Stage primaryStage) {
// primaryStage.setTitle("Shop Application");

// TextField seedNameField = new TextField();
// seedNameField.setPromptText("Enter seed name");
// TextField seedQuantityField = new TextField();
// seedQuantityField.setPromptText("Enter quantity");

// Button buySeedButton = new Button("Buy Seed");
// buySeedButton.setOnAction(e -> {
// String seedName = seedNameField.getText();
// int quantity = Integer.parseInt(seedQuantityField.getText());
// boolean success = buySeed(seedName, quantity);
// feedbackLabel.setText(success ? "Purchase successful!" : "Purchase failed.");
// displayStock();
// });

// Button buyCropButton = new Button("Buy Crop");
// buyCropButton.setOnAction(e -> {
// String cropName = seedNameField.getText();
// int quantity = Integer.parseInt(seedQuantityField.getText());
// boolean success = buyCrop(cropName, quantity);
// feedbackLabel.setText(success ? "Purchase successful!" : "Purchase failed.");
// displayStock();
// });

// displayStock();

// VBox layout = new VBox(10);
// layout.setPadding(new Insets(15));
// layout.getChildren().addAll(
// new Label("Seed/Crop Name:"), seedNameField,
// new Label("Quantity:"), seedQuantityField,
// buySeedButton, buyCropButton,
// stockLabel, feedbackLabel);

// Scene scene = new Scene(layout, 300, 400);
// primaryStage.setScene(scene);
// primaryStage.show();
// }

// private void displayStock() {
// stockLabel.setText("Stock:\n" +
// "Wheat - " + cropsOwned.get("Wheat") + "\n" +
// "Rice - " + cropsOwned.get("Rice") + "\n" +
// "Maize - " + cropsOwned.get("Maize") + "\n" +
// "Balance: ₹" + farmer.getBankBalance());
// }

// public boolean buySeed(String seedName, int quantity) {
// Double cost = priceSeed.get(seedName);
// if (cost == null)
// return false;
// cost *= quantity;

// if (cropsOwned.get(seedName) < quantity) {
// feedbackLabel.setText("Not enough stock of " + seedName + " seeds.");
// return false;
// }

// if (cost > farmer.getBankBalance()) {
// feedbackLabel.setText("Insufficient funds.");
// return false;
// }

// farmer.setBankBalance(farmer.getBankBalance() - cost);
// cropsOwned.put(seedName, cropsOwned.get(seedName) - quantity);
// return true;
// }

// public boolean buyCrop(String cropName, int quantity) {
// Double cost = priceCrop.get(cropName);
// if (cost == null)
// return false;
// cost *= quantity;

// if (cropsOwned.get(cropName) < quantity) {
// feedbackLabel.setText("Not enough stock of " + cropName + " crops.");
// return false;
// }

// if (cost > farmer.getBankBalance()) {
// feedbackLabel.setText("Insufficient funds.");
// return false;
// }

// farmer.setBankBalance(farmer.getBankBalance() - cost);
// cropsOwned.put(cropName, cropsOwned.get(cropName) - quantity);
// return true;
// }

// public static void main(String[] args) {

// Farmer farmer = new Farmer("john_doe", "password123", "John Doe", "Punjab",
// "1234567890", 1000.0);
// ShopApp shop = new ShopApp(farmer);
// launch(args);
// }

// }
