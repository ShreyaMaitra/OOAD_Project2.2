import java.util.Random;

public class Buyer {
   
    String buyingIntend ;
    String buyingAbility;
    String desiredVehicle;


    public String getBuyingIntention() {
        return buyingIntend;
    }
        
    public String setBuyingIntention(String buyingIntend2) {
        return buyingIntend;
        
    }
    public String getDesiredVehicle() {
        return desiredVehicle;
    }

    public String setDesiredVehicle()
    {   
       
        final String[] cond =  { "RegularCar", "PerformanceCar", "PickUpCar"};
        Random rand = new Random();
        String condition = cond[rand.nextInt(cond.length)];
        return condition;
    }
}

