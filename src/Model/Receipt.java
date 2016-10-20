/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


public class Receipt {
    private Company company;
    private String id, category;
    private double amount;

    public Receipt(String rID, String rCateg, Company cn, int rAmount) {
        id = rID;
        category = rCateg;
        amount = rAmount;
        company = cn;
    }

    public String getReceiptID() {
        return id;
    }

    public String getCompanyName() {
        return company.getName();
    }

    public Company getCompany() {
        return company;
    }

    public String getCompanyOwnerName() {
        return company.getCompanyOwnerNm();
    }

    public CompanyOwner getOwner() {
        return company.getCompanyOwner();
    }

    public String getCompanyOwnerAFM() {
        return company.getOwnerAFM();
    }

    public String getCompanyAddr() {
        return company.getAddress();
    }

    public String getReceiptCategory() {
        return category;
    }

    public double getReceiptAmount() {
        return amount;
    }
    
}
