/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Model {
    private List<Taxpayer> tax = new ArrayList<Taxpayer>();
    private List<Company> comps = new ArrayList<Company>();
    private List<CompanyOwner> compOwners = new ArrayList<CompanyOwner>();

    public void addTaxpayer(String name, String afm, int income){
        boolean taxExists = false;
        for(int i=0; i<tax.size(); i++)
        {
            if(name.matches(tax.get(i).getTaxName()))
            {
                JOptionPane.showMessageDialog(null, name + "has been already added !");
                taxExists = true;
                break;
            }
        }
        
        if(!taxExists)
        {
            tax.add(new Taxpayer(name, afm, income));
        }
    }

    private int checkCompany(String company, String addr){
        int val = -1;
        for(int i=0; i<comps.size(); i++)
        {
            if(company.matches(comps.get(i).getName()) && addr.matches(comps.get(i).getAddress()))
            {
                val = i;
                break;
            }
        }
        return val;
    }

    private int checkCompanyOwner(String owner, String afm){
        int val = -1;
        for(int i=0; i<compOwners.size(); i++)
        {
            if(owner.matches(compOwners.get(i).getName()) && afm.matches(compOwners.get(i).getVAT()))
            {
                val = i;
                break;
            }
        }
        return val;
    }
    public void setReceipt(String taxpayer, String rID, String rCateg, int rAmount, String company, String addr, String owner, String afm, int nComp){
        int companyExists = checkCompany(company, addr);
        int companyOwnerExists = checkCompanyOwner(owner, afm);

        for(int i=0; i< tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName()))
            {
                if(companyOwnerExists == -1 && companyExists == -1)
                {
                    CompanyOwner co = new CompanyOwner(owner, afm, nComp);
                    Company c = new Company(company, co, addr);
                    compOwners.add(co);
                    comps.add(c);
                    tax.get(i).addReceipt(rID, rCateg, rAmount, c);
                }
                else if(companyOwnerExists != -1 && companyExists == -1)
                {
                    Company c = new Company(company, compOwners.get(companyOwnerExists), addr);
                    comps.add(c);
                    tax.get(i).addReceipt(rID, rCateg, rAmount, c);
                }
                else
                {
                    tax.get(i).addReceipt(rID, rCateg, rAmount, comps.get(companyExists));
                }
                break;
            }
        }
    }

    public String[][] allReceipts(String taxpayer){
        String[][] allreceipts = new String[tax.size()][];
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName()))
            {
                allreceipts = tax.get(i).getReceipt();
                break;
            }
        }

        return allreceipts;
    }

    public String getAFM(String taxpayer){
        String afm = new String();
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName())){
                afm = tax.get(i).getAFM();
                break;
            }
        }

        return afm;
    }

    public int getIncome(String taxpayer){
        int income = 0;
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName())){
                income = tax.get(i).getIncom();
                break;
            }
        }

        return income;
    }

    public int getReceiptsSize(String taxpayer){
        int size = 0;
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName())){
                size = tax.get(i).getRecSize();
                break;
            }
        }

        return size;
    }

    public String[] allTaxpayers(){
        String[] allTaxp = new String[tax.size()];
        for(int i=0; i<tax.size(); i++)
        {
            allTaxp[i] = tax.get(i).getTaxName();
        }

        return allTaxp;
    }

    public double getTotalReceiptAmount(String taxpayer) {
        double amount = 0;
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName())){
                amount = tax.get(i).getReceiptAmount();
                break;
            }
        }
        return amount;
    }

    public double getAmountCategory(String taxpayer, String category) {
        double amount = 0;
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName())){
                amount = tax.get(i).getCategoryAmount(category);
                break;
            }
        }
        return amount;
    }

    public void removeTaxpayer(String taxpayer) {
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName())){
                tax.remove(i);
                break;
            }
        }
    }

    public String[] getAllReceiptID(String taxpayer){
        String[] allID = null;
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName())){
                allID = tax.get(i).getReceiptsID();
                break;
            }
        }
        return allID;
    }

    public void deleteReceipts(String taxpayer, String[] receipts){
        Company cor = null;
        for(int i=0; i<tax.size(); i++)
        {
            if(taxpayer.matches(tax.get(i).getTaxName()))
            {
                for(int j=0; j<receipts.length; j++)
                {
                    cor = tax.get(i).getReceiptCompany(receipts[j]);
                    tax.get(i).removeReceipt(receipts[j]);
                }
                break;
            }
        }

        int c = 0;
        for(int i=0; i<tax.size(); i++)
        {
            c += tax.get(i).getNumOfReceiptsWithSameCompany(cor);
        }

        if(c == 0)
        {
            comps.remove(cor);
            if(getNumOfCompsWithSameOwner(cor.getCompanyOwnerNm(), cor.getOwnerAFM()) == 0)
            {
                for(int i=0; i<compOwners.size(); i++)
                {
                    if(cor.getCompanyOwnerNm().matches(compOwners.get(i).getName()) && cor.getOwnerAFM().matches(compOwners.get(i).getVAT())){
                        compOwners.remove(i);
                        break;
                    }
                }
            }
        }
    }

    private int getNumOfCompsWithSameOwner(String owner, String afm){
        int c = 0;
        for(int i=0; i<comps.size(); i++)
        {
            if(owner.matches(comps.get(i).getCompanyOwnerNm()) && afm.matches(comps.get(i).getOwnerAFM()))
            {
                c++;
            }
        }
        return c;
    }

}
