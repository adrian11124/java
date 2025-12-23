package controllers;

import java.util.ArrayList;
import java.util.List;

import Models.Huesped;
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
    public String[] getValuesHPD(Huesped hpd) {
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
    public List<String[]> getDataHPD() {
        List<String[]> listArray = new ArrayList<>();
        for (Huesped hbt : MainRunApp.listHuespeds) {
            listArray.add(getValuesHPD(hbt));
        }
        return listArray;
    }
}
