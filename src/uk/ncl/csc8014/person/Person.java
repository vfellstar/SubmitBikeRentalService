package uk.ncl.csc8014.person;

import uk.ncl.csc8014.bike.Bike;

import java.util.Date;
import java.util.GregorianCalendar;

class Person {
    private final String firstName;
    private final String lastName;
    private final Date dob;
    private final CustomerRecord customerRecord;
    private Bike renting;

    Person(String firstName, String lastName, Date dob, String uniqueNumber) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Name is null!");
        }
        if (dob == null) {
            throw new IllegalArgumentException("Date of birth is null!");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.customerRecord = new CustomerRecord(createIDCode(uniqueNumber));
    }

    String getFirstName() {
        return firstName;
    }
    String getLastName() {
        return lastName;
    }
    Date getDob() {
        return dob;
    }

    private String createIDCode(String twoNumbers) {

        if (!String.valueOf(twoNumbers).matches("\\d\\d")) throw new IllegalArgumentException("A two digit number was not selected");

        Date d = new GregorianCalendar().getTime();

        String toReturn = "";
        toReturn += getFirstName().substring(0, 1) +
               getLastName().substring(0, 1) + "-"
                + d.toString().substring(d.toString().length() - 4) + "-" +
                twoNumbers;
        return toReturn;
    }

    CustomerRecord getCustomerRecord() {
        return customerRecord;
    }

    boolean isRentingBike() {
        if (renting == null) return false;
        return true;
    }

    void returnBike() {
        renting = null;
    }

    void rentBike(Bike bike) {
        renting = bike;
        // TESTING  -===================================================================================================
        System.out.println(renting);
    }

    @Override
    public String toString(){
        return firstName + " " + lastName + "\n" + customerRecord.getCustomerID() + "\n" + dob + "\nRenting: " + renting;
    }

    Bike getRentedBike() {
        return renting;
    }
}
