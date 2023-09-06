package carRentalSimulation;

import java.util.Scanner;

public class CarRentingLinesDemo {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter available car count, N= ");
        int N = scan.nextInt();

        System.out.print("Enter customer count, k= ");
        int k = scan.nextInt();

        CarRentingLines carRentingLine = new CarRentingLines();
        carRentingLine.simulate(N,k);


    }

}
