import java.util.ArrayList;
import java.util.Random;

public class Vehicle {
    
    private Double carCost;
    private String carName;
    private String carCondition;
    private String carCleanliness;
    private String carType;
    private int bonus;
    private double Sp;
    private ArrayList<Vehicle> v;
    private ArrayList<Vehicle> soldCarlist;
    private String carStatus;

    public Vehicle()
    {
       this.carName = getCarname();
      this.carCondition = determineCondition();
       this.carType = VehicleType();
       this.carCost = VehicleCost();
      this.carCleanliness = determineCleanliness();
      this.carStatus = "Stock";

      // System.out.println("i m in cnstructor2222");
    }
    
    public String VehicleName()
    {
        return carName;
    }

    public String getVehicleName()
    {
        return carName;
    }

    public Double VehicleCost()
    {
        return carCost;
    }

    public Double getVehicleCost()
    {
        return carCost;
    }

    public String VehicleCondition()
    {
        return carCondition;
    }
    public String VehicleCleanliness()
    {
        return carCleanliness;
    }
    public String VehicleType()
    {
        return carType;
    }

    public String VehicleStatus()
    {
       
     return carStatus;
    }

    public void setVehicleStatus(String carStatus)
    {
       
     this.carStatus = carStatus;
    }

    public String getCarname ()
    {
        final String[] FIRST_NAMES =  { "Tesla", "Ferrari", "Lamborghini", "Porsche", "Audi", "Bentley", "BMW",
        "Bugatti", "Mercedes", "Jaguar" };
         final String[] LAST_NAMES = { "XL", "XS", "SUV", "Sedan", "LUX", "Sumo", "Racer", "Sports", "Hatchback", "Sports", "EXL" };
   
      Random random = new Random();
      String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
      String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
      String fullcarname = firstName + " " + lastName ;
      return fullcarname;

    }
    
    public String determineCondition ()
    {
        final String[] cond =  { "Like New", "Used", "Broken"};
        Random rand = new Random();
        String condition = cond[rand.nextInt(cond.length)];
        return condition;
    }

    public String determineCleanliness ()
    {
        String[] cleanlinessStates = {"Sparkling", "Clean", "Dirty"};
        Random random = new Random();
            int randNum = random.nextInt(100);
        if (randNum < 5) {
            carCleanliness = cleanlinessStates[0];
        } else if (randNum < 40) {
            carCleanliness = cleanlinessStates[1];
        } else {
            carCleanliness = cleanlinessStates[2];
        }
        return carCleanliness;
    }
    
    public void setVehicleSP(Double Cp) {
        this.Sp = 2 * Cp;
    }

    public double getVehicleSP() {
        return Sp;
    }

    public void setCleanliness(String cleanliness) {
        this.carCleanliness = cleanliness;
    }

    public void setCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public void setCarPrice(Double carCost) {
        this.carCost = carCost;
    }

    public int VehicleWashBonus() {
        return bonus;
    }

    public int VehicleRepairBonus() {
        return bonus;
    }
    

    public void addSoldCarList(Vehicle mostExpensive)
    {
        
        soldCarlist.add(mostExpensive);
    }

    
    public ArrayList<Vehicle> getInventory()
    {
       
    return v; 
    }
}
