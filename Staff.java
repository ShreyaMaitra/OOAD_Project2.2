import java.util.Random;

public class Staff {
    private double salary;
    private String fullname;
    public String type;
    //String = fullname;
    private Double bonus = 0.0;
    private Double totalSalary =0.0;
    int noOfWorkingDays;
    public Staff()
    {
       this.fullname = setStaffname();
       //System.out.println("i m in cnstructor111");
       this.salary = determineStaffsalary();
       this.type = staffType();
      //this.washBonus = washBonus;
      // System.out.println("i m in cnstructor2222");
    }


    public double determineStaffsalary() {
        return salary;
    }


    public String getStaffname()
    {
        return fullname;
    }

    public Double staffDailySalary()
    {
        return salary;
    }

    public String staffType()
    {
        return type;
    }

    public String setStaffname ()
    {
       // System.out.println("i m in method");
        final String[] FIRST_NAMES = { "Ava", "Emma", "Olivia", "Sophia", "Isabella", "Mia", "Charlotte",
        "Amelia", "Harper", "Evelyn" };
         final String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
        "Wilson", "Moore", "Taylor" };
   
      Random random = new Random();
      String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
      String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
      fullname = firstName + " " + lastName;
      return fullname;
    }

    public Double getStaffsalary ()
    { 
        return salary;  
    }

    public void updateWorkingDays ()
    { 
       
        noOfWorkingDays ++;  
    }

    public int getWorkingDays ()
    { 
       
        return noOfWorkingDays ;  
    }
    
    public void updateTotalStaffSalary (double totalSalary)
    { 
        this.totalSalary = this.totalSalary+ totalSalary;
    }

    public Double getTotalStaffSalary ()
    { 
        return totalSalary;
    }

    public void updateStaffBonus (int i)
    { 
        this.bonus = this.bonus+ i;
    }

    public Double getTotalStaffBonus ()
    { 
        return bonus;
    }

    public Double wash (double washBonus)
    {
        return washBonus;
    }

}



