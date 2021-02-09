package uk.ncl.csc8014.person;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class CustomerRecord {
    private boolean isGoldClass;
    private final CustomerID customerID;
    private final Date dateIssued;

    CustomerRecord(String uniqueReference) {
        this.customerID = new CustomerID(uniqueReference);
        this.dateIssued = new GregorianCalendar().getTime();
        this.isGoldClass = false;
    }

    public boolean isGoldClass() {
        return isGoldClass;
    }

    public CustomerID getCustomerID() {
        return  customerID;
    }

    @Override
    public String toString() {
        return customerID.toString() + "\nGold Class: " + isGoldClass + "\nDate Issued: " + dateIssued;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRecord that = (CustomerRecord) o;
        return isGoldClass == that.isGoldClass && Objects.equals(customerID, that.customerID) && Objects.equals(dateIssued, that.dateIssued);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }
}
