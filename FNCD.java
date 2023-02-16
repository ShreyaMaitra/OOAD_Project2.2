import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class FNCD extends Buyer {

    // method to add intern
    public static void hireIntern (int c,ArrayList<Staff> s ,FileWriter writer) throws IOException
        {
            for (int i = c; i < 3; i++)
            {
            s.add(new Intern());
            writer.write("Hired "+ s.get(i).getStaffname()+"\n");
            System.out.println("Hired "+ s.get(i).getStaffname());
        }
        
        }
        
        // tracking if operating budget is required and update operating budget accrdingly
        public static double checkOperatingBudget (double operatingBudget, FileWriter writer) throws IOException
        {
           if (operatingBudget <0)
           {operatingBudget = operatingBudget+250000;
            writer.write(" ***  Adding operating budget of $250000   ***\n");
            System.out.println(" ***  Adding operating budget of $250000   ***\n");
        }
           
            return operatingBudget;
        }


      // method to add vehicle
        public static double addVehicle (int c,ArrayList<Vehicle> v ,ArrayList<Vehicle> inventoryList, String type, double operatingBudget, FileWriter writer) throws IOException
        {
            double cost= 0;
            if ( type.equals("RegularCar"))
            {
            for (int i = c; i < 4; i++)
            {
                v.add(new RegularCars());
                inventoryList.addAll(v); // add new vehicles to the inventory list
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
       
    public static double opening(Double operatingBudget, ArrayList<Staff> s, ArrayList<Vehicle> v,ArrayList<Vehicle> inventoryList,FileWriter writer) throws IOException {
       writer.write("Opening....(current budget $"+operatingBudget+")\n");
        System.out.println("Opening....(current budget $"+operatingBudget+")");

        int counter =0;
        int ccounter= 0;
        int pcounter = 0;
        int ucounter =0 ;
        double val = operatingBudget;
        
        //check if number of interns are three or not
        for (Staff person : s) {
             if (person.getStaffType().equals("Intern"))
              { 
                 counter++ ;
              }
        }

        // if interns are less then hire intern
        if ( counter < 3)
        {
            hireIntern(counter, s, writer);
        }

        // Check vehicle count in inventory
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
        
        // if vehicle count is less then add vehicle
        if ( ccounter < 4)
        {     val= addVehicle(ccounter, v,inventoryList, "RegularCar",operatingBudget, writer)  ;      
        }
        
        if ( pcounter < 4)
        {     val=  addVehicle(ccounter, v, inventoryList,"PerformanceCar", operatingBudget, writer);
        }
        if ( ucounter < 4)
        {     val= addVehicle(ccounter, v,inventoryList, "PickUpCar", operatingBudget, writer);      
        }
        // checking operating budget
        double val1 = checkOperatingBudget(val,writer);
        return val1;
}

public static void washing( ArrayList<Staff> s, ArrayList<Vehicle> v, FileWriter writer) throws IOException {
    writer.write("Washing....\n");
    System.out.println("Washing....");

    ArrayList<Vehicle> dirtyCars = new ArrayList<Vehicle>();
    ArrayList<Vehicle> cleanCars = new ArrayList<Vehicle>();
    
    for (Vehicle car : v) {
        // Creating list of Dirty Cars
        if (car.VehicleCleanliness().equals("Dirty"))
        dirtyCars.add(car);
        // Creating list of Clean Cars
        if (car.VehicleCleanliness().equals("Clean"))
        cleanCars.add(car);
    }
    
    // Washing to be done by Interns
    for (Staff person : s) {
        if (person.staffType().equals("Intern"))
       {   

        if (dirtyCars.size() != 0)
    {   Random random = new Random();
        Vehicle firstDirtyCar = dirtyCars.get(random.nextInt(dirtyCars.size()));
        double chanceDirty = random.nextDouble();
        // Checking probability and updating cleanliness according
        if (chanceDirty < 0.8) {
            firstDirtyCar.setCleanliness("Clean");
        } else if (chanceDirty < 0.9) {
            firstDirtyCar.setCleanliness("Sparkling");
            
            //interns earns bonus based on car cleaned
            person.setBonusAmount(firstDirtyCar.VehicleWashBonus());
            person.setStaffStatus("Worked");
  
                   
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
         // Checking probability and updating cleanliness according
        if (chanceClean < 0.05) {
            firstCleanCar.setCleanliness("Dirty");
        } else if (chanceClean < 0.7) {
            firstCleanCar.setCleanliness("Sparkling");
            
            //interns earn bonus based on car cleaned
            person.setBonusAmount(firstCleanCar.VehicleWashBonus());
            person.setStaffStatus("Worked");
            
            writer.write("Intern "+ person.getStaffname()+" washed "+ firstCleanCar.VehicleType()+" "+
            firstCleanCar.VehicleName()+" and made it Sparkling (earned $"+firstCleanCar.VehicleWashBonus()+")\n");
            System.out.println("Intern "+ person.getStaffname()+" washed "+ firstCleanCar.VehicleType()+" "+
            firstCleanCar.VehicleName()+" and made it Sparkling (earned $"+firstCleanCar.VehicleWashBonus()+")");
         }}
       }
    }
  
  }

public static void repairing(ArrayList<Staff> s, ArrayList<Vehicle> v,FileWriter writer ) throws IOException {
    
   // writer.write("Repairing....\n");
    System.out.println("Repairing....");

    ArrayList<Vehicle> repairCarsList = new ArrayList<Vehicle>();
    int cnt =0;
    
    // Repair is done on Broken and Used Vehicles so creating the list of vehicles to be repaired
    for (Vehicle car : v) {
        if (car.VehicleCondition().equals("Broken") || car.VehicleCondition().equals("Used") )
       { 
        repairCarsList.add(car);
        }
    }
    
   // System.out.println("repairCarsList size" + repairCarsList.size());

    for (Staff person : s) {
        // Mechanics will perform the repai activity
        if (person.getStaffType().equals("Mechanic") )
       {   
       
        if (repairCarsList.size() != 0)
    {   Random random = new Random();
        Vehicle firstrepairCar = repairCarsList.get(random.nextInt(repairCarsList.size()));
        double chanceRepair = random.nextDouble();
        double cost;
         // Checking repair probability and cnt to count number of vehicles repaired as each Mechanic can repair just two vehicles
        if (chanceRepair < 0.8 && cnt < 2 ) {
            if (firstrepairCar.VehicleCondition().equals("Broken"))
             {
                // Updating car condition after repair
                firstrepairCar.setCondition("Used");
                //inc cost price of car
                cost = firstrepairCar.VehicleCost();
                firstrepairCar.setCarPrice(cost *1.5);
                cnt ++;

                //calculating bonus earned and daily salary
                person.setBonusAmount(firstrepairCar.VehicleRepairBonus());
                person.setStaffStatus("Worked");

                
                writer.write("Mechanic "+ person.getStaffname()+" repaired Broken "+firstrepairCar.VehicleName()+
                " and made it "+firstrepairCar.VehicleCondition()+" (earned $"+firstrepairCar.VehicleRepairBonus()+")\n");
                System.out.println("Mechanic "+ person.getStaffname()+" repaired Broken "+firstrepairCar.VehicleName()+
                " and made it "+firstrepairCar.VehicleCondition()+" (earned $"+firstrepairCar.VehicleRepairBonus()+")");
                
                System.out.println("Cost current" +firstrepairCar.VehicleCost()); 
             }
            else if (firstrepairCar.VehicleCondition().equals("Used"))
            {    // Updating car condition after repair
                firstrepairCar.setCondition("Like New");
                //inc cost price of car
                cost = firstrepairCar.VehicleCost();
                firstrepairCar.setCarPrice(cost *1.25);
                cnt ++;

                //calculating bonus earned and daily salary
                person.setBonusAmount(firstrepairCar.VehicleRepairBonus());
                person.setStaffStatus("Worked");
                

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

  }

  public static double selling(Double operatingBudget, ArrayList<Staff> s, ArrayList<Vehicle> v, String dayOfWeek, 
  ArrayList<Vehicle> invList , FileWriter writer) throws IOException
  {

    System.out.println("Selling....");
    int numOfBuyers =0;
    Random random = new Random();
    double SellingPrice=0;
    // Based on days of the week we determine the number of buyers
    if (dayOfWeek.equals("Friday") || dayOfWeek.equals("Saturday")) 
     { numOfBuyers = random.nextInt(7) + 2;
    }
     else {
         numOfBuyers = random.nextInt(6);
     }

     System.out.println("numOfBuyers" + numOfBuyers);
     String desiredVehicle="";
     ArrayList<Vehicle> buyCarsList = new ArrayList<Vehicle>();
     ArrayList<Vehicle> buyInvCarsList = new ArrayList<Vehicle>();
     Vehicle mostExpensive = new Vehicle();
     ArrayList<Vehicle> SoldVehicle = new ArrayList<Vehicle>();
     
     // Creating the list of buyers
     ArrayList<Buyer> Buyers = new ArrayList<Buyer>();
     for (int k = 0 ; k <numOfBuyers; k++)
     {Buyers.add(new Buyer());}
    
     for (Buyer b:Buyers)
     {
    desiredVehicle = b.getDesiredVehicle();
    // setting buying ability based buying intention
    if (b.getBuyingIntention().equals("Just Looking"))
    {b.setBuyingAbility(0.1);  }
    if (b.getBuyingIntention().equals("Wants One"))
    {b.setBuyingAbility(0.4);  }
    if(b.getBuyingIntention().equals("Needs One"))
    {b.setBuyingAbility(0.7);  }
     
       
    ArrayList<Staff> SalesStaff = new ArrayList<Staff>();
    for (Staff person : s) {
       // System.out.println(person.staffType());
        if (person.getStaffType().equals("Salesperson"))
       { //System.out.println("i m in sales person");
         SalesStaff.add(person);
         //System.out.println(SalesStaff.staffType());
        }
    }
        
       // randomly selecting a salesperson)
       Random rand = new Random();
       //Staff selectedSalesperson = SalesStaff.get(rand.nextInt(SalesStaff.size()));
       System.out.println("i am above index printing ..."+ SalesStaff.size());
       int index = rand.nextInt(SalesStaff.size());
       System.out.println("index printing ..." + index);
       Staff selectedSalesperson = SalesStaff.get(index);

     // creating the list of cars based on the buyers choice which are not broken 
      for (Vehicle car : v) {
        if (car.VehicleType().equals(desiredVehicle) && !car.VehicleCondition().equals("Broken") )
       { 
        buyCarsList.add(car);
        }
     }
   
     // find the most expensive car out of the created list of cars which are desired by the buyer
        if (buyCarsList.size() != 0){
        mostExpensive = buyCarsList.get(0);  
        for (Vehicle buyCarsLst : buyCarsList) {
            if (buyCarsLst.getVehicleCost() > mostExpensive.getVehicleCost()) {
              mostExpensive = buyCarsLst;
                          }
        }
     
        Random checkSale = new Random();
        double ifSold = checkSale.nextDouble() ;
          // System.out.println("i m here above if" + b.getBuyingIntention()+" "+ifSold);   

        if(b.getBuyingIntention().equals("Needs One") &&  ifSold < 0.7)
        {   System.out.println("i m here above if" + b.getBuyingIntention()+" "+ifSold);   
            b.setBuyingAbility(0.7);         
            SoldVehicle.add(mostExpensive); // Creating List of sold cars        
            v.remove(mostExpensive); // removing from list of vehicles
            // Updating the car status in inventory as sold
            for (Vehicle InvCarsLst : invList) {
                 if (InvCarsLst.getVehicleName().equals(mostExpensive.getVehicleName())) {
                    InvCarsLst.setVehicleStatus("Sold");
                    InvCarsLst.setVehicleSP(mostExpensive.getVehicleCost());
            }
            }
           
            selectedSalesperson.setBonusAmount(mostExpensive.VehicleSaleBonus());
            selectedSalesperson.setStaffStatus("Worked");
            mostExpensive.setVehicleSP(mostExpensive.getVehicleCost());
            SellingPrice = mostExpensive.getVehicleSP();
            writer.write("Salesperson "+ selectedSalesperson.getStaffname()+" sold "+mostExpensive.VehicleCondition() +" "+
            mostExpensive.VehicleType()+" "+ mostExpensive.VehicleName()+" to Buyer for $" + SellingPrice +" (earned $ "+
            mostExpensive.VehicleSaleBonus() +" bonus)\n");
        }
        else if (b.getBuyingIntention().equals("Wants One") && ifSold < 0.4)
        {   System.out.println("i m here above if" + b.getBuyingIntention()+" "+ifSold);   
            b.setBuyingAbility(0.4);  

            SoldVehicle.add(mostExpensive); // Creating List of sold cars        
            v.remove(mostExpensive); // removing from list of vehicles
            // Updating the car status in inventory as sold
            for (Vehicle InvCarsLst : invList) {
                 if (InvCarsLst.getVehicleName().equals(mostExpensive.getVehicleName())) {
                    InvCarsLst.setVehicleStatus("Sold");
                    InvCarsLst.setVehicleSP(mostExpensive.getVehicleCost());
            }
            }
           
            selectedSalesperson.setBonusAmount(mostExpensive.VehicleSaleBonus());
            selectedSalesperson.setStaffStatus("Worked");
            mostExpensive.setVehicleSP(mostExpensive.getVehicleCost());
            SellingPrice = mostExpensive.getVehicleSP();
            writer.write("Salesperson "+ selectedSalesperson.getStaffname()+" sold "+mostExpensive.VehicleCondition() +" "+
            mostExpensive.VehicleType()+" "+ mostExpensive.VehicleName()+" to Buyer for $" + SellingPrice +" (earned $ "+
            mostExpensive.VehicleSaleBonus() +"bonus)\n");
        }
        else if (b.getBuyingIntention().equals("Just Looking") && ifSold < 0.1)
        {   System.out.println("i m here above if" + b.getBuyingIntention()+" "+ifSold);   
            b.setBuyingAbility(0.1); 
            SoldVehicle.add(mostExpensive); // Creating List of sold cars        
            v.remove(mostExpensive); // removing from list of vehicles
            // Updating the car status in inventory as sold
            for (Vehicle InvCarsLst : invList) {
                 if (InvCarsLst.getVehicleName().equals(mostExpensive.getVehicleName())) {
                    InvCarsLst.setVehicleStatus("Sold");
                    InvCarsLst.setVehicleSP(mostExpensive.getVehicleCost());
            }
            }
           
            selectedSalesperson.setBonusAmount(mostExpensive.VehicleSaleBonus());
            selectedSalesperson.setStaffStatus("Worked"); 
            mostExpensive.setVehicleSP(mostExpensive.getVehicleCost());
            SellingPrice = mostExpensive.getVehicleSP();
            writer.write("Salesperson "+ selectedSalesperson.getStaffname()+" sold "+mostExpensive.VehicleCondition() +" "+
            mostExpensive.VehicleType()+" "+ mostExpensive.VehicleName()+" to Buyer for $" + SellingPrice +" (earned $ "+
            mostExpensive.VehicleSaleBonus() +" bonus)\n");
        }
        else { SellingPrice = 0;}
    
        
      
        }
        else { 

            // check for the available cars desired by the buyer in the inventory which are in stock
            for (Vehicle invcar : invList) {
                if (invcar.VehicleType().equals(desiredVehicle) && !invcar.VehicleCondition().equals("Broken") 
                && invcar.getVehicleStatus().equals("In Stock"))
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
                      
                    Random checkSale = new Random();
                    double ifSold = checkSale.nextDouble() ;
                          // checking chances of buying when checking from vehicle inventory
                    if(b.getBuyingIntention().equals("Needs One") &&  ifSold < (0.7*0.8))
                    {b.setBuyingAbility(0.7);         
                        SoldVehicle.add(mostExpensive); // Creating List of sold cars        
                        v.remove(mostExpensive); // removing from list of vehicles
                        // Updating the car status in inventory as sold
                        for (Vehicle InvCarsLst : invList) {
                             if (InvCarsLst.getVehicleName().equals(mostExpensive.getVehicleName())) {
                                InvCarsLst.setVehicleStatus("Sold");
                                InvCarsLst.setVehicleSP(mostExpensive.getVehicleCost());
                        }
                        }
                       
                        selectedSalesperson.setBonusAmount(mostExpensive.VehicleSaleBonus());
                        selectedSalesperson.setStaffStatus("Worked");
                        mostExpensive.setVehicleSP(mostExpensive.getVehicleCost());
                        SellingPrice = mostExpensive.getVehicleSP();
                        writer.write("Salesperson "+ selectedSalesperson.getStaffname()+" sold "+mostExpensive.VehicleCondition() +" "+
                        mostExpensive.VehicleType()+" "+ mostExpensive.VehicleName()+" to Buyer for $" + SellingPrice +" (earned $ "+
                        mostExpensive.VehicleSaleBonus() +" bonus)\n");
                    }
                    else if (b.getBuyingIntention().equals("Wants One") && ifSold < (0.4*0.8))
                    {b.setBuyingAbility(0.4);  
                        SoldVehicle.add(mostExpensive); // Creating List of sold cars        
                        v.remove(mostExpensive); // removing from list of vehicles
                        // Updating the car status in inventory as sold
                        for (Vehicle InvCarsLst : invList) {
                             if (InvCarsLst.getVehicleName().equals(mostExpensive.getVehicleName())) {
                                InvCarsLst.setVehicleStatus("Sold");
                                InvCarsLst.setVehicleSP(mostExpensive.getVehicleCost());
                        }
                        }
                       
                        selectedSalesperson.setBonusAmount(mostExpensive.VehicleSaleBonus());
                        selectedSalesperson.setStaffStatus("Worked");
                        mostExpensive.setVehicleSP(mostExpensive.getVehicleCost());
                        SellingPrice = mostExpensive.getVehicleSP();
                        writer.write("Salesperson "+ selectedSalesperson.getStaffname()+" sold "+mostExpensive.VehicleCondition() +" "+
                        mostExpensive.VehicleType()+" "+ mostExpensive.VehicleName()+" to Buyer for $" + SellingPrice +" (earned $ "+
                        mostExpensive.VehicleSaleBonus() +" bonus)\n");
                    }
                    else if (b.getBuyingIntention().equals("Just Looking") && ifSold < (0.1*0.8))
                    {b.setBuyingAbility(0.1); 
                        SoldVehicle.add(mostExpensive); // Creating List of sold cars        
                        v.remove(mostExpensive); // removing from list of vehicles
                        // Updating the car status in inventory as sold
                        for (Vehicle InvCarsLst : invList) {
                             if (InvCarsLst.getVehicleName().equals(mostExpensive.getVehicleName())) {
                                InvCarsLst.setVehicleStatus("Sold");
                                InvCarsLst.setVehicleSP(mostExpensive.getVehicleCost());
                        }
                        }
                       
                        selectedSalesperson.setBonusAmount(mostExpensive.VehicleSaleBonus());
                        selectedSalesperson.setStaffStatus("Worked"); 
                        mostExpensive.setVehicleSP(mostExpensive.getVehicleCost());
                        SellingPrice = mostExpensive.getVehicleSP();
                        writer.write("Salesperson "+ selectedSalesperson.getStaffname()+" sold "+mostExpensive.VehicleCondition() +" "+
                        mostExpensive.VehicleType()+" "+ mostExpensive.VehicleName()+" to Buyer for $" + SellingPrice +" (earned $ "+
                        mostExpensive.VehicleSaleBonus() +" bonus)\n");
                    }
                    else {SellingPrice = 0;}     

                       
                    }
        }
       
   
    }

    
    double operatingBudgetf = operatingBudget + SellingPrice;
    double val = checkOperatingBudget(operatingBudgetf, writer);
    return val;
    
}

  public static double ending(double operatingBudget, ArrayList<Staff> s, ArrayList<Staff> allStaffs, FileWriter writer) throws IOException
  { double perDaySalary = 0;
    for (Staff person : s) {
    if (person.getStaffStatus().equals("Worked"))
   {     
     person.updateStaffBonus(person.getBonusAmount());
     person.updateTotalStaffSalary(person.getStaffsalary());
     person.updateWorkingDays();
     perDaySalary = perDaySalary + person.getStaffsalary() + person.getBonusAmount();
     person.setStaffStatus("Working");
    }
  }
  int SalespersonQuit =0;
  int InternQuit =0;
  int MechanicQuit =0;
 
  // Randomly quiting
  Random rand = new Random();
  for (Staff person : allStaffs) {
    if (person.getStaffType().equals("Intern") && (rand.nextDouble() < 0.1) && InternQuit <1)
    {
        person.setStaffStatus("Quit"); // updating in staff type that they have quit
      s.remove(person); // removing from store staff list
     InternQuit++;
    writer.write(person.getStaffType()+" "+person.getStaffname()+" has quit the FNCD");}
   
    if (person.getStaffType().equals("Salesperson") && (rand.nextDouble() < 0.1) && SalespersonQuit <1 )
    { 
        person.setStaffStatus("Quit");
        s.remove(person);
        SalespersonQuit++;
        writer.write(person.getStaffType()+" "+person.getStaffname()+" has quit the FNCD");}
     
    if (person.getStaffType().equals("Mechanic") && (rand.nextDouble() < 0.1) && MechanicQuit <1)
    {
        person.setStaffStatus("Quit");
        s.remove(person);
        MechanicQuit++;

    writer.write(person.getStaffType()+" "+person.getStaffname()+" has quit the FNCD");}
 
  }

   // forming list of interns
   ArrayList<Staff> InternList = new ArrayList<>();
  
  for (Staff interns : s) 
  {     if (interns.getStaffType().equals("Intern"))
   { 
    InternList.add(interns);
  }
}

 //Removing teh interns list from Store Staff list
 for (Staff iL : InternList) 
 {s.remove(iL);
 }



  // adding from intern to Mechanics
  for (int i = 0; i < SalespersonQuit; i ++)
  {
    Staff mech = InternList.get(i);
    mech.setStaffType("Mechanic");
    InternList.remove(mech);
    s.add(mech);

  }

  // adding from intern to Sales person
  for (int i = 0; i < MechanicQuit; i ++)
  {
    Staff sale = InternList.get(i);
    sale.setStaffType("Salesperson");
    InternList.remove(sale);
    s.add(sale);

  }

  //adding if any remaining interns left
  for (Staff iL1 : InternList) 
 {s.add(iL1);
 }

  operatingBudget = operatingBudget - perDaySalary;

  //after paying the salary per day checking operating bonus
  double val = checkOperatingBudget(operatingBudget, writer);
 return val;
}
}