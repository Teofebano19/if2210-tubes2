/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelprof;

/**
 *
 * @author Andre
 */
public class LoginFailedException extends Exception{
    @Override
    public String getMessage(){
        return "Login telah gagal bro!";
    }
}
