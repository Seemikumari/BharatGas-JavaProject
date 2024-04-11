

import Customers.*;
import gasBooking.*;
import gasSupplier.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static gasSupplier.gasAgency.agencyCode;
import static gasSupplier.gasAgency.phNumber;

public class Main {
    static int count;
    static int bcount =0, ccount=0, dcount=0, pcount=0;

    static String dpname;

    public static void cylinderCount(Delivery[] obj){
       String[] months = new String[]{"January", "february", "March", "April", "May", "June",
       "July", "August", "September", "October", "November", "December"};

       for(Delivery delivery: obj){
           count = 0;
           System.out.println("In the month of "+ (months[delivery.dt_2.getMonth()])+ ":");
           System.out.println("In "+ delivery.area);
           if(delivery.status.equals("D")){
               count+=delivery.numberOfCylinders;
           }
           System.out.println("-"+count+"cylinders delivered");

       }
        System.out.println("\n");

    }

    public static void checkLateDel(Delivery[] obj){
        String[] months = new String[]{"January", "february", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        int[] month = new int[12];
        for(Delivery delivery: obj){
            if(delivery.status.equals("D") && delivery.amount == 783.75){
            month[delivery.dt_2.getMonth()] += 1;
            /*february , month array ->at index 1st = 1;*/
            }
        }
        System.out.println("-------late delivery -------------");
        for(int i =0 ; i<12; i++){
            if(month[i] != 0){
                System.out.println("* In "+months[i] +"there are" +month[i]);
            }
        }
        System.out.println("\n");

    }

    public static void numOfSingleCylinders(Delivery[] obj) {
        System.out.println("----------Single Cylinder Holders----------------");
        for (int i = 0; i < obj.length; i++) {
            if (obj[i].numberOfCylinders == 1) {
                System.out.println("* Customer Name: " + obj[i].name);
                System.out.println("* Mobile No.: " + obj[i].mobile);
                System.out.println("* Gas Connection No. : " + (i + 101));

            }
        }
        System.out.println("\n");
    }

    public static void DeliveryDetails(Delivery[] obj){
        System.out.println("-------------Delivery Details----------------");
        System.out.println("enter the name of the delivery person: ");
        dpname =  new Scanner(System.in).next();
        for(Delivery delivery: obj){
            if(delivery.status.equals("D") && delivery.delPersonName.equals(dpname)){
                System.out.println("* Customer Name: " + delivery.name);
                System.out.println(" - "+delivery.Street+","+delivery.area+ " , "+ delivery.pincode);
            }
        }
        System.out.println("\n");
    }

    public static void printReport(Delivery[] obj){
        System.out.println("-------Delivery Report--------------");
        for(int i =0; i<obj.length;i++){
            if(obj[i].status.equals("D")){
                dcount++;
            }
            else if(obj[i].status.equals("B")){
                bcount++;
            }
            else if(obj[i].status.equals("C")){
                ccount++;
            }
            else if(obj[i].status.equals("P")){
                pcount++;
            }
            else {
                System.out.println("Status Invalid");
            }
            System.out.println("* Booked");
            System.out.println(" - "+ bcount + "booked");
            System.out.println("* Delivered");
            System.out.println(" - "+dcount +"delivered");
            System.out.println("* Cancelled ");
            System.out.println(" - "+ccount + "cancelled");
            System.out.println("* Pending");
            System.out.println(" - "+pcount + "pending");
            System.out.println("\n");
        }
    }

    public static void printInvoice(Delivery[] obj){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String invoiceDate = sdf.format(d);
        for( int i =0; i<obj.length; i++){
            if(obj[i].status.equals("D")){
                System.out.println("-----------------------------------------------------------------");
                System.out.println("                                    INVOICE                      ");
                System.out.println("------------------------------------------------------------------");
                System.out.println("Gas Agency Code"+ agencyCode + "\t\t\t" +"Date of Invoice: " + invoiceDate);
                System.out.println("Gas Agency Code"+ agencyCode + "\t\t\t" +"Agency Phone Number: " + phNumber);
                System.out.println("Gas Connection No.:" +(i + 101) + "\t\t\t" + "Customer Name "+ obj[i].name);
                System.out.println("Booking Date: " + sdf.format(obj[i].dt_1)+ "\t\t" +"Customer Mobile No.: "+obj[i].mobile);
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Amount: "+obj[i].amount);
                System.out.println("Refund: "+obj[i].refund);
                System.out.println("Total Amount: "+(obj[i].amount - obj[i].refund));
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Delivery Person Name: "+obj[i].delPersonName + "\t\t"+ "Delivery Person Mobile: "+ obj[i].DelMobileNo);
                System.out.println("Delivery Date: "+sdf.format(obj[i].dt_2));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("\n\n");

            }
        }
    }

    public static void main(String[] args) {

        System.out.println("**********************************************************************************");
        System.out.println("                               BHARAT GAS AGENCY                                  ");
        System.out.println("**********************************************************************************");
        Delivery[] deliveryObject = new Delivery[5];
        deliveryObject[0] = new Delivery("Prashansa", "LN-40", "Alpha Zone", "000002" , "7892348191" , 1);
        deliveryObject[1] = new Delivery("Ananya", "LN-7", "Beta Zone", "000003" , "7892348151" , 2);
        deliveryObject[2] = new Delivery("Shruti", "LN-0", "Gamma Zone", "000004" , "7892348231" , 3);
        deliveryObject[3] = new Delivery("Aakriti", "LN-9", "Sine Zone", "000005" , "1232348191" , 1);
        deliveryObject[4] = new Delivery("Ranjana", "LN-48", "Cosmos Zone", "000006" , "4562348191" , 2);

        for(Delivery delivery : deliveryObject){
            delivery.delPersonDetails();
            delivery.getLastDate();
            delivery.getDates();
            delivery.validate();
            delivery.amountCalc();
            delivery.verifyOtp();

        }

        System.out.println();
        cylinderCount(deliveryObject);
        checkLateDel(deliveryObject);
        numOfSingleCylinders(deliveryObject);
        DeliveryDetails(deliveryObject);
        printReport(deliveryObject);
        printInvoice(deliveryObject);

    }

}