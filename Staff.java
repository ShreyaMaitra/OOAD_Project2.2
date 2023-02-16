import java.util.Random;

// Below is an example of abstraction 

abstract class Staff {

    private double salary;
    private String fullname;
    public String type;
    private Double bonus = 0.0;
    private Double totalSalary =0.0;
    int noOfWorkingDays;
    String staffStatus;
   
    double bonusAmount;
    public Staff()
    {
       this.fullname = setStaffname();
       this.salary = determineStaffsalary();
       this.type = staffType();
       this.staffStatus = "Working";
    
    }

// Below Getter and setter methods defined for Encapsulation
// these methods are access in our code using below :
// Staff s = new Staff();
 // s.setStaffStatus("Working");
 // System.out.println("Name: " + s.getStaffStatus());
    public String getStaffStatus()
    {
        
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus)
    {
        this.staffStatus= staffStatus;
    }

    public String getStaffType( )
    {
        return type;
    }

    public void setStaffType(String type)
    {
        this.type= type;
    }

    public void setBonusAmount (double bonusAmount)
    {
        this.bonusAmount= bonusAmount;
    }

    public Double getBonusAmount ()
    {
        return bonusAmount;
    }

    public Double staffDailySalary()
    {
        return salary;
    }


    public double determineStaffsalary() {
        return salary;
    }

    // Below Getter and setter methods defined for Encapsulation
// these methods are access in our code using below :
// Staff s = new Staff();
 // System.out.println("Name: " + person.getStaffname());

 
    public String getStaffname()
    {
        return fullname;
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

  

    public String staffType()
    {
        return type;
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

    public void updateStaffBonus (Double bonus)
    { 
        this.bonus = this.bonus+ bonus;
    }

    public Double getTotalStaffBonus ()
    { 
        return bonus;
    }

    


}



