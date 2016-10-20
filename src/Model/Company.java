/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


public class Company {
    private CompanyOwner owner ;
    private String name;
    private String address;

    public Company(String n, CompanyOwner nOwner, String addr) {
        name = n;
        address = addr;
        owner = nOwner;
    }

    public String getName() {
        return name;
    }

    public String getCompanyOwnerNm() {
        return owner.getName();
    }

    public CompanyOwner getCompanyOwner() {
        return owner;
    }

    public String getOwnerAFM() {
        return owner.getVAT();
    }

    public String getAddress() {
        return address;
    }
}
