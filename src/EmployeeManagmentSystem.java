/*
Name: Sadeem Abduallah Abdulrahman Almalkie

 */
package employeemanagementsystem;
import java.lang.*;
import java.io.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.time.LocalDate;
public class EmployeeManagementSystem {
    
    private static ArrayList<Employee>employees=new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException {
      
        
        
         File inputFile=new File("inpuut.txt");       //making file object and assign it to the input file
         if(!inputFile.exists()){
             System.out.println("the input file dosnt exist");
             System.exit(1);
         }
        
         Scanner input=new Scanner(inputFile);               //scanner to read from the input file 
         
         File outputFile=new File("outpuuut.txt");           //file object and assign it to th output file
         
         PrintWriter output=new PrintWriter(outputFile);     //printwriter to write to the output file
        
        
         //while loop include all the if else statments of the program , finishis when all the command in the input file is finished
        while (input.hasNextLine()){
            String command=input.nextLine();             //read line by line in from the comman file and stor it in command object
            String []commandArray=command.split(",");    //split the command in array 
            
            
            
            
            
            //checks if the first index of ccommand array is refer to Add command
            if(commandArray[0].matches("Add[\\w]*")){
                int ID=Integer.parseInt(commandArray[2].trim());  //takes the employee id
               double salary=Double.parseDouble(commandArray[3].trim());   //take the salary
               
               
                //if-else statments to check which type of employee and add it 
                if(commandArray[0].matches("[\\w]*Designer")){
                    Designer addingNewDesigner=new Designer(commandArray[1].trim(),ID,salary);  
                    output.println(addEmployee(addingNewDesigner));
                    
                }
                else if(commandArray[0].matches("[\\w]*Developer")){
                    Developer addingNewDeveloper=new Developer(commandArray[1].trim(),ID,salary);
                    output.println(addEmployee(addingNewDeveloper));
                }
                else{
                    Manager addingNewManager=new Manager(commandArray[1].trim(),ID,salary);
                    output.println(addEmployee(addingNewManager));
                
                }
            
            }
           
            //checks if the first index of ccommand array is refer to give leave command
            else if(commandArray[0].matches("Give_Leave")){
                int managerID=Integer.parseInt(commandArray[1].trim());  //store manager id
                int employeeID=Integer.parseInt(commandArray[2].trim()); //store employee id
                 
                String date=commandArray[3].trim();   //store the date 
                String[] splitStartDate=date.split("-");   //split the days and month and year 
                int day=Integer.parseInt(splitStartDate[0]);
               int month=Integer.parseInt(splitStartDate[1]);
                int year=Integer.parseInt(splitStartDate[2]);
                
                LocalDate StartDate= LocalDate.of(day,month,year);  // local date object to store start date
               
                int days=Integer.parseInt(commandArray[4].trim());  //stores the days of leave for the employee
               
                
                output.println(approveLeave(managerID,employeeID,StartDate,days));  //write to the output file the leave approve of the employee
                
                
            }
            
            
            
            //this if-else bloch checks if the command is referring to change state
            else if(commandArray[0].matches("Change_State")){
                int managerID=Integer.parseInt(commandArray[1].trim());  //store manager id
                int employeeID=Integer.parseInt(commandArray[2].trim()); //store employee id
                output.println(markEmployeeAsWorking(managerID,employeeID)); //write to the output file the checke message of changing state to working+ send the details to changing state method
            }
            
            
            
            //this if-else bloch checks if the command is referring to delete employee  
            else if(commandArray[0].matches("del_Employee")){
                int employeeID=Integer.parseInt(commandArray[1].trim());  //store employee id
                output.println(removeEmployee(employeeID));  // write to the output file the checked message for removing employee + send the details to removing method
                
                
            }
            
            
            //this if-else block checks if the command is referring to printing the leave record
            else if(commandArray[0].matches("print_Leave_Records")){
                int employeeID=Integer.parseInt(commandArray[1]);   //store employee id
                output.println(printEmpolyeeLeaveRecords(employeeID)); // write to the output file the leave record +send it to leave record method
            }
        
            
            
            //this if-else block checks if the command is referring to print all employees
            else if (commandArray[0].equals("printAllEmployees")) {

                String employees=printAllEmployees();  //store the employee matrix 
                output.println(employees); //write it to the output file
            }
        
        
        }
      
        input.close();
        output.close();

    
    }

    
 //-------------------------------------------------------------------------------------------------------- 
    
//This method adds a new employee object to the employees ArrayList  
public static  String addEmployee(Employee employee){
         
          employees.add(employee); //add new employee
          String employeeType;
          
          
          //define the type of the employee adedd
          if(employee instanceof Designer){
              employeeType="Designer";
                 
          }
          else if(employee instanceof Developer){
              employeeType="Developer";
              
          }
          else
              employeeType="Manager";
          
          
          
          String addedSucssedMessage=employeeType+" "+employee.getName()+" "+"added."; //store checkd message of adding employee
          return addedSucssedMessage;
}

//--------------------------------------------------------------------------------------------------------------

//this method remove employee object by specefic employee ID from the Array List of the employee
public static String removeEmployee(int ID){
    
    Employee wantedEmployee=findEmployeeByID(ID); //store employee id
    if(wantedEmployee!=null){
        
       int indexOfWantedEmployee= employees.indexOf(wantedEmployee);  //store the index of the employee object
        employees.remove(indexOfWantedEmployee); //remove the employee
        return"Employee removed successfully.";
    }
    else
        return"Employee with ID "+ID+" not found.";

}

//----------------------------------------------------------------------------------------------------------------

//This method generates and returns a string that contains a formatted list of all employees in the system
public static String printAllEmployees(){
   String arrayString= ("Name            ID      Salary        Bouns");
    
    for(int i=0;i<employees.size();i++){
        Employee indexObject=employees.get(i);
       arrayString+= String.format("\n%-16s%-6d%-11.2f%11.2f ",indexObject.getName(),indexObject.getId(),indexObject.getSalary(),indexObject.calculateBouns());
       
    }
     return arrayString;

   }   



//----------------------------------------------------------------------------------------------------------------

// This method Generates and returns a string containing the leave records of a specific employee, identified by their ID.
public static String printEmpolyeeLeaveRecords(int ID){
    Employee employeeObj=findEmployeeByID(ID);  //searching for the specefic employee by id using find employee by id method
    
    //if employee found
    if(employeeObj!=null) {
          
     ArrayList <Leave> employeeLeaveRecord=employeeObj.getLeaveRecords();//getting the employee leave record and store it in array list of leave type
     String leaveRecord="Leave Records for "+employeeObj.getName()+":\n"; 
     leaveRecord+=String.format("%-15s %s\n","Start Date","Days of Leave");
     
     //loop to get the leave objects of the employee
     for(int i=0;i<employeeLeaveRecord.size();i++){
     Leave LeaveObject=employeeLeaveRecord.get(i);
     leaveRecord+=String.format("%-15s %d\n",LeaveObject.getStartDate(),LeaveObject.getDaysOfLeave()); //store it in variable with string format to write it to the file later
     
     }
     return leaveRecord;
     
}
else
        return("Employee with ID "+ID+" not found."); //if employee id not found
}


//--------------------------------------------------------------------------------------------------------------

//This method used internally to find and return an Employee object based on its ID.
private static Employee findEmployeeByID(int ID){
    //for loop to search to go into all employee objects in employees arrayList
    for(int i=0;i<employees.size();i++){
        //checking if the object in this round have the wanted id to return it and exit loop
        if(employees.get(i).getId()==ID){
            return employees.get(i);
        }
        
    
    
    }
    return null; //if the id not found

}
//--------------------------------------------------------------------------------------------------------------

//This method Facilitates the approval of leave requests. 
public static  String approveLeave(int managerID, int employeeID,LocalDate startDate,int days ){
     Employee manager=findEmployeeByID(managerID);//search for the employee (manager) by sending the id to find employee by id method
     Employee employee=findEmployeeByID(employeeID); //search for the employee  by sending the id to find employee by id method
     
         //if id's found
         if(manager!=null && employee!=null){
             //this if statment checks if the employee found is manager by making sure is instance of approver 
             if(manager instanceof Approver){
                 //checking if the state on working to assign it to leave
                 if(employee.getState().equalsIgnoreCase("Working")){
                     Leave employeeLeave=new Leave(employee,startDate,days);  //leave object contain employee details
                     employee.addLeaveRecord(employeeLeave); //adding the object to leave records of the employee
                     employee.setStateOnLeave();      //set employee state to leave
                     return ("Leave approved for "+employee.getName()+".");
                     
                     
                     
                 }
                  //if they are already on leave     
                 else{
                       return ("cannot approve Leave for "+employee.getName()+" as they are already on Leave.");
                     
                 }
             
             }       
            //if manager ID dosnt belong to the manager 
            else
            return ("Leave approval failed. Either manager ID is incorrect or employee ID does not exist.");
         
         
         
         }
        //if one of the id's not found
        else
        return ("Leave approval failed. Either manager ID is incorrect or employee ID does not exist.");
      

    }




//---------------------------------------------------------------------------------------------------------------

    //This method is used to update an employee s state to working after a leave period.
     public static String markEmployeeAsWorking(int managerID,int employeeID){
         Employee manager =findEmployeeByID(managerID); //search for the id by sending it to find employee by id method of the employee(manager) and store it
         Employee employee=findEmployeeByID(employeeID);//search for the id by sending it to find employee by id method of the employee and store it
         
         //if both id's found
         if(manager!=null && employee!=null){
             //checking if manager object belong to manager by checking if its instance of approver 
             if(manager instanceof Approver){
                 employee.setStateWorking(); //changes the state of the employee to working 
                 return("Employee "+employee.getName()+" is now marked as working."); //return checkd message
             }
             //if the id of the manager wasnt instance of approver thats mean is not manager
             else
             return("marking employee as working failed. Either manager ID is incorrect or employee ID does not exist.");
         
         
         }
         //if one or both id's not found
         else
             return("marking employee as working failed. Either manager ID is incorrect or employee ID does not exist.");  
     
     }

}
