package Top_Classes;

import java.util.Scanner;
import Top_Classes.*;
import javafx.event.ActionEvent;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;



public class Menus extends Application{
    Scanner sc = new Scanner(System.in);
    Government Govt = new Government();
    static int Fcount = 0;
    static Farmer[] Farmers = new Farmer[3];
    private Stage primaryStage;
    private final FarmerSupportSystem system = new FarmerSupportSystem();

    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setScene(MainMenuScene());
        primaryStage.setTitle("Agriculture Management System");
        primaryStage.show();
    }

    public Scene MainMenuScene() {
        GridPane rootNode = new GridPane(); 
        rootNode.setHgap(10); 
        rootNode.setVgap(10); 
        rootNode.setAlignment(Pos.CENTER); 
    
        Label title = new Label("\t\t\tLogin");
        Button farmerBtn = new Button("Farmer Login");
        Button govtBtn = new Button("Government Login");
    
        farmerBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(FarmerSignInScene(false));
            }
        });
    
        govtBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(GovtLoginScene());
            }
        });
    
        rootNode.add(title, 0, 0, 2, 1); 
        rootNode.add(govtBtn, 0, 1); 
        rootNode.add(farmerBtn, 1, 1); 
    
        Scene myScene = new Scene(rootNode, 500, 400);
        return myScene;
    }

    public Scene FarmerSignInScene(boolean regck){
        Label wlcm = new Label("Welcome");
        Label untxt = new Label("User name:");
        Label pwtxt = new Label("Password:");
        Button Loginbtn = new Button("Sign in");
        Button Regbtn = new Button("Register");
        Button Backbtn = new Button("Go Back");
        TextField tf = new TextField();
        Label response = new Label();
        PasswordField pf = new PasswordField();
        if(regck){
            response.setTextFill(Color.BLACK);
            response.setText("Registered!");
        }

        Backbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(MainMenuScene());
            }
        });
        Loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String unm = tf.getText();
                String pass = pf.getText();
                
                try {
                    int farmerIndex = authenticateFarmer(unm, pass);
                    primaryStage.setScene(FarmerMainMenuScene(farmerIndex));
                } catch (InvalidCredentialsException e) {
                    response.setText(e.getMessage());
                    response.setTextFill(Color.RED);
                }
            }
        });
        Regbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if(Fcount>=3){
                    response.setText("Farmer Limit Exceeded");
                    response.setTextFill(Color.RED);
                }
                else
                    primaryStage.setScene(FarmerRegScene());
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(wlcm, 0, 0);
        GridPane.setConstraints(untxt, 0, 1);
        GridPane.setConstraints(tf, 1, 1);
        GridPane.setConstraints(pwtxt, 0, 2);
        GridPane.setConstraints(pf, 1, 2);
        GridPane.setConstraints(response, 1, 0);
        GridPane.setConstraints(Backbtn, 0, 3);
        GridPane.setConstraints(Regbtn, 1, 3);
        GridPane.setConstraints(Loginbtn, 2, 3);
        grid.getChildren().addAll(wlcm,untxt,tf,pwtxt,pf,response,Backbtn,Regbtn,Loginbtn);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid,500,400);
        return scene;
    }

    private int authenticateFarmer(String username, String password) throws InvalidCredentialsException {
        for (int i = 0; i < Fcount; i++) {
            if (Farmers[i].authenticate(username, password)) {
                return i;
            }
        }
        throw new InvalidCredentialsException("Invalid Username/Password");
    }

    public Scene FarmerRegScene(){
        Label wlcm = new Label("Farmer Register");
        Label untxt = new Label("User name:");
        Label pwtxt = new Label("Password:");
        Label nmtxt = new Label("Name:");
        Label regtxt = new Label("Region:");
        Label pnotxt = new Label("Phone Number:");
        Button Regbtn = new Button("Register");
        Button Backbtn = new Button("Go Back");
        RadioButton option1 = new RadioButton("North");
        RadioButton option2 = new RadioButton("South");
        RadioButton option3 = new RadioButton("East");
        RadioButton option4 = new RadioButton("West");
        TextField untf = new TextField();
        TextField nmtf = new TextField();
        TextField pnotf = new TextField();
        Label response = new Label();
        PasswordField pf = new PasswordField();
        String unm,pw,nm,Pno;
        String[] details = {"","","","",""};

        ToggleGroup group = new ToggleGroup();
        option1.setToggleGroup(group);
        option2.setToggleGroup(group);
        option4.setToggleGroup(group);

        Backbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(FarmerSignInScene(false));
            }
        });
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selectedRadioButton = (RadioButton) newValue;
                    details[3] = selectedRadioButton.getText();
                }
            }
        });
        Regbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                details[0] = untf.getText();
                details[1] = pf.getText();
                details[2] = nmtf.getText();
                details[4] = pnotf.getText();
                Farmer F = new Farmer(details[0],details[1],details[2],details[3],details[4]);
                Farmers[Fcount++] = F;
                primaryStage.setScene(FarmerSignInScene(true));
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setConstraints(wlcm, 0, 0);
        GridPane.setConstraints(untxt, 0, 1);
        GridPane.setConstraints(untf, 1, 1);
        GridPane.setConstraints(pwtxt, 0, 2);
        GridPane.setConstraints(pf, 1, 2);
        GridPane.setConstraints(nmtxt, 0, 3);
        GridPane.setConstraints(nmtf, 1, 3);
        GridPane.setConstraints(regtxt, 0, 4);
        GridPane.setConstraints(option1, 1, 4);
        GridPane.setConstraints(option2, 2, 4);
        GridPane.setConstraints(option4, 3, 4);
        GridPane.setConstraints(pnotxt, 0, 5);
        GridPane.setConstraints(pnotf, 1, 5);
        GridPane.setConstraints(response, 1, 0);
        GridPane.setConstraints(Backbtn, 0, 6);
        GridPane.setConstraints(Regbtn, 1, 6);
        grid.getChildren().addAll(
    wlcm, untxt, untf, pwtxt, pf, nmtxt, nmtf, regtxt, option1, option2, option4, 
    pnotxt, pnotf, response, Backbtn, Regbtn
);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid,500,400);
        return scene;
    }

    public Scene FarmerMainMenuScene(int Fno){
        Farmer CurrFarmer = Farmers[Fno];

        Label title = new Label("\t\t Welcome "+CurrFarmer.name);
        Button farmBtn = new Button("Go to Farm");
        Button commBtn = new Button("Queries");
        Button reccBtn = new Button("Reccomendations");
        Button Backbtn = new Button("Logout");

        Backbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(MainMenuScene());
            }
        });
    
        farmBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(FarmScene(Fno));
            }
        });
    
        commBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(CommScene(Fno));
            }
        });
        reccBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(reccScene(Fno));
            }
        });
    
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(farmBtn, 0, 1);
        GridPane.setConstraints(commBtn, 1, 1);
        GridPane.setConstraints(reccBtn, 0, 2);
        GridPane.setConstraints(Backbtn, 0, 3);
        grid.getChildren().addAll(title, farmBtn, commBtn, reccBtn, Backbtn);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid,500,400);
        return scene;
    }

    public Scene CommScene(int Fno) {
    Label questionLabel = new Label("Please select your question:");
    ComboBox<String> questionComboBox = new ComboBox<>();
    questionComboBox.getItems().addAll(system.getQuestions()); 
    Button askButton = new Button("Ask");
    Label answerLabel = new Label();
    Button backBtn = new Button("Back");

    askButton.setOnAction(e -> {
        String selectedQuestion = questionComboBox.getValue();
        if (selectedQuestion != null) {
            String answer = system.getAnswer(selectedQuestion);
            answerLabel.setText("Answer: " + answer);
        } else {
            answerLabel.setText("Please select a question.");
        }
    });

    backBtn.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent ae) {
            primaryStage.setScene(FarmerMainMenuScene(Fno));
        }
    });

    GridPane grid = new GridPane();
    grid.setVgap(10);
    grid.setHgap(10);
    grid.setAlignment(Pos.CENTER);

    GridPane.setConstraints(questionLabel, 0, 0);
    GridPane.setConstraints(questionComboBox, 1, 0);
    GridPane.setConstraints(askButton, 1, 1);
    GridPane.setConstraints(answerLabel, 0, 2, 2, 1);
    GridPane.setConstraints(backBtn, 1, 3);

    grid.getChildren().addAll(questionLabel, questionComboBox, askButton, answerLabel, backBtn);

    return new Scene(grid, 500, 400);
}


    public Scene FarmScene(int Fno){
        Farmer CurrFarmer = Farmers[Fno];

        Label title = new Label("\t\t\t\t\t   " + CurrFarmer.name + "s Farm");
        String crpnm = "None";
        if(CurrFarmer.farmCrop!=null&&CurrFarmer.plantStat()) crpnm = CurrFarmer.farmCrop.cropName;
        Label farmstat = new Label("Farm Status\nPlant Growing: " + crpnm);
        ComboBox<String> cropComboBox = new ComboBox<>();
        cropComboBox.getItems().addAll("Wheat", "Rice", "Maize");
        Button backBtn = new Button("Back");
        Label selectionLabel = new Label("Select a crop:");

        Button plantbtn = new Button("Plant");

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(FarmerMainMenuScene(Fno));
            }
        });
    

        plantbtn.setOnAction(e -> {
            String selectedCrop = cropComboBox.getValue(); // Get the selected item
            if (selectedCrop != null) {
                if(CurrFarmer.farmCrop==null||!CurrFarmer.farmCrop.Growing) CurrFarmer.plant(selectedCrop);
                else selectionLabel.setText("Wait till Farm is free");
            } else {
                selectionLabel.setText("Please select a crop.");
            }
        });   
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(farmstat, 1, 1);
        GridPane.setConstraints(cropComboBox, 0, 2);
        GridPane.setConstraints(selectionLabel, 1, 2);
        GridPane.setConstraints(plantbtn, 3, 2);
        GridPane.setConstraints(backBtn, 0, 3);
        grid.getChildren().addAll(title, farmstat, cropComboBox, plantbtn, selectionLabel,backBtn);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid,500,400);
        return scene;
    }

    public Scene reccScene(int Fno){
        Farmer CurrFarmer = Farmers[Fno];
        String crp = "";
        if((CurrFarmer.region).equals("North")) crp = "Wheat";
        if((CurrFarmer.region).equals("West")) crp = "Rice";
        if((CurrFarmer.region).equals("South")) crp = "Maize";
        Label title = new Label("Recommended Plant to Grow: " + crp);
        Button Backbtn = new Button("Back");

        Backbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(FarmerMainMenuScene(Fno));
            }
        });
    
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(Backbtn, 0, 3);
        grid.getChildren().addAll(title, Backbtn);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid,500,400);
        return scene;
    }

    

    public Scene GovtLoginScene(){
        Label wlcm = new Label("Welcome");
        Label untxt = new Label("User name:");
        Label pwtxt = new Label("Password:");
        Button Loginbtn = new Button("Sign in");
        Button Backbtn = new Button("Go Back");
        TextField tf = new TextField();
        Label response = new Label();
        PasswordField pf = new PasswordField();

        Backbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(MainMenuScene());
            }
        });
        Loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String unm = tf.getText();
                String pass = pf.getText();
                
                try {
                    authenticateGovernment(unm, pass);
                    primaryStage.setScene(GovtMainMenuScene());
                } catch (InvalidCredentialsException e) {
                    response.setText(e.getMessage());
                    response.setTextFill(Color.RED);
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(wlcm, 0, 0);
        GridPane.setConstraints(untxt, 0, 1);
        GridPane.setConstraints(tf, 1, 1);
        GridPane.setConstraints(pwtxt, 0, 2);
        GridPane.setConstraints(pf, 1, 2);
        GridPane.setConstraints(response, 1, 0);
        GridPane.setConstraints(Backbtn, 0, 3);
        GridPane.setConstraints(Loginbtn, 2, 3);
        grid.getChildren().addAll(wlcm,untxt,tf,pwtxt,pf,response,Backbtn,Loginbtn);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid,500,400);
        return scene;
    }

    private void authenticateGovernment(String username, String password) throws InvalidCredentialsException {
        if (!Govt.authenticate(username, password)) {
            throw new InvalidCredentialsException("Invalid Username/Password");
        }
    }

    public Scene GovtMainMenuScene(){
        Label title = new Label("\t\t Welcome ");
        Button crrepBtn = new Button("Crop Production Report");
        Button detBtn = new Button("View Farmers & Details");
        Button Backbtn = new Button("Logout");

        Backbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(MainMenuScene());
            }
        });
    
        crrepBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(CropProductionReportScene());
            }
        });

        detBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                primaryStage.setScene(FarmerDetailsScene());
            }
        });

        
    
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(crrepBtn, 0, 1);
        GridPane.setConstraints(detBtn, 1, 1);
        GridPane.setConstraints(Backbtn, 0, 2);
        grid.getChildren().addAll(title, crrepBtn, detBtn, Backbtn);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid,500,400);
        return scene;
    }

    public Scene CropProductionReportScene() {
        Label title = new Label("Crop Production Report");
        Label cropReport = new Label(Govt.getCropProductionReport(Farmers));
        Button backBtn = new Button("Back");

        backBtn.setOnAction(ae -> primaryStage.setScene(GovtMainMenuScene()));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(title, 0, 0);
        grid.add(cropReport, 0, 1);
        grid.add(backBtn, 0, 2);

        Scene scene = new Scene(grid, 500, 400);
        return scene;
    }

    public Scene FarmerDetailsScene() {
    Label title = new Label("Farmers and Their Details");
    Button backBtn = new Button("Back");

    GridPane grid = new GridPane();
    grid.setVgap(10);
    grid.setHgap(10);
    grid.setAlignment(Pos.CENTER);

    GridPane.setConstraints(title, 0, 0, 3, 1);  // Updated column span to 3
    grid.add(title, 0, 0);
    GridPane.setConstraints(backBtn, 0, Fcount + 2);
    grid.add(backBtn, 0, Fcount + 2);

    // Column headers without username
    Label nameHeader = new Label("Name");
    Label regionHeader = new Label("Region");
    Label phoneHeader = new Label("Phone Number");

    GridPane.setConstraints(nameHeader, 0, 1);
    GridPane.setConstraints(regionHeader, 1, 1);
    GridPane.setConstraints(phoneHeader, 2, 1);
    grid.getChildren().addAll(nameHeader, regionHeader, phoneHeader);

    for (int i = 0; i < Fcount; i++) {
        Farmer farmer = Farmers[i];
        
        Label nameLabel = new Label(farmer.name);
        Label regionLabel = new Label(farmer.region);
        Label phoneLabel = new Label(farmer.phoneNumber);

        GridPane.setConstraints(nameLabel, 0, i + 2);
        GridPane.setConstraints(regionLabel, 1, i + 2);
        GridPane.setConstraints(phoneLabel, 2, i + 2);
        grid.getChildren().addAll(nameLabel, regionLabel, phoneLabel);
    }

    backBtn.setOnAction(ae -> primaryStage.setScene(GovtMainMenuScene()));

    Scene scene = new Scene(grid, 500, 400);
    return scene;
}



    public Scene ResourceDistributionScene() {
        Label title = new Label("Resource Distribution Report");
        Label resourceReport = new Label(Govt.getResourceDistributionReport());
        Button backBtn = new Button("Back");

        backBtn.setOnAction(ae -> primaryStage.setScene(GovtMainMenuScene()));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(title, 0, 0);
        grid.add(resourceReport, 0, 1);
        grid.add(backBtn, 0, 2);

        Scene scene = new Scene(grid, 500, 400);
        return scene;
    }

}
