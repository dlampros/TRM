/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package References;

import Model.Model;
import java.io.File;


public abstract class References {

    public abstract void createReferenceFile(Model model, File file, String taxpayer, String req, String gath, String reduc, String penal, String[] perCateg);

}
