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
  
//        AbstractGenericOperation abg=new SOVratiSvePolaskeZaDatumPocetnuKrajnuStanicu();
//        abg.templateExecute(new Polazak(-1, "", new Date(), null, new Linija(-1, null, -1, -1, new Stanica(-1, "ZEDNIK", null), new Stanica(-1, "VRBAS", null), null), null));
//         List<GeneralEntity> lista = ((SOVratiSvePolaskeZaDatumPocetnuKrajnuStanicu) abg).getPolasci();
//        for (GeneralEntity generalEntity : lista) {
//            Polazak p=(Polazak) generalEntity;
//            System.out.println(p.toString());
//        }
    }
}
