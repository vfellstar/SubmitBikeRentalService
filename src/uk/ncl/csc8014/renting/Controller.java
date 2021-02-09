package uk.ncl.csc8014.renting;
import uk.ncl.csc8014.bike.Bike;
import uk.ncl.csc8014.bike.BikeFactory;
import uk.ncl.csc8014.person.CustomerRecord;
import uk.ncl.csc8014.person.PersonFactory;
import java.util.ArrayList;

public final class Controller {
    private static final String ROAD_BIKE = "road";
    private static final String ELECTRIC_BIKE = "electric";


    ArrayList availableBikes(String typeOfBike) {
        if (typeOfBike == ROAD_BIKE) return BikeFactory.getAllAvailableRoadBikes();
        if (typeOfBike == ELECTRIC_BIKE) return BikeFactory.getAllAvailableElectricBikes();
        return new ArrayList();
    }

    ArrayList getRentedBikes() {

        return BikeFactory.getAllUnavailableBikes();

      }

    void issueBike(CustomerRecord customerRecord, String typeOfBike) {
        Bike bike = BikeFactory.rentBike(customerRecord, typeOfBike);
        PersonFactory.changeRentBike(customerRecord, bike);
    }

    Bike getBike(CustomerRecord customerRecord) {
        return PersonFactory.getPersonBike(customerRecord);
    }
/*
    terminateRental(customerRecord){

    // This method terminates the rental contract associated with the given customer record.
    In effect, the renter is returning the bike. The bike is then available for rent by someone else.
    The method removes the record of the rental from the company\\'s records. This method changes the status of the
    returned bike to not rented. If the bike is an electric bike, then it is necessary to recharge the battery.
    There is no charge to the customer for this. Terminating a non-existent contract has no effect.

    }
*/
    // Test Methods for Bikes
    // Tests how creation method handles incorrect input
    public void CreationOfBikesInvalidInputs() {
        // All of these will cause errors
        // invalid serialNumbers
        System.out.println(BikeFactory.getInstance("road", "11111"));
        System.out.println(BikeFactory.getInstance("road", "aaaaa"));
        System.out.println(BikeFactory.getInstance("road", "123412341234"));
        System.out.println(BikeFactory.getInstance("electric", "11111"));
        System.out.println(BikeFactory.getInstance("electric", "aaaaa"));
        System.out.println(BikeFactory.getInstance("electric", "123412341234"));
        // Invalid bikeType
        System.out.println(BikeFactory.getInstance("boomBike", "ab123"));
    }

    // Tests how it handles duplicate serial numbers - also used to fill the HashMaps with bikes for further testing
    public void CreationOfDuplicatesBikes() {

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

    public ArrayList getAllPeople() {
        return PersonFactory.getAllRegisteredUsers();
    }
    // Tests if new objects can be created outside of the factory and potentially altered.
    /*    public void alteringBikes() {
        Bike b = BikeFactory.getInstance("road", "ad123");
        System.out.println(BikeFactory.getAllAvailableBikes());

        BikeSerial bs = b.getSerialNumber(); //Cannot access BikeSerial from outside the package

        b = new RoadBikes(); //Cannot access class from outside the package
    }*/

    //Test Methods for Person
    //Tests for Duplicates
    public void testDuplicatePersons() {
        System.out.println(PersonFactory.getInstance("Vincent", "Taylor", 1996, 5, 20, "CD-2018-01"));
        System.out.println();
        System.out.println("Printing all registered people");


        PersonFactory.getInstance("Vincent", "Taylor", 1996, 5, 20, "VT-2021-01");

        PersonFactory.getInstance("Rhian", "Smith", 1995, 9, 28, "RS-2021-02");

        System.out.println();
        System.out.println("Printing all registered people");
        System.out.println(getAllPeople());
    }

    public void testRenting() {
        Controller c = new Controller();
        c.CreationOfDuplicatesBikes();
        c.testDuplicatePersons();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("All People");
        System.out.println(c.getAllPeople());
        System.out.println();
        System.out.println("All Bikes - currently available");
        System.out.println(BikeFactory.getAllAvailableBikes());
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("The customer record: ");
        CustomerRecord customerRecord = PersonFactory.getCustomerRecord("VT-2021-01");
        CustomerRecord customerRecord1 = PersonFactory.getCustomerRecord("RS-2021-02");
        System.out.println(customerRecord);
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        c.issueBike(customerRecord, "road");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println(c.getAllPeople());
        System.out.println(BikeFactory.getAllAvailableBikes());
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println(c.getBike(customerRecord1));
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        c.testRenting();
    }

}
