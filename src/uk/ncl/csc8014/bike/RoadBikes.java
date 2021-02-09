package uk.ncl.csc8014.bike;


import uk.ncl.csc8014.person.CustomerID;

class RoadBikes extends BikeFactory{

    private final BikeSerial uniqueNumber;
    private final PastRenters pastRenters;
    private CustomerID renter = null;

    public RoadBikes(String serialNumber) {
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
    public String toString() {
        return "Road: " + getSerialNumber().getSerialNumberTwoLetters() + getSerialNumber().getSerialNumberDigits();
    }

    public void rent(CustomerID customerID) {
        renter = customerID;
    }

    @Override
    public void returnBike() {
        pastRenters.addToList(this.renter);
        renter = null;
    }


}
