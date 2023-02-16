import java.util.Random;

// Below is an example of inheritance
public class PickUpCars extends Vehicle{
    public String VehicleType ()
    {
        return "PickUpCar";
    }

    public Double VehicleCost() // example of polymorphism
    {   
        Random r = new Random ();
        int min = 10000;
        int max = 40000;
        double carCost = r.nextInt(max-min) + min;
        return carCost;
    }

    public int VehicleWashBonus ()
    {
        return 300;
    }

    public int VehicleRepairBonus ()
    {
        return 350;
    }

    public int VehicleSaleBonus ()
    {
        return 2000;
    }
}
