/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.server.json.JsonFile;

/**
 *
 * @author Stefan
 */
public class main {
    public static void main(String[] args) throws Exception {
     JsonFile jf=new JsonFile();
     
    //Ucitaj iz baze i upisi u fajl
    //jf.ucitajIzBazeStaniceISacuvaj("Stanice.txt");



//    Ucitaj iz fajla
//    List<Stanica> lista2=jf.ucitajSveStanice_TypeToken("Stanice.txt");
//    for (Stanica stanica : lista2) {
//        System.out.println(stanica.toString());
//    }
//    System.out.println("?????????????????????????");
        

// dodaj stanicu
//       Stanica s=new  Stanica(150, "dagsdg", new Mesto(125, "ASDGag"));
//        jf.sacuvajStanicu("Stanice.txt",  s);
        


    }
}
