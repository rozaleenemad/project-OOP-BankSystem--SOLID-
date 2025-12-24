package models;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;
    private double salary;
    private int branchID;

    // Constructor
    public Employee(int employeeID, String firstName, String lastName, String email, String phone,
                    String position, double salary, int branchID) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.branchID = branchID;
    }

    public Employee() {}

    // Getters & Setters
    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public int getBranchID() { return branchID; }
    public void setBranchID(int branchID) { this.branchID = branchID; }

    // Methods placeholder
    public void displayEmployee() {
        System.out.println("Employee: " + firstName + " " + lastName + ", Position: " + position);
    }
}
