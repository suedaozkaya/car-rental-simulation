package carRentalSimulation;

import java.util.Random;

public class CarRentingLines {
    private DequeInterface<Car> carArrayDeque;
    private QueueInterface<Customer> customerArrayQueue;
    private ListInterface<Car> rentedCarList;
    private int numberOfDays;
    Random rnd = new Random();

    public CarRentingLines(){
        carArrayDeque = new ArrayDeque<>();
        customerArrayQueue = new ArrayQueue<>();
        rentedCarList = new ArrayList<>();
        numberOfDays = 0;
        reset();
    }

    private void reset() {
        carArrayDeque.clear();
        customerArrayQueue.clear();
        rentedCarList.clear();
    }

    public void simulate(int numberOfCars, int numberOfCustomers){
        for(int i=1; i<=numberOfCars; i++){
            double qualityScore = 1 + 2* rnd.nextDouble();
            Car car = new Car(i, qualityScore);
            carArrayDeque.addToBack(car);
        }

        for(int i=1; i<=numberOfCustomers; i++){
            double qualityThreshold = 1 + 2* rnd.nextDouble();
            Customer customer = new Customer(i, qualityThreshold);
            customerArrayQueue.enqueue(customer);
        }

        Customer customer;
        Car nextCar;
        int repeatTimeForEachCar;
        boolean isCarTaken;

        while (numberOfCustomers!=0){
            numberOfDays++;
            System.out.println("**************Day" + numberOfDays + "**************");


            if (!rentedCarList.isEmpty()){
                for (int j = 1; j <= rentedCarList.getLength(); j++) {
                    rentedCarList.getEntry(j).setDaysLeft(rentedCarList.getEntry(j).getDaysLeft()-1);
                    if (rentedCarList.getEntry(j).getDaysLeft()==0){
                        rentedCarList.getEntry(j).setCustomer(null);
                        carArrayDeque.addToFront(rentedCarList.getEntry(j));
                        numberOfCars++;
                        rentedCarList.remove(j);
                        j--; // after remove operation
                    }
                }
            }

            if (numberOfCars==0){
                System.out.println("There are no cars to rent. Moving to the next day...");
                System.out.println("Rented cars: ");
                for (int r = 1; r <= rentedCarList.getLength(); r++) {
                    Car rentedCar = rentedCarList.getEntry(r);
                    System.out.println("car" + rentedCar.getCarID() +
                            " by cust" +rentedCar.getCustomer().getCustomerID() +
                            " occupancy=" + rentedCar.getDaysLeft());
                }
                continue;
            }

            int numberOfCarsAtTheBeg = numberOfCars;

            for (int i = 1; i <= numberOfCarsAtTheBeg; i++) {

                nextCar = carArrayDeque.removeFront();
                numberOfCars--;

                repeatTimeForEachCar = 0;
                isCarTaken = false;
                int rejectNum = 0;


                while(!isCarTaken && repeatTimeForEachCar<=numberOfCustomers-1 && !customerArrayQueue.isEmpty()){
                    customer = customerArrayQueue.dequeue();
                    numberOfCustomers--;
                    System.out.print("Current " + nextCar + " is offering to \n" +
                            "\tCurrent " + customer);

                    if (nextCar.getQualityScore()>=customer.getQualityThreshold()){
                        repeatTimeForEachCar++;
                        isCarTaken = true;
                        nextCar.setCustomer(customer);
                        System.out.println("                ---accepted");
                        rentedCarList.add(nextCar);

                        int daysLeft = rnd.nextInt(5) + 1;
                        nextCar.setDaysLeft(daysLeft);
                        for (int m = 0; m < numberOfCustomers-rejectNum; m++) {
                            customerArrayQueue.enqueue(customerArrayQueue.dequeue());
                        } // this for loop is for providing the following task:
                        // "If the applicant does not accept, he or she is placed back
                        // at the beginning of the waiting list for the next car, and must wait again."

                    }else {
                        isCarTaken = false;
                        rejectNum++;
                        repeatTimeForEachCar++;
                        System.out.println("                ---not accepted");
                        customer.setQualityThreshold(customer.getQualityThreshold()*0.9);
                        customerArrayQueue.enqueue(customer);
                        numberOfCustomers++;
                    }
                    if (repeatTimeForEachCar==numberOfCustomers && !isCarTaken){
                        System.out.println("\t---not accepted by any customer---");
                    }

                }

                if (!isCarTaken){
                    carArrayDeque.addToBack(nextCar);
                    numberOfCars++;
                }

                if(i==numberOfCarsAtTheBeg){
                    System.out.println("All cars have seen");
                    if(!customerArrayQueue.isEmpty()) {
                        System.out.println("But there are still customers waiting");
                    }else {
                        System.out.println("All customer rent a car.");
                        break;
                    }
                    System.out.println("Rented cars: ");
                    for (int r = 1; r <= rentedCarList.getLength(); r++) {
                        Car rentedCar = rentedCarList.getEntry(r);
                        System.out.println("\tcar" + rentedCar.getCarID() +
                                " by cust" +rentedCar.getCustomer().getCustomerID() +
                                " occupancy=" + rentedCar.getDaysLeft());
                    }
                    System.out.println("Available cars: ");
                    for (int s = 0; s < numberOfCars; s++) {
                        Car availableCar = carArrayDeque.removeFront();
                        System.out.println("\tcar" + availableCar.getCarID());
                        carArrayDeque.addToBack(availableCar);
                    }
                    System.out.println("****************End of Day****************\n");

                }
            }

        }
    }

}
