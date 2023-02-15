public class Intern extends Staff{
 
    public double determineStaffsalary ()
    {
       // System.out.println("i m in intern");
       // staffType();
        double min = 50;
        double max = 80;
        double salary = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return salary;
    }

    public String staffType ()
    {
        return "Intern";
    }

    public Double wash (double washBonus)
    {
        return washBonus;
    }

}
