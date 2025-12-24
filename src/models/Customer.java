package models;

import java.util.Date;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String nationalID;
    private String phone;
    private String email;
    private String address;
    private Date birthDate;
    private Date createdAt;

    // Constructor كامل
    public Customer(int customerID, String firstName, String lastName, String nationalID, String phone,
                    String email, String address, Date birthDate, Date createdAt) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
    }

    // Empty constructor
    public Customer() {}

    // Getters & Setters
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getNationalID() { return nationalID; }
    public void setNationalID(String nationalID) { this.nationalID = nationalID; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    // Print Customer Info
    public void printCustomerInfo() {
        System.out.println("Customer: " + firstName + " " + lastName + ", ID: " + nationalID);
    }

    public String getBranchID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBranchID'");
    }
}
