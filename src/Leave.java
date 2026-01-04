/*
Name: Sadeem Abduallah Abdulrahman Almalkie

 */
package employeemanagementsystem;
import java.time.*;

public class Leave {
    private Employee employee;
    private LocalDate startDate;
    private int daysOfLeave;
    
    
    public Leave(Employee employee,LocalDate startDate,int daysOfLeave){
        this.employee=employee;
        this.startDate=startDate;
        this.daysOfLeave=daysOfLeave;
    }
   
    //this method return the employee object 
    public Employee getEmployee(){
        return employee;
    }
   
    
    //this method return the start date
    public LocalDate getStartDate(){
        return startDate;
    }
   
    //this method return days of leave for the employee
    public int getDaysOfLeave(){
        return daysOfLeave;
    }
    
    //this method display the leave details (start date) and (days of leave) of the employee 
    public void displayLeaveDetails(){
        
        System.out.println(startDate+"      "+daysOfLeave);
               
    }



}
