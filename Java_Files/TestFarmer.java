package Top_Classes;

public class TestFarmer {
    public static void main(String[] args) {
        // Step 1: Create a Resources object
        Resources farmerResources = new Resources(2, 5, 10); // 2 machines, 5 fertilizers, irrigation rating of 10

        // Step 2: Create a Farmer object
        Farmer farmerJohn = new Farmer("John", "Bangalore", "7019659332", farmerResources);

        // Step 3: Check initial resources
        System.out.println("Initial Resources:");
        farmerJohn.checkResources();

        // Step 4: Plant a crop
        System.out.println("\nPlanting Crops:");
        farmerJohn.plantCrop("Wheat"); // Plant wheat
        farmerJohn.plantCrop("Rice"); // Plant rice

        // Step 5: Water the crops
        System.out.println("\nWatering Crops:");
        farmerJohn.waterCrops();

        // Simulate some time passing (in a real case, we'd wait for crops to grow)
        try {
            Thread.sleep(5000); // Sleep for 5 seconds to simulate crop growth
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 6: Harvest crops
        System.out.println("\nHarvesting Crops:");
        farmerJohn.harvestCrops();

        // Step 7: Check resources after planting and watering
        System.out.println("\nResources after operations:");
        farmerJohn.checkResources();
    }
}
