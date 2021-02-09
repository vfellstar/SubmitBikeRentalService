package uk.ncl.csc8014.bike;
import uk.ncl.csc8014.person.CustomerID;
import java.util.ArrayList;

class PastRenters {
    private final static ArrayList<CustomerID> pastRenters = new ArrayList<>();

    public ArrayList<CustomerID> getPastRenters() {
        ArrayList ar = pastRenters;
        return ar;
    }

    void addToList(CustomerID customerIdentifier) {
        pastRenters.add(customerIdentifier);
    }


}
