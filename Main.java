import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

   
    public static void main(String[] args) throws ParseException, IOException {
        FileWriter writer = new FileWriter("SimResults.txt", false);
        ArrayList<Staff> s = new ArrayList<>();
        ArrayList<Vehicle> v = new ArrayList<>();
        for (int i = 0; i <3; i++)
        {  
            //s.add(new Intern());
            s.add(new Mechanic());
            s.add(new Salesperson());
        }
         for (Staff person : s) {
            System.out.println("Name: " + person.getStaffname()+"  Salary: " + person.staffDailySalary()+
            "  Type: " + person.staffType());
          }

          
          for (int i = 0; i <4; i++)
          {  
              v.add(new RegularCars());
              v.add(new PickUpCars());
              v.add(new PerformanceCars());
          }
          
         
       
          for (Vehicle car : v) {
            
            if (car.VehicleCondition().equals("Used"))
            {
                car.setCarPrice(car.getVehicleCost()*0.8);
            }
            if (car.VehicleCondition().equals("Broken"))
            {
                car.setCarPrice(car.getVehicleCost()*0.5);
            }
            
                System.out.println("Car Name: " + car.VehicleName()+"  Condition: " + car.VehicleCondition()+
                "  Car Cleanliness: " + car.VehicleCleanliness()+"  Car Type: " + car.VehicleType() + "  Car Cost: " + car.getVehicleCost());
                
        }
              
        ArrayList<Vehicle> inventoryList = new ArrayList<Vehicle>();
        inventoryList.addAll(v); //// add to inventory  
                         
        Double operatingBudget = 500000.00;
        //for (int i = 1; i <31; i++)
        for (int i = 1; i <11; i++)
        {  
           String dateString = "2023-01-"+i;
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           Date  date = dateFormat.parse(dateString);
           Calendar c = Calendar.getInstance();
           c.setTime(date);
           String dayWeekText = new SimpleDateFormat("EEEE").format(date);
       
        if (dayWeekText.equals("Sunday"))
        {  
         writer.write("*** FNCD Day "+ i+" is closed on "+dayWeekText +"  ***\n");
         System.out.println("*** FNCD Day "+ i+" is closed on "+dayWeekText +"  ***");
        }
        else { 
            writer.write  ("*** FNCD Day "+ i+" "+dayWeekText+ " ***\n")  ;    
            System.out.println("*** FNCD Day "+ i+" "+dayWeekText+ " ***");   
            operatingBudget= FNCD.opening(operatingBudget, s, v, writer);
            operatingBudget= FNCD.washing(operatingBudget, s, v, writer);
            FNCD.repairing(operatingBudget, s, v,writer);
            operatingBudget = FNCD.selling(operatingBudget, s, v, dayWeekText, inventoryList, writer);
           // FNCD.ending(operatingBudget, s, v, dayWeekText);
           // System.out.println(operatingBudget);     
       }
       
    }
    for (Vehicle car : v) {
  
        System.out.println("Car Name: " + car.VehicleName()+"  Condition: " + car.VehicleCondition()+
         "  Car Cleanliness: " + car.VehicleCleanliness()+"  Car Type: " + car.VehicleType() + "  Car Cost: " + car.getVehicleCost());
      }


           writer.write("Name: \t          Total Normal Salary:\tTotal Bonus:\t No Of Working Days: \n");
      for (Staff person : s) {

        writer.write(person.getStaffname()+"\t          "+person.getTotalStaffSalary()+"\t             "+person.getTotalStaffBonus()
        +"\t           " +person.getWorkingDays()+"\n");
        System.out.println("Name: " + person.getStaffname()+"  Total Normal Salary: " + person.getTotalStaffSalary()+
        "  Total Bonus: " + person.getTotalStaffBonus()+"  No Of Working Days: " + person.getWorkingDays());
      }
        
        writer.close();
    }  
    }


