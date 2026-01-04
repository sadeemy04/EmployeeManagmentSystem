/*
Name: Sadeem Abduallah Abdulrahman Almalkie

 */
package employeemanagementsystem;

public class Designer extends Employee {
    
    public Designer(String name,int ID,double salary){
        super(name,ID,salary);
    }
    //this method return the bouns based on the employee salary according to specefic ratio 10% because the employee is designer
    @Override
    public double calculateBouns(){
        double bouns;
        bouns=super.getSalary()*0.10;
        return bouns;
    }


}
