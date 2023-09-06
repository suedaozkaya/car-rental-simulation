package carRentalSimulation;

public class Customer {
    private int customerID;
    private double qualityThreshold;

    public Customer(int customerID, double qualityThreshold) {
        this.customerID = customerID;
        this.qualityThreshold = qualityThreshold;
    }

    public double getQualityThreshold() {
        return qualityThreshold;
    }

    public void setQualityThreshold(double qualityThreshold) {
        this.qualityThreshold = qualityThreshold;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "cust" + customerID +
                " threshold=" + String.format("%.2f",qualityThreshold);
    }
}
