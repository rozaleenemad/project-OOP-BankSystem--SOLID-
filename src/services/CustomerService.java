package services;

import java.util.ArrayList;
import java.util.List;
import models.Customer;

public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    // إضافة عميل
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // جلب كل العملاء
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // تحديث بيانات العميل
    public void updateCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == customer.getCustomerID()) {
                customers.set(i, customer);
                break;
            }
        }
    }

    // حذف عميل
    public void deleteCustomer(int customerID) {
        customers.removeIf(c -> c.getCustomerID() == customerID);
    }

    // مثال لطباعة العملاء
    public void printCustomers() {
        for (Customer c : customers) {
            System.out.println(
                    "CustomerID: " + c.getCustomerID() +
                    " | Name: " + c.getFirstName() + " " + c.getLastName() +
                    " | Email: " + c.getEmail() +
                    " | Phone: " + c.getPhone() +
                    " | BranchID: " + c.getBranchID()
            );
        }
    }
}
