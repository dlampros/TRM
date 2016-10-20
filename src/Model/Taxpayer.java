/*
 * To change this template,  choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.List;


public class Taxpayer {
    private List<Receipt> receipts = new ArrayList<>();
    private String name, vat;
    private int income;

    public Taxpayer(String n, String afm, int inc) {
        name = n;
        vat = afm;
        income = inc;
    }

    public String getTaxName() {
        return name;
    }

    public String getAFM() {
        return vat;
    }

    public int getIncom() {
        return income;
    }

    public int getRecSize() {
        return receipts.size();
    }
    
/*
    private int checkCompany(String company, String addr){
        int c = -1;
        for(int i=0; i<receipts.size(); i++)
        {
            if(company.matches(receipts.get(i).getCompanyName()) && addr.matches(receipts.get(i).getCompanyAddr()))
            {
                c = i;
                break;
            }
        }
        return c;
    }

    private int checkCompanyOwner(String owner,  String afm){
        int cOwner = -1;
        for(int i=0; i<receipts.size(); i++)
        {
            if(owner.matches(receipts.get(i).getCompanyOwnerName()) && afm.matches(receipts.get(i).getCompanyOwnerAFM()))
            {
                cOwner = i;
                break;
            }
        }
        return cOwner;
    }
*/

    public void addReceipt(String rID, String rCateg, int rAmount, Company company) {
    	receipts.add(new Receipt(rID, rCateg, company, rAmount));
    }

    public String[][] getReceipt(){
        int size = receipts.size();
        String[][] allReceipts = new String[size][7];
        
        for(int i=0; i<receipts.size(); i++){
            allReceipts[i][0] = receipts.get(i).getReceiptID();
            allReceipts[i][1] = receipts.get(i).getCompanyName();
            allReceipts[i][2] = receipts.get(i).getCompanyOwnerName();
            allReceipts[i][3] = receipts.get(i).getCompanyOwnerAFM();
            allReceipts[i][4] = receipts.get(i).getCompanyAddr();
            allReceipts[i][5] = receipts.get(i).getReceiptCategory();
            allReceipts[i][6] = Double.toString(receipts.get(i).getReceiptAmount());
        }

        return allReceipts;
    }

    public double getReceiptAmount() {
        double amounts = 0;
        
        for(int i=0; i<receipts.size(); i++)
            amounts += receipts.get(i).getReceiptAmount();

        return amounts;
    }

    public double getCategoryAmount(String category) {
        double amounts = 0;
        
        for(int i=0; i<receipts.size(); i++) {
            if(category.matches(receipts.get(i).getReceiptCategory()))
                amounts += receipts.get(i).getReceiptAmount();
        }

        return amounts;
    }

    public String[] getReceiptsID() {
        int size = receipts.size();
        if(receipts.isEmpty())
            size = 1;
        
        String[] IDs = new String[size];
        if(receipts.isEmpty())
            IDs[0] = "";
        
        for(int i=0; i<receipts.size(); i++)
            IDs[i] = receipts.get(i).getReceiptID();
        
        return IDs;
    }

    public void removeReceipt(String reciepts) {
	    for(int j=0; j<receipts.size(); j++) {
	        if(reciepts.matches(receipts.get(j).getReceiptID())) {
	            receipts.remove(j);
	            break;
	        }
	    }
    }

    public Company getReceiptCompany(String receipt) {
        Company c = null;
        for(int i=0; i<receipts.size(); i++) {
            if(receipt.matches(receipts.get(i).getReceiptID())) {
                c = receipts.get(i).getCompany();
                break;
            }
        }
        return c;
    }

    public int getNumOfReceiptsWithSameCompany(Company company) {
        int num = 0;
        for(int i=0; i<receipts.size(); i++) {
            if(company.getName().matches(receipts.get(i).getCompanyName()) && company.getAddress().matches(receipts.get(i).getCompanyAddr())) {
                num++;
            }
        }
        return num;
    }
    
}
