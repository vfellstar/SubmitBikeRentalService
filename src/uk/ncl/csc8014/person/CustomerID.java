package uk.ncl.csc8014.person;

public class CustomerID {
    private final String uniqueReference;

    CustomerID(String uniqueReference) {
        this.uniqueReference = uniqueReference;
    }


    @Override
    public String toString() {
        return uniqueReference;
    }
}
