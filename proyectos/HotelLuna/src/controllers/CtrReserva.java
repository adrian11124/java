package controllers;

import java.util.ArrayList;
import java.util.List;

import java.awt.HeadlessException;

import Models.Habitacion;
import Models.HabitacionVIP;
import Models.Reserva;
import bd.conexion;

/**
 *
 * @author adrian11124
 */
public class CtrReserva {

    /**
     * genera un lista de reserva
     * @return 
    */
    public List<Reserva> listReserva(){
        List<Reserva> listReserva = new ArrayList<>();
        String[] array = fileReadObject();
        for (String registro : array) {
            String[] atrs = registro.split(",");
            Reserva r = new Reserva();
            r.setId(Integer.valueOf(atrs[0]));
            r.setNumHabitacion(atrs[1]);
            r.setHuesped(atrs[2]);
            r.setNombreHuesped(atrs[3]);
            r.setVip(atrs[4]);
            r.setFechaIngreso(atrs[5]);
            r.setFechaSalida(atrs[6]);
            listReserva.add(r);
        }
        return listReserva;
    }

    public String[] fileReadObject() {
        conexion cn = new conexion();
        return cn.fileReadObject("/bd/archivo.txt", 3);
    }

    public void saveProcess(List<Reserva> listReserva){
        conexion cn = new conexion();
        cn.saveProcess(null, null, null, listReserva);
    }
    /**
     * genera un array(reserva)
     * @param hpd
     * @return 
     */
    public String[] getValues(Reserva hpd) {
        String[] huesped = hpd.toString().split(",");
        String h = "";
        for (String atr : huesped) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    /**
     * genera reserva de habitacion
     * @param r
     */
    public void reservarHabitacion(Reserva r) {
        List<Reserva> listReserva = listReserva();
        listReserva.add(r);
        saveProcess(listReserva);
    }

    /**
     * genera reserva de habitacion
     * @param data
     */
    public void createReserva(String data) {
        
        Reserva r = new Reserva();
        CtrHabitacion ctr_habitacion = new CtrHabitacion();
        String[] array_data = data.split(",");

        r.setId(cantidadr() + 1);
        r.setHuesped(array_data[0]);
        r.setNombreHuesped(array_data[1]);
        r.setVip(array_data[2]);
        r.setNumHabitacion(array_data[3]);
        r.setFechaIngreso(array_data[4]);
        r.setFechaSalida(array_data[5]);
        reservarHabitacion(r);
        if (r.getVip().equals("SI")) {
            List<HabitacionVIP> listHabitacionVip = ctr_habitacion.listHabitacionVIP();
            for (HabitacionVIP hbt : listHabitacionVip) {
                if (hbt.getNumero().equals(r.getNumHabitacion())) {
                    hbt.setEstado("Reservada");
                    ctr_habitacion.updateHabitacionVIP(hbt);
                    break;
                }
            }
        } else {
            List<Habitacion> listHabitacion = ctr_habitacion.listHabitacion();
            for (Habitacion hbt : listHabitacion) {
                if (hbt.getNumero().equals(r.getNumHabitacion())) {
                    hbt.setEstado("Reservada");
                    ctr_habitacion.updateHabitacion(hbt);
                    break;
                }
            }
        }
    }

    /**
     * genera total de reservas
     * @return 
     */
    public int cantidadr() {
        return listReserva().size();
    }

    /**
     * genera array de reservas
     * @return 
     */
    public List<String[]> listToArrayReserva() {
        List<String[]> listArray = new ArrayList<>();
        for (Reserva r : listReserva()) {
            listArray.add(getValues(r));
        }
        return listArray;
    }   

    /**
     * elimina reserva por id
     * @param id
     */
    public void removeReservaById(String id) {
        try {
            int i = 0;
            CtrHabitacion ctr_habitacion = new CtrHabitacion();
            List<Reserva> listReserva = listReserva();
            Integer parseId = Integer.valueOf(id);
            for (Reserva r : listReserva) {
                if (r.getId().equals(parseId)) {

                    boolean rh = false;
                    for (Habitacion h : ctr_habitacion.listHabitacion()) {
                        if (h.getNumero().equals(r.getNumHabitacion())) {
                            h.setEstado("MANTENIMIENTO");
                            rh = true;
                            break;
                        }

                    }
                    if(!rh){
                        for (HabitacionVIP h : ctr_habitacion.listHabitacionVIP()) {
                            if (h.getNumero().equals(r.getNumHabitacion())) {
                                h.setEstado("MANTENIMIENTO");
                                rh = true;
                                break;
                            }

                        }
                    }
                    if (rh) {
                        listReserva.remove(i);
                        saveProcess(listReserva);
                        break;
                    }

                }
                i++;
            }
        } catch (HeadlessException e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    /**
     * @return 
     */
    public List<String[]> listToArrayHuesped() {
        CtrHuesped ctr_huesped = new CtrHuesped();
        return ctr_huesped.listOfArrayHPD();
    }

    /**
     * @param nameColumn
     * @param value
     * @return 
     */
    public List<String[]> searhHuesped(String nameColumn, Object value) {
        CtrHuesped ctr_huesped = new CtrHuesped();
        return ctr_huesped.searhHuesped(nameColumn, value);
    }

    /**
     * @return 
     */
    public List<String[]> listToArrayDataVipDisponible() {
        CtrHabitacion ctr_habitacion = new CtrHabitacion();
        return ctr_habitacion.listToArrayDataVipDisponible();
    }
    
    /**
     * @return 
     */
    public List<String[]> listToArrayHabitacionDisponible() {
        CtrHabitacion ctr_habitacion = new CtrHabitacion();
        return ctr_habitacion.listToArrayHabitacionDisponible();
    }

    /**
     * @param nameColumn
     * @param value
     * @return 
     */
    public List<String[]> searchHabitacionVIP(String nameColumn, Object value) {
        CtrHabitacion ctr_habitacion = new CtrHabitacion();
        return ctr_habitacion.searchHabitacionVIP( nameColumn,  value);
    }

    /**
     * @param nameColumn
     * @param value
     * @return 
     */
    public List<String[]> searchHabitacion(String nameColumn, Object value) {
        CtrHabitacion ctr_habitacion = new CtrHabitacion();
        return ctr_habitacion.searchHabitacion( nameColumn,  value);
    }

    /**
     * @return 
     */
    public String[] atributeTableHuesped() {
        CtrHuesped ctr_huesped = new CtrHuesped();
        return ctr_huesped.atributeTable();
    }
    
    /**
     * @return 
     */
    public String[] atributeTableHabitacionVip() {
        CtrHabitacion ctr_habitacion = new CtrHabitacion();
        return ctr_habitacion.atributeTableTwo();
    }
    
    /**
     * @return 
     */
    public String[] atributeTableHabitacion() {
        CtrHabitacion ctr_habitacion = new CtrHabitacion();
        return ctr_habitacion.atributeTable();
    }

    

    /**
     * genera un array con los atributos del modelo
     * @return 
     */
    public String[] atributeTable(){
        String[] atribute = {
                            "NUMERO HABITACION", "DOCUMENTO HUESPED", 
                            "NOMBRE HUESPED", "FECHA INGRESO", "FECHA SALIDA", "VIP"
                        };

        return atribute;
    }
}
