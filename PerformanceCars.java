import java.util.Random;

public class PerformanceCars extends Vehicle {

    public String VehicleType ()
    {
        return "PerformanceCar";
    }

    public int VehicleWashBonus ()
    {
        return 200;
    }

    public int VehicleRepairBonus ()
    {
        return 250;
    }

 
    public Double VehicleCost()
    {   
        Random r = new Random ();
        int min = 20000;
        int max = 40000;
        double carCost = r.nextInt(max-min) + min;       
        return carCost;
    }
}
