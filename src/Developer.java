/*
Name: Sadeem Abduallah Abdulrahman Almalkie

 */
package employeemanagementsystem;

public class Developer extends Employee {
    
    public Developer(String name,int ID,double salary){
        super(name,ID,salary);
    }
    //this method return the bouns based on the employee salary according to specefic ratio 12% because the employee is developer
    @Override
    public double calculateBouns(){
        double salaryAfterBouns;
        salaryAfterBouns=super.getSalary()*0.12;
        return salaryAfterBouns;
    }




}
