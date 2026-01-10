package controllers;

import java.util.ArrayList;
import java.util.List;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import Models.Huesped;
import Models.Reserva;
import bd.conexion;
import java.util.Objects;
/**
 *
 * @author adrian11124
 */
public class CtrHuesped {

    /**
     * genera una lista de Huesped
     * @return 
     */
    public List<Huesped> listHuesped(){
        List<Huesped> listHuesped = new ArrayList<>();
        String[] array = fileReadObject();
        for (String registro : array) {
            String[] atrs = registro.split(",");
            Huesped hpd = new Huesped();
            hpd.setId(Integer.valueOf(atrs[0]));
            hpd.setNombre(atrs[1]);
            hpd.setNumDocumento(Integer.valueOf(atrs[2]));
            listHuesped.add(hpd);
        }
        return listHuesped;
    }

    /**
     * type: Registrar Huesped
     * @return 
    */
    public String[] fileReadObject() {
        conexion cn = new conexion();
        return cn.fileReadObject("/bd/archivo.txt", 0);
    }

    /**
     * type: Registrar Huesped
     * @param listHuesped
    */
    public void saveProcess(List<Huesped> listHuesped){
        conexion cn = new conexion();
        cn.saveProcess(listHuesped, null, null, null);
    }
    /**
     * type: Registrar Huesped
     * @param nombre
     * @param documento
     * @return 
    */
    public String registrarHuesped(String nombre, String documento) {
        String message = "";
        List<Huesped> listHuesped = listHuesped();
        try {
            if (!nombre.equals("") && !documento.equals("")) {
                Huesped hp = new Huesped();
                hp.setId(listHuesped().size() + 1);
                hp.setNombre(nombre);
                hp.setNumDocumento(Integer.valueOf(documento));
                listHuesped.add(hp);
                saveProcess(listHuesped);
            } else {
                message = "Validar que los campos no esten vacios";
            }
        } catch (NumberFormatException e) {
            System.out.println("" + e);
        }
        return message;
    }

    /**
     * se toma una clase Huesped el cual termina siendo una cadena String, se procesa y se obtiene solo
     * los valores, agregados a un array
     * @param hpd
     * @return 
    */
    public String[] converHPDToArray(Huesped hpd) {
        String[] huesped = hpd.toString().split(",");
        String h = "";
        for (String atr : huesped) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    /**
     * genera lista de arrays(datos de huespes)  
     * @return 
    */
    public List<String[]> listOfArrayHPD() {
        List<String[]> listOfArray = new ArrayList<>();
        for (Huesped hpd : listHuesped()) {
            listOfArray.add(converHPDToArray(hpd));
        }
        return listOfArray;
    }

    /**
     * genera lista de arrays(datos de huespes) 
     * @param nameColumn
     * @param value
     * @return 
    */
    public List<String[]> searhHuesped(String nameColumn, Object value) {
        List<String[]> listSearh = new ArrayList<>();
        for (Huesped h : listHuesped()) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listSearh.add(converHPDToArray(h));
            }
        }
        return listSearh;
    }

    /**
     * actualiza un Huesped 
     * @param hpd
    */
    public void updateHuesped(Huesped hpd) {
        try {
            List<Huesped> listHuesped = listHuesped();
            for (Huesped h : listHuesped) {
                if (Objects.equals(h.getId(), hpd.getId())) {
                    h.setNumDocumento(hpd.getNumDocumento());
                    h.setNombre(hpd.getNombre());
                    saveProcess(listHuesped);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    /**
     * elimina huesped
     * @param documento
    */
    public void removeHuesped(String documento) {
        try {
            int i = 0;
            Integer parseDocument = Integer.valueOf(documento);
            CtrReserva ctr_reserva = new CtrReserva();
            List<Huesped> listHuesped = listHuesped();
            for (Huesped h : listHuesped) {
                if (h.getNumDocumento().equals(parseDocument)) {

                    boolean rh = false;
                    for (Reserva rsv : ctr_reserva.listReserva()) {

                        if (rsv.getHuesped().equals(documento)) {
                            JOptionPane.showMessageDialog(null, "El huesped tiene una reserva");
                            rh = true;
                        }
                    }
                    if (!rh) {
                        listHuesped.remove(i);
                        saveProcess(listHuesped);
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
     * genera el total de huesped existentes
     * @return 
     */
    public int cantidadHuesped() {
        return listHuesped().size();
    }

    /**
     * genera un array con los atributos del modelo
     * @return 
     */
    public String[] atributeTable(){
        String[] atribute = {"DOCUMENTO", "NOMBRE"};

        return atribute;
    }

    /**
     * genera busqueda por documento
     * @param document
     * @return 
     */
    public Huesped searhHuespedByDocument(String document){
        Huesped mdl = new Huesped();
        for (Huesped h : listHuesped()) {
            if (h.getNumDocumento().equals(Integer.valueOf(document))) {
                mdl = h;
                break;
            }
        }
        return mdl;
    }
}
