package uk.ncl.csc8014.renting;
import uk.ncl.csc8014.bike.BikeFactory;
import uk.ncl.csc8014.person.PersonFactory;

public class Testing {
    public static final String ROAD_BIKE = "road";
    public static final String ELECTRIC_BIKE = "electric";

    /*
        getBike(customerRecord) {

        //Given a person\\'s customer record, this method returns the bike they are currently renting (if any)

        }

        issueBike(customerRecord, typeOfBike) {

          //Given a person's customerRecord and type of bike required (road or electric),
          this method determines whether the person is eligible to rent a bike of the specified type and, if there is a bike available,
          issues a bike of the specified type. The method associates the bike with the person\\'s customer number
          (so that the company has a record of bikes out for rent and the people renting them). If a bike cannot be issued,
          the method returns an appropriate indication of the failure to issue a bike. Note, this does not have to indicate
          why a bike cannot be issued, it simply indicates that a bike cannot be issued. The rules for determining whether
          or not a bike can be issued are given below.

          }

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


        PersonFactory.getInstance("Vincent", "Taylor", 1996, 5, 20, "CD-2018-02");

        System.out.println();
        System.out.println("Printing all registered people");
    }


    public static void main(String[] args) {
        Controller c = new Controller();
        c.CreationOfDuplicatesBikes();
        System.out.println("All Road Bikes");
        System.out.println(c.availableBikes("road"));
        System.out.println("All Electric Bikes");
        System.out.println(c.availableBikes("electric"));
        System.out.println();
        System.out.println(c.getRentedBikes());
    }
}