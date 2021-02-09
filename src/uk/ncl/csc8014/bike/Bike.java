package uk.ncl.csc8014.bike;

import uk.ncl.csc8014.person.CustomerID;

public interface Bike {



    BikeSerial getSerialNumber();

    boolean isAvailable();

    void rent(CustomerID customerID);

    void returnBike();

}
