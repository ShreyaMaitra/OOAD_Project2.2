# OOAD_Project2.2

# Team Members : Shaily Goyal, Shreya Maitra

<h2>Java Version :17.0.6</h2>

<h1>Changes made compare to previous UML are following:</h1>

<h3>Vehicle class:</h3> datatype added: carCleanliness(),carType(),carStatus() and 
            methods added: VehicleWashBonus(),VehicleRepairBonus(),VehicleSaleBonus()
      
<h3>Buyer class:</h3> methods added: getBuyingAbility(),getBuyingIntention(),getDesiredVehicle()

<h3>PerformanceCars class, RegularCars class, PickupCars class:</h3> methods added: VehicleWashBonus(), VehicleRepairBonus(), VehicleSaleBonus()

<h3>Staff class:</h3> method added: StaffType()

<h3>Salesperson class, Mechanic class, Intern class:</h3> methods added: StaffType(),determineStaffSalary()

<h3>FNCD class:</h3> methods added: hireIntern(), checkOperatingBudget()

<h2>Below is the updated UML daigram:</h2>

![image](https://user-images.githubusercontent.com/59019087/219286539-db090aa6-f1c0-4310-9371-e3af69a280a6.png)

# UML Changes
We are only considering Staff as the abstract class . In the previous UML we had considered Vehicle also as an abstract class.

# Assumptions : 

We are starting the simulation considering that there are three types of staffs of each type Intern, Salerperson, Mechanic. 
Also we are considering that the inventory has 10 Vehicles of each type. The store vehicle stock has 4 of each types of Cars i.e. Regular Cars, Pick Up Cars, Performance Cars. Also we are considering that one sales person can sell multiple cars. Also it is likely that one of each type of Mechanic, Salesperson and Intern can quit. 

<h2>References:</h2>
<h3>https://www.w3schools.com/</h3>
<h3>https://www.geeksforgeeks.org/</h3>
<h3>https://www.tutorialspoint.com/</h3>
<h3>https://www.javatpoint.com/ </h3>
