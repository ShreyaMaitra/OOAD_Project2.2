import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MonthlyWorkingStatisics {

   
    public static void main(String[] args) throws ParseException, IOException {
        FileWriter writer = new FileWriter("SimResults.txt", false); // storing the output in the file
        
        // Creating employees
        ArrayList<Staff> s = new ArrayList<>(); 
        for (int i = 0; i <3; i++)
        {  
            s.add(new Intern());
            s.add(new Mechanic());
            s.add(new Salesperson());
        }
        //List of all Staffs
        ArrayList<Staff> allStaffs = new ArrayList<>(); 
        allStaffs.addAll(s);

         // Add vehicle list to inventory 
         ArrayList<Vehicle> inventoryList = new ArrayList<>();  // example of Identity   
          for (int i = 0; i <10; i++)
          {  
            inventoryList.add(new RegularCars());
            inventoryList.add(new PickUpCars());
            inventoryList.add(new PerformanceCars());
          }
               
       // Based on condition of the cars reduce their Cost Price
          for (Vehicle car : inventoryList) {
            if (car.VehicleCondition().equals("Used"))            {               
                 car.setCarPrice(car.getVehicleCost()*0.8);            }
            if (car.VehicleCondition().equals("Broken"))            {
                car.setCarPrice(car.getVehicleCost()*0.5);            }
       
        }
        Collections.shuffle(inventoryList); // shuffling the order of vehicles stored in inventory    
        int pc =0;
        int puc =0;
        int rc =0 ;

        // Creating vehicle list consisting 4 of each type
        ArrayList<Vehicle> v = new ArrayList<Vehicle>(); // example of Identity 
        for (Vehicle cars : inventoryList) {
            if (cars.VehicleType().equals("PerformanceCar") && pc <4)
            {  v.add(cars);
                pc ++;
            }
            if (cars.VehicleType().equals("PickUpCar") && puc <4)
            {  v.add(cars);
                puc ++;
            }
            if (cars.VehicleType().equals("RegularCar") && rc <4)
            {  v.add(cars);
                rc ++;
            }
        
        }
        
        //Initialiszing starting budget
        Double operatingBudget = 500000.00;
        System.out.println("size " + s.size());
        //Running FNCD for 30 days
        for (int i = 1; i <31; i++)
        {  
           String dateString = "2023-01-"+i;
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           Date  date = dateFormat.parse(dateString);
           Calendar c = Calendar.getInstance();
           c.setTime(date);
           String dayWeekText = new SimpleDateFormat("EEEE").format(date);
       
        // Checking if Sunday or not as FNC is closed on Sunday
        if (dayWeekText.equals("Sunday"))
        {  
         writer.write("*** FNCD Day "+ i+" is closed on "+dayWeekText +"  ***\n");
         System.out.println("*** FNCD Day "+ i+" is closed on "+dayWeekText +"  ***");
        }
        else { 
            writer.write  ("*** FNCD Day "+ i+" "+dayWeekText+ " ***\n")  ;    
            System.out.println("*** FNCD Day "+ i+" "+dayWeekText+ " ***");   
            operatingBudget= FNCD.opening(operatingBudget, s, v, inventoryList, writer);
            FNCD.washing(s, v, writer);
            FNCD.repairing( s, v, writer);
            allStaffs.addAll(s);
            operatingBudget = FNCD.selling(operatingBudget, s, v, dayWeekText, inventoryList, writer);
            operatingBudget= FNCD.ending(operatingBudget, s, allStaffs , writer);
            writer.write("\n");
            writer.write("Name: \t      No Of Working Days:\t            Total Normal Salary:\t            Total Bonus: \t         Working Status :\n");
            for (Staff person : allStaffs) {
      
              writer.write(person.getStaffname()+"\t         "+person.getWorkingDays()+"\t                               "+person.getTotalStaffSalary()
              +"\t                      "  +person.getTotalStaffBonus()+"\t               "+person.getStaffStatus() + "\n");
              System.out.println("Name: " + person.getStaffname()+"  Total Normal Salary: " + person.getTotalStaffSalary()+
              "  Total Bonus: " + person.getTotalStaffBonus()+"  Total Days Worked: " + person.getWorkingDays());
            }  
           
            writer.write("\n");
            writer.write("Name: \t     Cost Price:\t            Sale Price:\t            Condition: \t         Cleanliness : \t     Status: \n");
            for (Vehicle car : v) {
                car.setVehicleSP(car.getVehicleCost());
              writer.write(car.getVehicleName()+"\t         "+car.getVehicleCost()+"\t           "+car.getVehicleSP()    +"\t            "  +car.VehicleCondition()+"\t              "+car.VehicleCleanliness() + "\t             "+car.getVehicleStatus()+ "\n");
             
            }  
            
            
       }
       
    }
   
           
      
      

        writer.close();
    }  
    }


