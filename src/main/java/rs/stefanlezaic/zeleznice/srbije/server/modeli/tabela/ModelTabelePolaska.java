/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.modeli.tabela;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import rs.stefanlezaic.zeleznice.srbije.server.kontroler.Kontroler;

/**
 *
 * @author sleza
 */
public class ModelTabelePolaska extends AbstractTableModel {

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    ArrayList<Polazak> list = new ArrayList<>();
    String[] kolone = {"R.B", "Linija", "Datum polaska", "Datum dolaska", "Broj mesta", "Napomena"};
    private final Class[] columnsType = new Class[]{Polazak.class, String.class, Polazak.class, Polazak.class, String.class, String.class};

    @Override

    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Polazak p = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return p.getLinija().getNaziv();
            case 2:
                return sdf.format(p.getDatumPolaska());
            case 3:
                return sdf.format(p.getDatumDolaska());
            case 4:
                int broj;
                try {
                    broj = (int) Kontroler.getInstance().vratiRezervacijePolaska(new Rezervacija(null, p, new Date())).size();
                } catch (Exception ex) {
                    broj = 0;
                }
                return broj + "/" + p.getVoz().getBrojSedista();

            case 5:
                return p.getNapomena();
            default:
                return " ";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return kolone[column];
            case 1:
                return kolone[column];
            case 2:
                return kolone[column];
            case 3:
                return kolone[column];
            case 4:
                return kolone[column];
            case 5:
                return kolone[column];
            default:
                return " ";
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Polazak p = list.get(rowIndex);
        switch (columnIndex) {
            case 5:
                String napomena = (String) aValue;
                p.setNapomena(napomena);
                fireTableDataChanged();
                return;
            default:
                return;
        }

    }

    public int vratiSizeListe() {
        return list.size() + 1;
    }

    public void dodajUTabelu(Polazak p) {
        list.add(p);
        fireTableDataChanged();
    }

    public void obrisi(int red) {
        list.remove(red);
        fireTableDataChanged();
    }

    public ArrayList<Polazak> vratiListu() {
        return list;
    }

    public void izbrisiListu() {
        for (int i = list.size() - 1; i >= 0; i--) {
            obrisi(i);
        }
        fireTableDataChanged();
    }

    public void setList(ArrayList<Polazak> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public ArrayList<Polazak> getList() {
        return list;
    }

    @Override
    public Class<?> getColumnClass(int column
    ) {
        return columnsType[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 5) {
            return true;
        }
        return false;
    }

}
