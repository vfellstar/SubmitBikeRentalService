package uk.ncl.csc8014.person;

import uk.ncl.csc8014.bike.Bike;

import java.util.*;

public class PersonFactory {
    private static final Map<String, Person> registeredPersons = new HashMap<>();

    // new Person(String name, Date dob, String uniqueNumber);

    PersonFactory() {

    }

    public static Person getInstance(String firstName, String lastName, int dobYear, int dobMonth, int dobDay, String uniqueCustomerID) {

        Person person = registeredPersons.get(uniqueCustomerID);
        if (person != null) {
            return person;
        }

        Calendar c = new GregorianCalendar(dobYear, dobMonth - 1, dobDay);
        Date dob = c.getTime();

        int twoDigitNumber = 0;
        String code = "0";

        boolean isNumber= true;

        try {
            twoDigitNumber = Integer.valueOf(uniqueCustomerID.substring(8));
        } catch (NumberFormatException e) {
            isNumber = false;
        }

        if (twoDigitNumber < 10) {
            code += twoDigitNumber;
        }
        if (isNumber == false || !code.matches("\\d\\d"))
            throw new IllegalArgumentException("uniqueCustomerID was not entered in the correct format.");

        person = new Person(firstName, lastName, dob, code);

        registeredPersons.put(person.getCustomerRecord().getCustomerID().toString(), person);
        return person;
    }

    public static Bike getPersonBike(CustomerRecord customerRecord) {
        Person person = registeredPersons.get(customerRecord.getCustomerID().toString());
        if (person != null) {
            return person.getRentedBike();
        }
        return null;
    }

    //Retrieve all Registered Users
    public static ArrayList getAllRegisteredUsers() {
        ArrayList ar = new ArrayList();

        for (Person p: registeredPersons.values()) {
            ar.add(p);
        }

        return ar;
    }

    //Renting to a person Methods
    public static CustomerRecord getCustomerRecord(String customerNumber) {
        Person person = registeredPersons.get(customerNumber);
        if (person == null) throw new IllegalArgumentException("This is not a registered user - Unable to retrieve customer record.");
        return person.getCustomerRecord();
    }

    //Renting to a person Methods
    private static Person retrievePerson(CustomerRecord customerRecord) {
        for (Person p: registeredPersons.values()) {
            if (p.getCustomerRecord().equals(customerRecord) && !p.isRentingBike()) {
                return p;
            }
        }
        return new Person(null, null, null, null);
    }

    public static void changeRentBike(CustomerRecord customerRecord, Bike bike) {
        retrievePerson(customerRecord).rentBike(bike);
    }



}
