package carRentalSimulation;

public class Car {

    private int carID;
    private double qualityScore;
    private int daysLeft;
    private Customer customer;

    public Car(int carID, double qualityScore) {
        this.carID = carID;
        this.qualityScore = qualityScore;
        this.daysLeft = 0;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "car" + carID +
                " quality=" + String.format("%.2f",qualityScore);
    }
}
