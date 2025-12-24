package models;

public class Branch {
    private int branchID;
    private String branchName;
    private String city;
    private String address;
    private int managerID;

    // Constructor
    public Branch(int branchID, String branchName, String city, String address, int managerID) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.city = city;
        this.address = address;
        this.managerID = managerID;
    }

    public Branch() {}

    // Getters & Setters
    public int getBranchID() { return branchID; }
    public void setBranchID(int branchID) { this.branchID = branchID; }
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getManagerID() { return managerID; }
    public void setManagerID(int managerID) { this.managerID = managerID; }

    // Methods placeholder
    public void displayBranch() {
        System.out.println("Branch: " + branchName + ", City: " + city);
    }
}
