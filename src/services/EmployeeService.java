package services;

import java.util.ArrayList;
import java.util.List;
import models.Employee;

public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    // إضافة موظف
    public void addEmployee(Employee emp) {
        if (emp.getSalary() < 0) throw new IllegalArgumentException("Salary cannot be negative");
        employees.add(emp);
    }

    // جلب كل الموظفين
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // تحديث موظف
    public void updateEmployee(Employee emp) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID() == emp.getEmployeeID()) {
                employees.set(i, emp);
                break;
            }
        }
    }

    // حذف موظف
    public void deleteEmployee(int empID) {
        employees.removeIf(e -> e.getEmployeeID() == empID);
    }

    // طباعة كل الموظفين
    public void printEmployees() {
        for (Employee e : employees) {
            System.out.println(
                    "EmployeeID: " + e.getEmployeeID() +
                    " | Name: " + e.getFirstName() + " " + e.getLastName() +
                    " | Email: " + e.getEmail() +
                    " | Phone: " + e.getPhone() +
                    " | Position: " + e.getPosition() +
                    " | Salary: " + e.getSalary() +
                    " | BranchID: " + e.getBranchID()
            );
        }
    }
}
