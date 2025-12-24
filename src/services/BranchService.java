package services;

import java.util.ArrayList;
import java.util.List;
import models.Branch;

public class BranchService {

    private final List<Branch> branches = new ArrayList<>();

    // إضافة فرع
    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    // جلب كل الفروع
    public List<Branch> getAllBranches() {
        return branches;
    }

    // تحديث فرع
    public void updateBranch(Branch branch) {
        for (int i = 0; i < branches.size(); i++) {
            if (branches.get(i).getBranchID() == branch.getBranchID()) {
                branches.set(i, branch);
                break;
            }
        }
    }

    // حذف فرع
    public void deleteBranch(int branchID) {
        branches.removeIf(b -> b.getBranchID() == branchID);
    }

    // طباعة كل الفروع
    public void printBranches() {
        for (Branch b : branches) {
            System.out.println(
                    "BranchID: " + b.getBranchID() +
                    " | Name: " + b.getBranchName() +
                    " | City: " + b.getCity() +
                    " | Address: " + b.getAddress() +
                    " | ManagerID: " + b.getManagerID()
            );
        }
    }
}
