/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import InputOutput.AntiParser;
import InputOutput.Parser;
import InputOutput.textAntiParser;
import InputOutput.textParser;
import InputOutput.xmlAntiParser;
import InputOutput.xmlParser;
import Model.Model;
import References.References;
import References.textReference;
import References.xmlReference;
import java.io.File;
import java.util.List;


public class Controller {
    private Model model;

    public Controller() {
        model = new Model();
    }

    public void loadFile(int TextXML, File file) {
        if(TextXML == 1) {
            Parser parser = new textParser();
            parser.parsing(model, file);
        }
        else {
            Parser parser = new xmlParser();
            parser.parsing(model, file);
        }
    }

    public void saveFile(int TextXML, File file, String taxpayer) {
        if(TextXML == 1) {
            AntiParser antiparser = new textAntiParser();
            antiparser.createFile(model, file, taxpayer);
        }
        else {
            AntiParser antiparser = new xmlAntiParser();
            antiparser.createFile(model, file, taxpayer);
        }
    }

    public String[] allIDs(String taxpayer) {
        return model.getAllReceiptID(taxpayer);
    }

    public String[] getTaxpayers() {
        return model.allTaxpayers();
    }

    public void addTaxpayer(String taxp, String vat, int inc) {
        model.addTaxpayer(taxp, vat, inc);
    }

    public void addReceipt(String taxpayer, String rID, String rCateg,int rAmount, String company, String addr, String owner, String afm, int nComp) {
        model.setReceipt(taxpayer, rID, rCateg, rAmount, company, addr, owner, afm, nComp);
    }

    public void deleteTaxpayer(String taxpayer) {
        model.removeTaxpayer(taxpayer);
    }

    public void deleteReceipts(String taxpayer, List<String> receipts) {
        String[] r = new String[receipts.size()];
        
        for(int i=0; i<receipts.size(); i++)
            r[i]=receipts.get(i).toString();
        
        model.deleteReceipts(taxpayer, r);
    }
    
    public String getTaxAFM(String taxpayer) {
        return model.getAFM(taxpayer);
    }

    public String getTaxIncome(String taxpayer) {
        return Integer.toString(model.getIncome(taxpayer));
    }

    public String getRequiredReceipts(String title) {
        String req = new String();
        
        if(model.getIncome(title) <= 6000)
            req = "0";
        else if(model.getIncome(title) > 6000 && model.getIncome(title) <= 12000)
            req = Double.toString(model.getIncome(title)*0.1);
        else if(model.getIncome(title) >= 12000)
            req = Double.toString(model.getIncome(title)*0.3);

        return req;
    }

    public String getReceiptsGathered(String title) {
        return Double.toString(model.getTotalReceiptAmount(title));
    }

    public String getTaxReduction(String title) {
        String reduction = new String();
        
        if(model.getIncome(title) <= 12000) {
            reduction = "0";
        }
        else if(model.getIncome(title) > 12000) {
            if(model.getTotalReceiptAmount(title) > model.getIncome(title)*0.3) {
                double amount = (model.getTotalReceiptAmount(title) - model.getIncome(title)*0.3)*0.1;
                reduction = Double.toString(amount);
            }
            else {
                reduction = "0";
            }
        }

        return reduction;
    }

    public String getTaxPenalty(String title) {
        String penalty = new String();
        
        if(model.getIncome(title) <= 12000) {
            penalty = "0";
        }
        else if(model.getIncome(title) > 12000) {
            if(model.getTotalReceiptAmount(title) < model.getIncome(title)*0.3) {
                double amount = (model.getIncome(title)*0.3 - model.getTotalReceiptAmount(title))*0.1;
                penalty = Double.toString(amount);
            }
            else {
                penalty = "0";
            }
        }

        return penalty;
    }

    public String getAmountForCategory(String title, String category) {
        return Double.toString(model.getAmountCategory(title,category));
    }

    public String[] getAmountPerCategory(String title) {
        String[] amountpercateg = new String[8];
        
        for(int i=0; i<8; i++)
            amountpercateg[i] = getAmountForCategory(title, Integer.toString(i + 1));

        return amountpercateg;
    }

    public void saveReferences(int TextXML, File file, String taxpayer) {
        String required = getRequiredReceipts(taxpayer);
        String gathered = getReceiptsGathered(taxpayer);
        String reduction = getTaxReduction(taxpayer);
        String penalty = getTaxPenalty(taxpayer);
        String[] amountPerCateg = getAmountPerCategory(taxpayer);
        
        if(TextXML == 1) {
            References refer = new textReference();
            refer.createReferenceFile(model, file, taxpayer, required, gathered, reduction, penalty, amountPerCateg);
        } 
        else {
            References refer = new xmlReference();
            refer.createReferenceFile(model, file, taxpayer, required, gathered, reduction, penalty, amountPerCateg);
        }
    }

}