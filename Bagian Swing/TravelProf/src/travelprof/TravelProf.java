/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelprof;

import javax.swing.UIManager;

/**
 *
 * @author Andre
 */
public class TravelProf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception x){
            
        }
        LoginForm login =  new LoginForm();
        login.show();
    }
    
}
