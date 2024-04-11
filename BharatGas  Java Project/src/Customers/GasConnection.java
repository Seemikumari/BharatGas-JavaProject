package Customers;

import java.text.SimpleDateFormat;
import java.util.*;

public class GasConnection extends Customer {
    public int numberOfCylinders;
    String date;
    static int connectionNumber = 100;
    {
        connectionNumber += 1;
    }
    public Date lastDate = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public GasConnection(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile); //call the constructor of parent class
        this.numberOfCylinders = numberOfCylinders;
    }
    public void getLastDate(){
        System.out.println("Enter the last date");
        date = new Scanner(System.in).nextLine();
        try {
            lastDate = dateFormat.parse(date);
        }catch(Exception e){
            System.out.println("error in getLastDate: "+ e);
        }
    }
}
