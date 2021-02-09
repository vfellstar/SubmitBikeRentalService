package uk.ncl.csc8014.bike;

import uk.ncl.csc8014.person.CustomerID;
import uk.ncl.csc8014.person.CustomerRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BikeFactory implements Bike{

    public static final String ROAD_BIKE = "road";
    public static final String ELECTRIC_BIKE = "electric";

    private final static Map<String, Bike> bikes = new HashMap<String, Bike>();

    private static int numberOfElectricBikes = 0;
    private static int numberOfRoadBikes = 0;

    private CustomerID renter = null;


    /**
     * Creator method to create a factory for the bikes.
     */
    BikeFactory(String serialNumber) {

    }

    /**
     * Get instance method to be used return already made bike using the serial number or create a new bike.
     * @param bikeType String to indicate the type of bike.
     * @param serialNumber Unique string to indicate serial number.
     * @return bike matching serial number or new bike object.
     */
    public static Bike getInstance (String bikeType, String serialNumber) {

        Bike bike = bikes.get(serialNumber);

        if (bike != null) {
            return bike;
        }

        if (bikeType.equals(ROAD_BIKE) && numberOfRoadBikes < 50) {
            bike = new RoadBikes(serialNumber);
        } else if (bikeType.equals(ELECTRIC_BIKE) && numberOfElectricBikes < 10) {
            bike = new ElectricBikes(serialNumber);

        } else {
            throw new IllegalArgumentException("Invalid bike type: " + bikeType + "\nor reached max number of " + bikeType + " bikes");
        }

        bikes.put(serialNumber, bike);
        return bike;
    }

    /**
     *
     * @param bikeType
     * @param serialNumber
     * @param customerRecord
     */
    private static Bike setBikeToRented (String bikeType, String serialNumber, CustomerRecord customerRecord) {
        Bike toRent = getInstance(bikeType, serialNumber);

        if (!customerRecord.isGoldClass() && bikeType == ELECTRIC_BIKE) {
            throw new IllegalArgumentException("Only \'Gold Class\' customers may rent Electric Bikes.");
        }

        toRent.rent(customerRecord.getCustomerID());
        return toRent;
    }

    public static ArrayList getAllAvailableBikes() {
        ArrayList<Bike> available = new ArrayList<>();
        for (Bike b: bikes.values()) {
            if (b.isAvailable()) {
                available.add(b);
            }
        }
        return available;
    }

    public static ArrayList getAllAvailableElectricBikes() {
        ArrayList<Bike> available = new ArrayList<>();
        for (Bike b: bikes.values()) {
            if (b.isAvailable() && b instanceof ElectricBikes) {
                available.add(b);
            }
        }
        return available;
    }

    public static ArrayList getAllAvailableRoadBikes() {
        ArrayList<Bike> available = new ArrayList<>();
        for (Bike b: bikes.values()) {
            if (b.isAvailable() && b instanceof RoadBikes) {
                available.add(b);
            }
        }
        return available;
    }

    public static ArrayList getAllUnavailableBikes() {
        ArrayList<Bike> unavailable = new ArrayList<>();
        for (Bike b: bikes.values()) {
            if (!b.isAvailable()) {
                unavailable.add(b);
            }
        }
        return unavailable;
    }

    //
    private static String getAvailableRoadKey() {
        String key = "";
        for (String s: bikes.keySet()) {
            Bike b = bikes.get(s);
            if (b.isAvailable() && b instanceof RoadBikes) return s;
        }
        return key;
    }

    private static String getAvailableElectricKey() {
        String key = "";
        for (String s: bikes.keySet()) {
            Bike b = bikes.get(s);
            if (b.isAvailable() && b instanceof ElectricBikes) return s;
        }
        return key;
    }

    private static Bike returnAvailableBikeSerialCode(String bikeType){
        String key = "";
        if (bikeType == ROAD_BIKE) {
            key = getAvailableRoadKey();
        } else if (bikeType == ELECTRIC_BIKE) {
            key = getAvailableElectricKey();
        } else {
            throw new IllegalArgumentException("That is not a valid bike type.");
        }

        return getInstance(bikeType, key);
    }

    public static Bike rentBike(CustomerRecord customerRecord, String typeOfBike) {
        if (bikes.isEmpty()) throw new IllegalArgumentException("There are no bikes!");

        Bike bike = returnAvailableBikeSerialCode(typeOfBike);
        String serialNumber  = bike.getSerialNumber().getSerialNumberTwoLetters() + bike.getSerialNumber().getSerialNumberDigits();
        return setBikeToRented (typeOfBike, serialNumber, customerRecord);
    }


    //Test
    public static void CreationOfDuplicatesBikes() {

        //Road Bikes
        System.out.println("Creating Road Bikes");
        System.out.println(BikeFactory.getInstance("road", "ad123"));
        System.out.println(BikeFactory.getInstance("road", "hg856"));
        System.out.println(BikeFactory.getInstance("road", "as223"));
        System.out.println("All available Bikes: ");
        System.out.println(BikeFactory.getAllAvailableBikes());
        System.out.println("Duplicate - " + BikeFactory.getInstance("road", "ad123"));
        System.out.println(BikeFactory.getAllAvailableBikes());
        //Electric Bikes
        System.out.println();
        System.out.println();
        System.out.println(BikeFactory.getInstance("electric", "af432"));
        System.out.println(BikeFactory.getInstance("electric", "gh576"));
        System.out.println(BikeFactory.getInstance("electric", "kj987"));
        System.out.println("All available Bikes: ");
        System.out.println(BikeFactory.getAllAvailableBikes());
        System.out.println("Duplicate - " + BikeFactory.getInstance("electric", "kj987"));
        System.out.println(BikeFactory.getAllAvailableBikes());
        //Creating Electric bikes with same serial code as Road Bikes
        System.out.println();
        System.out.println();
        System.out.println(BikeFactory.getInstance("electric", "ad123"));
        System.out.println(BikeFactory.getInstance("electric", "as223"));
        System.out.println(BikeFactory.getInstance("electric", "as223"));
        System.out.println("All available Bikes: ");
        System.out.println(BikeFactory.getAllAvailableBikes());
        System.out.println("Duplicate - " + BikeFactory.getInstance("electric", "ad123"));
        System.out.println(BikeFactory.getAllAvailableBikes());

    }
    public static void main(String[] args) {
        CreationOfDuplicatesBikes();
        System.out.println(returnAvailableBikeSerialCode("electric"));



    }

}
