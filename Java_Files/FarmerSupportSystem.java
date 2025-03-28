package Top_Classes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class FarmerSupportSystem {
    private final HashMap<String, String> questionsAndAnswers;

    public FarmerSupportSystem() {
        questionsAndAnswers = new HashMap<>();
        initializeData();
    }

    private void initializeData() {
        questionsAndAnswers.put("What should I do about pest control?", 
            "Contact your local agricultural extension office for advice.");
        questionsAndAnswers.put("How can I improve irrigation?", 
            "Consider using drip irrigation to conserve water.");
        questionsAndAnswers.put("What are the latest policies on crop subsidies?", 
            "Visit the government agriculture website for updates.");
    }

    public String getAnswer(String question) {
        return questionsAndAnswers.getOrDefault(question, 
            "Sorry, I don't have an answer for that.");
    }

    // Method to retrieve all predefined questions
    public List<String> getQuestions() {
        return new ArrayList<>(questionsAndAnswers.keySet());
    }

    
}
