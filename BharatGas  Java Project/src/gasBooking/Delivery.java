package gasBooking;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Delivery extends Booking{

    public String delPersonName;
    int customerOtp;
    public Delivery(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile, numberOfCylinders);
    }
    public void amountCalc(){
        long dayDiff = dt_2.getTime() - dt_1.getTime(); //dt_2 is delivery and dt_1 is booking
        long newDiff = TimeUnit.DAYS.convert(dayDiff, TimeUnit.MILLISECONDS);

        if(newDiff > 7 ){
            refund = 41.25;
            amount = amount - refund; //amount = 800-41.45
        }
    }
    public void verifyOtp(){
        if(status.equals("B")){ //status = booked
            System.out.println("enter Otp: ");
            customerOtp = new Scanner(System.in).nextInt();

            if(customerOtp != otp){
                status = "C"; //cancelled
            }else{
                status = "D"; //delivered
            }
        }else{
            System.out.println("no booking found!!!!");
        }
    }
    public void delPersonDetails(){
        System.out.println("\n Enter the delivery person name : ");
        delPersonName = new Scanner (System.in).nextLine();

    }
}
