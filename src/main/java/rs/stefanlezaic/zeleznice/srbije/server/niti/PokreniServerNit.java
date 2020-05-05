/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sleza
 */
public class PokreniServerNit extends Thread {

    //kada mu se prikaci da ce mu jednu nist
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(8999);
            System.out.println("Server se pokrenuo!");
            //Prikaci se klijent beskonacno njih zato je while beskonacna petlja
            while (true) {
                Socket s = ss.accept();
                System.out.println("Klijent se povezao!");
                //kada mu se prikaci da ce mu jednu nit  
                ObradaKlijentskogZahtevaNit okzn = new ObradaKlijentskogZahtevaNit(s);
                okzn.start();
            }
        } catch (IOException ex) {
            System.out.println("Puko je server!");
            Logger.getLogger(PokreniServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
