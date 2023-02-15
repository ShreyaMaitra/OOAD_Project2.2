import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FNCD extends Buyer {

    public static void hireIntern (int c,ArrayList<Staff> s ,FileWriter writer) throws IOException
        {
            for (int i = c; i < 3; i++)
            {
            s.add(new Intern());
            writer.write("Hired "+ s.get(i).getStaffname()+"\n");
            System.out.println("Hired "+ s.get(i).getStaffname());
        }
        
        }
        
        public static double checkOperatingBudget (double operatingBudget, FileWriter writer) throws IOException
        {
           if (operatingBudget <0)
           {operatingBudget = operatingBudget+250000;
            writer.write(" ***  Adding operating budget of $250000   ***\n");
            System.out.println(" ***  Adding operating budget of $250000   ***\n");
        }
           
            return operatingBudget;
        }



        public static double addVehicle (int c,ArrayList<Vehicle> v , String type, double operatingBudget, FileWriter writer) throws IOException
        {
            double cost= 0;
            if ( type.equals("RegularCar"))
            {
            for (int i = c; i < 4; i++)
            {
                v.add(new RegularCars());
                cost = v.get(i).VehicleCost();
                operatingBudget = operatingBudget - cost;
                writer.write("Purchased "+ v.get(i).VehicleCondition()+" , "+v.get(i).VehicleCleanliness()+" "
                +v.get(i).VehicleType()+" for $"+ cost + "Cost\n");
                System.out.println("Purchased "+ v.get(i).VehicleCondition()+" , "+v.get(i).VehicleCleanliness()+" "
                +v.get(i).VehicleType()+" for $"+ cost + "Cost");
            }
        }
            if ( type.equals("PerformanceCar"))
            {
            for (int i = c; i < 4; i++)
            {
                v.add(new PerformanceCars());
                cost = v.get(i).VehicleCost();
                operatingBudget = operatingBudget - cost;
            }
        }
            if ( type.equals("PickUpCar"))
            {
            for (int i = c; i < 4; i++)
            {
                v.add(new PickUpCars());
                cost = v.get(i).VehicleCost();
                operatingBudget = operatingBudget - cost;
            }
        
        }
        
        return operatingBudget;
    }
       
    public static double opening(Double operatingBudget, ArrayList<Staff> s, ArrayList<Vehicle> v,FileWriter writer) throws IOException {
       writer.write("Opening....(current budget $"+operatingBudget+")\n");
        System.out.println("Opening....(current budget $"+operatingBudget+")");

        int counter =0;
        int ccounter= 0;
        int pcounter = 0;
        int ucounter =0 ;
        double val = operatingBudget;
        for (Staff person : s) {
             if (person.staffType().equals("Intern"))
              { 
                 counter++ ;
              }
        }
        if ( counter < 3)
        {
            hireIntern(counter, s, writer);
        }

        for (Vehicle car : v) {
            if (car.VehicleType().equals("RegularCar"))
              { 
                ccounter++ ;
              }
              if (car.VehicleType().equals("PerformanceCar"))
              { 
                pcounter++ ;
              }
              if (car.VehicleType().equals("PickUpCar"))
              { 
                ucounter++ ;
              }
        }

        if ( ccounter < 4)
        {     val= addVehicle(ccounter, v, "RegularCar",operatingBudget, writer)  ;      
        }
        
        if ( pcounter < 4)
        {     val=  addVehicle(ccounter, v, "PerformanceCar", operatingBudget, writer);
        }
        if ( ucounter < 4)
        {     val= addVehicle(ccounter, v, "PickUpCar", operatingBudget, writer);      
        }
        double val1 = checkOperatingBudget(val,writer);
        return val1;
}

public static double washing(double operatingBudget, ArrayList<Staff> s, ArrayList<Vehicle> v, FileWriter writer) throws IOException {
    writer.write("Washing....\n");
    System.out.println("Washing....");

    ArrayList<Vehicle> dirtyCars = new ArrayList<Vehicle>();
    ArrayList<Vehicle> cleanCars = new ArrayList<Vehicle>();
    
    for (Vehicle car : v) {
        if (car.VehicleCleanliness().equals("Dirty"))
        dirtyCars.add(car);
        if (car.VehicleCleanliness().equals("Clean"))
        cleanCars.add(car);
    }
    
    //System.out.println("dirtyCars size" + dirtyCars.size());
    //System.out.println("cleanCars size" + cleanCars.size());

    for (Staff person : s) {
        if (person.staffType().equals("Intern"))
       {   

        if (dirtyCars.size() != 0)
    {   Random random = new Random();
        Vehicle firstDirtyCar = dirtyCars.get(random.nextInt(dirtyCars.size()));
        double chanceDirty = random.nextDouble();
        if (chanceDirty < 0.8) {
            firstDirtyCar.setCleanliness("Clean");
        } else if (chanceDirty < 0.9) {
            firstDirtyCar.setCleanliness("Sparkling");
            person.wash(firstDirtyCar.VehicleWashBonus());
            person.updateStaffBonus(firstDirtyCar.VehicleWashBonus());
            person.updateTotalStaffSalary(person.getStaffsalary());
            person.updateWorkingDays();

            operatingBudget = operatingBudget -firstDirtyCar.VehicleWashBonus() - person.getStaffsalary();
            //interns earns based on car cleaned
            writer.write("Intern "+ person.getStaffname()+" washed "+ firstDirtyCar.VehicleType()+" "+
            firstDirtyCar.VehicleName()+" and made it Sparkling (earned $"+firstDirtyCar.VehicleWashBonus()+")\n");
            System.out.println("Intern "+ person.getStaffname()+" washed "+ firstDirtyCar.VehicleType()+" "+
            firstDirtyCar.VehicleName()+" and made it Sparkling (earned $"+firstDirtyCar.VehicleWashBonus()+")");
        }
    }
    if (cleanCars.size() != 0){
        Random random = new Random();
        Vehicle firstCleanCar = cleanCars.get(random.nextInt(cleanCars.size()));        
        double chanceClean = random.nextDouble();
        if (chanceClean < 0.05) {
            firstCleanCar.setCleanliness("Dirty");
        } else if (chanceClean < 0.7) {
            firstCleanCar.setCleanliness("Sparkling");
            person.wash(firstCleanCar.VehicleWashBonus());

            person.updateStaffBonus(firstCleanCar.VehicleWashBonus());
            person.updateTotalStaffSalary(person.getStaffsalary());
            System.out.println("Staff Salary "+person.getStaffsalary() );
            person.updateWorkingDays();
            operatingBudget = operatingBudget -firstCleanCar.VehicleWashBonus() - person.getStaffsalary();
            
            writer.write("Intern "+ person.getStaffname()+" washed "+ firstCleanCar.VehicleType()+" "+
            firstCleanCar.VehicleName()+" and made it Sparkling (earned $"+firstCleanCar.VehicleWashBonus()+")\n");
            System.out.println("Intern "+ person.getStaffname()+" washed "+ firstCleanCar.VehicleType()+" "+
            firstCleanCar.VehicleName()+" and made it Sparkling (earned $"+firstCleanCar.VehicleWashBonus()+")");
         }}
       }
    }

    double val = checkOperatingBudget(operatingBudget, writer);
    return val;
  }

public static double repairing(double operatingBudget, ArrayList<Staff> s, ArrayList<Vehicle> v,FileWriter writer ) throws IOException {
    
   // writer.write("Repairing....\n");
    System.out.println("Repairing....");

    ArrayList<Vehicle> repairCarsList = new ArrayList<Vehicle>();
    int cnt =0;
    
    for (Vehicle car : v) {
        if (car.VehicleCondition().equals("Broken") || car.VehicleCondition().equals("Used") )
       { 
        repairCarsList.add(car);
        }
    }
    
    System.out.println("repairCarsList size" + repairCarsList.size());

    for (Staff person : s) {
        
        if (person.staffType().equals("Mechanic"))
       {   
       
        if (repairCarsList.size() != 0)
    {   Random random = new Random();
        Vehicle firstrepairCar = repairCarsList.get(random.nextInt(repairCarsList.size()));
        double chanceRepair = random.nextDouble();
        double cost;
        if (chanceRepair < 0.8 && cnt < 2 ) {
            if (firstrepairCar.VehicleCondition().equals("Broken"))
             {
                firstrepairCar.setCondition("Used");
                //inc sp by 50%
                cost = firstrepairCar.VehicleCost();
                firstrepairCar.setCarPrice(cost *1.5);
                cnt ++;
                person.wash(firstrepairCar.VehicleRepairBonus());


                person.updateStaffBonus(firstrepairCar.VehicleRepairBonus());
                person.updateTotalStaffSalary(person.getStaffsalary());
                person.updateWorkingDays();

                operatingBudget = operatingBudget -firstrepairCar.VehicleRepairBonus() - person.getStaffsalary();
                
                writer.write("Mechanic "+ person.getStaffname()+" repaired Broken "+firstrepairCar.VehicleName()+
                " and made it "+firstrepairCar.VehicleCondition()+" (earned $"+firstrepairCar.VehicleRepairBonus()+")\n");
                System.out.println("Mechanic "+ person.getStaffname()+" repaired Broken "+firstrepairCar.VehicleName()+
                " and made it "+firstrepairCar.VehicleCondition()+" (earned $"+firstrepairCar.VehicleRepairBonus()+")");
                
                System.out.println("Cost current" +firstrepairCar.VehicleCost()); 
             }
            else if (firstrepairCar.VehicleCondition().equals("Used"))
            {
                firstrepairCar.setCondition("Like New");
                //inc sp by 25%
                cost = firstrepairCar.VehicleCost();
                firstrepairCar.setCarPrice(cost *1.25);
                cnt ++;
                person.wash(firstrepairCar.VehicleRepairBonus());

                person.updateStaffBonus(firstrepairCar.VehicleRepairBonus());
                person.updateTotalStaffSalary(person.getStaffsalary());
                person.updateWorkingDays();

                operatingBudget = operatingBudget -firstrepairCar.VehicleRepairBonus() - person.getStaffsalary();

                writer.write("Mechanic "+ person.getStaffname()+" repaired Used "+
                firstrepairCar.VehicleName()+" and made it "+firstrepairCar.VehicleCondition()+" (earned $"+
                firstrepairCar.VehicleRepairBonus()+")\n");

                System.out.println("Mechanic "+ person.getStaffname()+" repaired Used "+
                firstrepairCar.VehicleName()+" and made it "+firstrepairCar.VehicleCondition()+" (earned $"+
                firstrepairCar.VehicleRepairBonus()+")");

                System.out.println("Cost current" +firstrepairCar.VehicleCost()); 
            }
             if (firstrepairCar.VehicleCleanliness().equals("Sparkling"))
                { firstrepairCar.setCleanliness("Clean");}
                else if (firstrepairCar.VehicleCleanliness().equals("Clean"))
                { firstrepairCar.setCleanliness("Dirty");}     
            }
        }
    
       }
    }
    double val = checkOperatingBudget(operatingBudget, writer);
    return val;
  }

  public static double selling(Double operatingBudget, ArrayList<Staff> s, ArrayList<Vehicle> v, String dayOfWeek, ArrayList<Vehicle> invList , FileWriter writer) throws IOException
  {
    int numOfBuyers =0;
    String buyingIntend;
    Random random = new Random();
    if (dayOfWeek.equals("Friday") || dayOfWeek.equals("Saturday")) 
     { numOfBuyers = random.nextInt(7) + 2;
    }
     else {
         numOfBuyers = random.nextInt(6);
     }
     String desiredVehicle="";
     ArrayList<Vehicle> buyCarsList = new ArrayList<Vehicle>();
     ArrayList<Vehicle> buyInvCarsList = new ArrayList<Vehicle>();
     ArrayList<Buyer> Buyers = new ArrayList<Buyer>(numOfBuyers);
     Vehicle mostExpensive = new Vehicle();
     Vehicle SoldVehicle = new Vehicle();
     int randomNum = random.nextInt(100) + 1;  // generate a random number between 1 and 100
     for (Buyer b:Buyers){
     if (randomNum <= 10) {
         buyingIntend = "Just Looking";
     } else if (randomNum <= 50) {
         buyingIntend = "Wants One";
     } else {
         buyingIntend = "Needs One";
     }
    b.setBuyingIntention(buyingIntend);
    b.setDesiredVehicle();
    desiredVehicle = b.getDesiredVehicle();
          
      for (Vehicle car : v) {
        if (car.VehicleType().equals(desiredVehicle) && !car.VehicleCondition().equals("Broken") )
       { 
        buyCarsList.add(car);
        }
     }
   
        if (buyCarsList.size() != 0){
        mostExpensive = buyCarsList.get(0);  
        for (Vehicle buyCarsLst : buyCarsList) {
            if (buyCarsLst.getVehicleCost() > mostExpensive.getVehicleCost()) {
              mostExpensive = buyCarsLst;
                          }
        }
        SoldVehicle.addSoldCarList(mostExpensive);
        invList.remove(mostExpensive);
        }
        else { 
            for (Vehicle invcar : invList) {
                if (invcar.VehicleType().equals(desiredVehicle) && !invcar.VehicleCondition().equals("Broken") )
               { 
                buyInvCarsList.add(invcar);
                }
            }
            if (buyInvCarsList.size() != 0){
                mostExpensive = buyInvCarsList.get(0);  
                for (Vehicle buyInvCarsLst : buyInvCarsList) {
                    if (buyInvCarsLst.getVehicleCost() > mostExpensive.getVehicleCost()) {
                      mostExpensive = buyInvCarsLst;}
                    }
                      SoldVehicle.addSoldCarList(mostExpensive);
                      invList.remove(mostExpensive);
                      mostExpensive.setVehicleSP(mostExpensive.getVehicleCost());
                    
                
                }
          
        }
    }
    
    double operatingBudgetf = operatingBudget + mostExpensive.getVehicleSP();
    double val = checkOperatingBudget(operatingBudgetf, writer);
    return val;
    }
 

  public static void ending(double operatingBudget, ArrayList<Staff> s, ArrayList<Vehicle> v)
  {

    // pay Staff
    // Staff salary per day out of operating budget
    //
  }

}