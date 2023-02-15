
public class Salesperson extends Staff {
   
    public double determineStaffsalary ()
    {
        //Random random = new Random();
       // System.out.println("i m in salesperson");
       //staffType();
        double min = 80;
        double max = 100;
        double salary = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return salary;
    }
    public String staffType ()
    {
        return "Salesperson";
    }

    public Double getSale (String vehicleType)
    {
        double bonus= 0;;
        switch (vehicleType) {
            case "RegularCar":
            bonus = 1000.0;
            break;
            case "PickUpCar":
            bonus = 2000.0;
            break;
            case "PerformanceCar":
            bonus = 3000.0;
            break;
        }
        return bonus;
}
}

    

