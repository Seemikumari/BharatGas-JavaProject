package gasBooking;

import Customers.*;
import java.util.*;
import java.text.*;
import java.util.concurrent.TimeUnit;
public class Booking extends GasConnection{
    public double otp = 5678, amount = 825.0, refund = 0;
    public String dt ,  delDate , Status , DelMobileNo = "7838047861", status;
    public Date   dt_1, dt_2;
    public Booking (String name, String street , String area, String pincode, String mobile, int numberOfCylinders){
        super(name, street, area, pincode, mobile, numberOfCylinders);
    }

   public void getDates(){
       System.out.println("enter booking date: ");
       dt = new Scanner (System.in).next();
       dt_1 = null;
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       try {
           dt_1 = dateFormat.parse(dt);
       }catch (ParseException e){
           System.out.println("The error in getDates function " +e);
       }

       //delivery details
       System.out.println("Enter Delivery Date: ");
       delDate = new Scanner (System.in).nextLine();
       try{
           dt_2 = dateFormat.parse(delDate);
       }catch(Exception e){
           System.out.println("Error parsing  data dt_2:"+ e);
       }

       //find the difference between two dates
       //for checking the pending status
       try{
           long difference = dt_2.getTime() - dt_1.getTime();

           //difference in days
           long newDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

           if(newDifference > 7){ //check difference
               status = "P"; // if number of days is more than 7 then keep the status P
           }

       }catch (Exception e){
           System.out.println("Enter while finding difference "+e);

       }

   }
   //validate function
    public void validate(){
        // Check if lastDate is null
        //get the difference between the two dates
        long abcd = dt_1.getTime() - lastDate.getTime();
        long diff = TimeUnit.DAYS.convert(abcd, TimeUnit.MILLISECONDS);

        //print the difference between the two days
        System.out.println("Difference between two dates is :" + diff);
        if(numberOfCylinders == 1){ //check for single cylinders
            //for a single cylinder
            if(diff <30){
                System.out.println("Booking can not be done");
                //numberofcylinders = 0;
                status = "C";
            }else{ //difference is greater than 30
                System.out.println("status:Booked");
                status = "B"; //status booked
                lastDate = dt_1; //current booking date will become the ast booking date
            }
        }
        else if(numberOfCylinders == 2){
            if(diff <50){
                System.out.println("booking can not be done");
                //numberOfCylinders =0;
                status = "C";
            }else{
                status= "B"; //booked
                lastDate = dt_1;

            }
        }
    }


}
