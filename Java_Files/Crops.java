
package Top_Classes;

import java.util.Random; //Tested

public class Crops implements Runnable {
    static Crops[] allCrops;
    String cropName;
    String suitableRegion;
    double priceSeed;
    double priceCrop;
    int growthTime;
    boolean Growing;
    boolean Grown;

    static {
        init();
    }

    // Default Constructor

    Crops() {
    }

    Crops(String n) {
        for (int i = 0; i < 3; i++) {
            if (n.equals(allCrops[i].cropName)) { // Assigning the details of crop according to the name.
                this.cropName = allCrops[i].cropName; // Could Implement Exception handling if crop name is invalid.
                this.suitableRegion = allCrops[i].suitableRegion;
                this.priceSeed = allCrops[i].priceSeed;
                this.priceCrop = allCrops[i].priceCrop;
                this.Grown = false;
                this.Growing = false;
                break;
            }
        }
        // Generating a random growth time in the range 100-199.
        Random r = new Random();
        growthTime = r.nextInt(30) + 30; // Put growthTime = r.nextInt(10)+10; for testing
    }

    public static void init() {
        allCrops = new Crops[3];
        // Setting Basic Crops & their Details.
        for (int i = 0; i < 3; i++)
            allCrops[i] = new Crops();
        allCrops[0].cropName = "Wheat";
        allCrops[1].cropName = "Rice";
        allCrops[2].cropName = "Maize";
        allCrops[0].suitableRegion = "North";
        allCrops[1].suitableRegion = "West";
        allCrops[2].suitableRegion = "South";
        allCrops[0].priceSeed = 25.0;
        allCrops[1].priceSeed = 120.0;
        allCrops[2].priceSeed = 250.0;
        allCrops[0].priceCrop = 34.0;
        allCrops[1].priceCrop = 55.0;
        allCrops[2].priceCrop = 22.8;
    }

    public void plant() { // Implementing Multithreading
        if (!(this.Growing)) {
            if (!(this.Grown)) {
                Thread crop = new Thread(this, "Crop " + this.cropName);
                crop.start();
                System.out.println("Crop Planted");
            } else {
                System.out.println("Already Grown");
            }
        } else {
            System.out.println("Already Planted");
        }
    }

    public void run() {
        this.Growing = true;
        try {
            while (this.growthTime != 0) {
                Thread.sleep(1000);
                this.growthTime--;
            }
            this.Growing = false;
            this.Grown = true;
        } catch (InterruptedException e) {
            System.out.println("Crop Growth Interrupted");
            System.exit(1);
        }
        System.out.println("Crop Grown");
    }
}

class TestCrops {
    public static void main(String[] args) {
        Crops Crop1 = new Crops("Maize");
        System.out.println("Grown: " + Crop1.Grown);
        System.out.println("Growing: " + Crop1.Growing);
        System.out.println("Growth time: " + Crop1.growthTime);
        System.out.println("Region: " + Crop1.suitableRegion);
        Crop1.plant();
        int i = 10; // i=9 to test during growth
        while (i != 0) {
            System.out.println("growing");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            i--;
        }
        System.out.println("Growth status: ");
        System.out.println("Grown: " + Crop1.Grown);
        System.out.println("Growing: " + Crop1.Growing);
    }
}
