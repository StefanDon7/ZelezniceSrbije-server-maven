/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.main;


import rs.stefanlezaic.zeleznice.srbije.server.niti.PokreniServerNit;


/**
 *
 * @author Stefan
 */
public class Main {

    public static void main(String[] args) throws Exception {
        PokreniServerNit psn = new PokreniServerNit();
        psn.start();
  
    }
}
