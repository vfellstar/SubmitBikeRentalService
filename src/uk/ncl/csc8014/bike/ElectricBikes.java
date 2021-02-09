package uk.ncl.csc8014.bike;

import uk.ncl.csc8014.person.CustomerID;

/**
 * This creates a new serial number.
 *
 * @author Vincent Taylor
 * @version 1.0
 */

class ElectricBikes extends BikeFactory{

    private final BikeSerial uniqueNumber;
    private final PastRenters pastRenters;
    private boolean fullyCharged;
    private CustomerID renter = null;

    public ElectricBikes(String serialNumber) {
        super(serialNumber);
        uniqueNumber = new BikeSerial(serialNumber.substring(0,2), Integer.valueOf(serialNumber.substring(2)));
        pastRenters = new PastRenters();

    }

    @Override
    public BikeSerial getSerialNumber() {
        return uniqueNumber;
    }

    @Override
    public boolean isAvailable() {
        if (renter == null) {
            return true;
        }
        return false;
    }

    @Override
    public void rent(CustomerID customerID) {
        renter = customerID;
    }

    @Override
    public void returnBike() {
        pastRenters.addToList(this.renter);
        renter = null;
    }

    public boolean isFullyCharged() {
        return fullyCharged;
    }

    public void chargeBattery() {
        fullyCharged = true;
    }

    @Override
    public String toString() {
        return "Electric: " + getSerialNumber().getSerialNumberTwoLetters() + getSerialNumber().getSerialNumberDigits();
    }
}
