/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.server.kontroler.Kontroler;

/**
 *
 * @author Stefan
 */
public class JsonFile {
        
        public void ucitajIzBazeStaniceISacuvaj(String json_file_name) throws Exception {
            ArrayList<Stanica> staniceIzBaze=Kontroler.getInstance().vratiMiSveStanice();
            try (FileWriter out =new FileWriter(json_file_name)){
			Gson gson=new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(staniceIzBaze,out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sacuvajStanicu(String json_file_name, Stanica stanica) {
            List<Stanica> stanice =ucitajSveStanice("Stanice.txt");
            stanice.add(stanica);
            try (FileWriter out =new FileWriter(json_file_name)){
			Gson gson=new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(stanice,out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
      public List<Stanica> ucitajSveStanice(String json_file_name) {
          List<Stanica> mestaLista=new ArrayList<>();
          try (FileReader in =new FileReader(json_file_name)){
			Gson gson=new GsonBuilder().create();
			Stanica[] niz=gson.fromJson(in, Stanica[].class);
			mestaLista=Arrays.asList(niz);
		} catch (IOException e) {
			e.printStackTrace();
		}
          return mestaLista;
	}
      
     
	public ArrayList<Stanica> ucitajSveStanice_TypeToken(String json_file_name) {
            ArrayList<Stanica> stanice=new ArrayList<>();
            try (FileReader in =new FileReader(json_file_name)){
			Gson gson=new Gson();
			Type collectionType = new TypeToken<ArrayList<Stanica>>()
			{}.getType();
			stanice=gson.fromJson(in, collectionType);
		} catch (IOException e) {
			e.printStackTrace();
		}
            return stanice;
	}
}
