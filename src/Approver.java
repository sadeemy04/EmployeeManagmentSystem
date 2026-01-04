
/*
Name: Sadeem Abduallah Abdulrahman Almalkie

 */

//Defines actions like approving leave and marking an employee as working
package employeemanagementsystem;
import java.time.LocalDate;
public interface Approver {
    
    
  public  void approveLeave(Employee employee,LocalDate date,int leaveDays);
  
  public  void markEmployeeAsWorking(Employee employee);    
    
}
