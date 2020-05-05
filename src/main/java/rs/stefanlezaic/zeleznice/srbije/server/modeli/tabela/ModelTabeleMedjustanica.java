/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.modeli.tabela;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabeleMedjustanica extends AbstractTableModel {

    private ArrayList<MedjuStanica> list = new ArrayList<>();
    String[] kolone = {"Rr.Broj", "Medjustanica"};
    private final Class[] columnsType = new Class[]{Integer.class, MedjuStanica.class};

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
        MedjuStanica m = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getRedniBroj();
            case 1:
                return m.getStanica().getNaziv();
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
            default:
                return " ";
        }
    }

    public int vratiSizeListe() {
        return list.size() + 1;
    }

    public void dodajUTabelu(MedjuStanica ms) {
        list.add(ms);
        fireTableDataChanged();
    }

    public void obrisi(int red) {
        list.remove(red);
        for (MedjuStanica medjuStanica : list) {
            if (medjuStanica.getRedniBroj() > red + 1) {
                medjuStanica.setRedniBroj(medjuStanica.getRedniBroj() - 1);
            }

        }
        fireTableDataChanged();
    }

    public ArrayList<MedjuStanica> vratiListu() {
        return list;
    }

    public void izbrisiListu() {
        for (int i = list.size() - 1; i >= 0; i--) {
            obrisi(i);
        }

        fireTableDataChanged();

    }

    public ArrayList<MedjuStanica> getList() {
        return list;
    }

    public void setList(ArrayList<MedjuStanica> list) {
        this.list = list;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        MedjuStanica m = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                int broj = m.getRedniBroj();
                int noviBroj = (int) aValue;
                if (noviBroj <= 0 || noviBroj == broj || noviBroj > list.size()) {
                    break;
                }
                m.setRedniBroj(noviBroj);
                if (noviBroj > broj) {
                    for (MedjuStanica medjuStanica : list) {
                        if (!m.equals(medjuStanica) && medjuStanica.getRedniBroj() <= noviBroj && medjuStanica.getRedniBroj() > broj) {
                            medjuStanica.setRedniBroj(medjuStanica.getRedniBroj() - 1);
                        }
                        fireTableDataChanged();
                    }
                    return;
                }
                if (noviBroj == list.size()) {
                    for (MedjuStanica medjuStanica : list) {
                        if (!m.equals(medjuStanica) && medjuStanica.getRedniBroj() > broj) {
                            medjuStanica.setRedniBroj(medjuStanica.getRedniBroj() - 1);
                        }
                        fireTableDataChanged();
                    }
                    return;
                }
                if (noviBroj == 1) {
                    for (MedjuStanica medjuStanica : list) {
                        if (!m.equals(medjuStanica) && medjuStanica.getRedniBroj() < broj) {
                            medjuStanica.setRedniBroj(medjuStanica.getRedniBroj() + 1);
                        }
                        fireTableDataChanged();
                    }
                    return;
                }
                if (noviBroj < broj) {
                    m.setRedniBroj(noviBroj);
                    for (MedjuStanica medjuStanica : list) {
                        if (!m.equals(medjuStanica) && medjuStanica.getRedniBroj() >= m.getRedniBroj() && medjuStanica.getRedniBroj() < broj) {
                            medjuStanica.setRedniBroj(medjuStanica.getRedniBroj() + 1);
                        }
                        fireTableDataChanged();
                    }
                    return;
                }
            case 1:
                break;
            default:
                return;
        }

    }

    @Override
    public Class<?> getColumnClass(int column
    ) {
        return columnsType[column];
    }

    public void sortirajTabelu() {
        ArrayList<MedjuStanica> lista = list;
        for (MedjuStanica medjuStanica : lista) {
            for (MedjuStanica medjuStanica1 : lista) {
                if (medjuStanica.getRedniBroj() > medjuStanica1.getRedniBroj()) {
                    MedjuStanica m = medjuStanica1;
                    medjuStanica = medjuStanica1;
                    medjuStanica1 = m;
                }
            }
        }
        list = lista;
    }

}
