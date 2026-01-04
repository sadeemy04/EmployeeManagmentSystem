/*
Name: Sadeem Abduallah Abdulrahman Almalkie

 */
package employeemanagementsystem;
import java.time.LocalDate;

public class Manager extends Employee implements Approver {
    public Manager(String name,int ID, double salary){
        super(name,ID,salary);
    }

    //this method return the bouns based on the employee salary according to specefic ratio 15% because the employee is manager
@Override
public double calculateBouns(){
    double Bouns;
    Bouns=super.getSalary() *0.15;
    return Bouns;
}

//approve the leave for the employee
@Override
public void approveLeave(Employee employee,LocalDate date,int leaveDays){
    Leave leave=new Leave(employee,date,leaveDays); //creating new leave object for the employee mentioned on the parameters
    employee.addLeaveRecord(leave); //adding the leave object to the his/her leave record
    employee.setStateOnLeave(); //change his/her state to leave 
    
}

@Override
public void markEmployeeAsWorking(Employee employee){
    employee.setStateWorking(); // change employee state to working
}


}
