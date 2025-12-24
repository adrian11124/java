package controllers;

import java.util.ArrayList;
import java.util.List;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import Models.Huesped;
import Models.Reserva;
import hotel.MainRunApp;
/**
 *
 * @author adrian11124
 */
public class CtrHuesped {

    /**
    *type: Registrar Huesped
    *return: String
    */
    public static String registrarHuesped(String nombre, String documento) {
        String message = "";
        try {
            if (!nombre.equals("") && !documento.equals("")) {
                Huesped hp = new Huesped();
                hp.setId(MainRunApp.listHuespeds.size() + 1);
                hp.setNombre(nombre);
                hp.setNumDocumento(Integer.valueOf(documento));
                MainRunApp.listHuespeds.add(hp);
            } else {
                message = "Validar que los campos no esten vacios";
            }
        } catch (NumberFormatException e) {
            System.out.println("" + e);
        }
        return message;
    }

    /**
    *se toma una clase Huesped el cual termina siendo una cadena String, se procesa y se obtiene solo
    *los valores, agregados a un array
    *return: String[]
    */
    public static String[] getValuesHPD(Huesped hpd) {
        String[] huesped = hpd.toString().split(",");
        String h = "";
        for (String atr : huesped) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    /**
    *genera lista de huesped
    *return: List<Huesped>
    */
    public List<Huesped> getListHPD() {
        return MainRunApp.listHuespeds;
    }

    /**
    *genera lista de arrays(datos de huespes) 
    *return: List<String[]> 
    */
    public static List<String[]> getDataHPD() {
        List<String[]> listArray = new ArrayList<>();
        for (Huesped hbt : MainRunApp.listHuespeds) {
            listArray.add(getValuesHPD(hbt));
        }
        return listArray;
    }

    /**
    *genera lista de arrays(datos de huespes) 
    *return: List<String[]> 
    */
    public static List<String[]> buscarHPD(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (Huesped h : MainRunApp.listHuespeds) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValuesHPD(h));
            }
        }
        return listBusqueda;
    }

    /**
    *actualiza un Huesped 
    *return: n/a
    */
    public void actualizarHPD(Huesped hpd) {
        try {
            for (Huesped h : MainRunApp.listHuespeds) {
                if (h.getNumDocumento().equals(hpd.getNumDocumento())) {
                    h.setNumDocumento(hpd.getNumDocumento());
                    h.setNombre(hpd.getNombre());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    public void removeHPD(String documento) {
        try {
            int i = 0;
            Integer parseDocument = Integer.valueOf(documento);
            for (Huesped h : MainRunApp.listHuespeds) {
                if (h.getNumDocumento().equals(parseDocument)) {

                    boolean rh = false;
                    for (Reserva rsv : MainRunApp.listReserva) {

                        if (rsv.getHuesped().equals(documento)) {
                            JOptionPane.showMessageDialog(null, "El huesped tiene una reserva");
                            rh = true;
                        }
                    }
                    if (!rh) {
                        MainRunApp.listHuespeds.remove(i);
                    }
                    break;
                }
                i++;
            }
        } catch (HeadlessException e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    /**
     *genera el total de huesped existentes
     *return: dato numerico(suma)
     */
    public int cantidadHuesped() {
        return MainRunApp.listHuespeds.size();
    }
}
