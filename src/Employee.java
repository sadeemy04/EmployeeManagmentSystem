/*
Name: Sadeem Abduallah Abdulrahman Almalkie

 */
package employeemanagementsystem;
import java.util.*;
public abstract class Employee {
    private String name;
    private int ID;
    private double salary;
    private String state="working";
    private ArrayList<Leave>leaveRecords;
    
 
    public Employee(String name, int ID, double salary){
        this.name=name;
        this.ID=ID;
        this.salary=salary;
        leaveRecords=new ArrayList<Leave>();
    }
   
    public abstract double calculateBouns() ;
 
@Override    
public String toString(){
    return "employee name: "+name+" ID: "+ID+" salary: "+salary+" state: "+state;
}

//change the state of employee to leave
public void setStateOnLeave(){
    state="Leave";
    
}
//change the state of employee as working
public void setStateWorking(){
    state="Working";
}

//return employee's name
public String getName(){
    return name;
}


//return employee's ID
public int getId(){
    return ID;
}

//return the employee's salary
public double getSalary(){
    return salary;
}


//return the state of the employee
public String getState(){
    return state;
}


//this method add the leave object of the employee to the leave records
public void addLeaveRecord(Leave employee){
    leaveRecords.add(employee);
}

//this method return leave record
public ArrayList getLeaveRecords(){
    return leaveRecords;
}


}
