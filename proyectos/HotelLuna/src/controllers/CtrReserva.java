package controllers;

import java.util.ArrayList;
import java.util.List;

import java.awt.HeadlessException;

import Models.Habitacion;
import Models.HabitacionVIP;
import Models.Reserva;
import hotel.MainRunApp;

/**
 *
 * @author adrian11124
 */
public class CtrReserva {

    /**
     *genera un array(reserva)
     * 
     */
    public String[] getValuesRSV(Reserva hpd) {
        String[] huesped = hpd.toString().split(",");
        String h = "";
        for (String atr : huesped) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    /**
     *genera reserva de habitacion
     *return: n/a
     */
    public static void reservarHabitacion(Reserva r) {
        MainRunApp.listReserva.add(r);

    }

    /**
     *genera reserva de 
     *return: n/a
     */
    public void generarReserva(String data) {
        Reserva r = new Reserva();
        String[] array_data = data.split(",");

        r.setId(cantidadRSV() + 1);
        r.setHuesped(array_data[0]);
        r.setNombreHuesped(array_data[1]);
        r.setVip(array_data[2]);
        r.setNumHabitacion(array_data[3]);
        r.setFechaIngreso(array_data[4]);
        r.setFechaSalida(array_data[5]);
        reservarHabitacion(r);
        if (r.getVip().equals("SI")) {
            for (Habitacion hbt : MainRunApp.listHBTVIP) {
                if (hbt.getNumero().equals(r.getNumHabitacion())) {
                    hbt.setEstado("Reservada");
                }
            }
        } else {
            for (Habitacion hbt : MainRunApp.listHBT) {
                if (hbt.getNumero().equals(r.getNumHabitacion())) {
                    hbt.setEstado("Reservada");
                }
            }
        }
    }

    /**
     *genera total de reservas
     *return: n/a
     */
    public static int cantidadRSV() {
        return MainRunApp.listReserva.size();
    }

    public List<String[]> getDataRSV() {
        List<String[]> listArray = new ArrayList<>();
        for (Reserva r : MainRunApp.listReserva) {
            listArray.add(getValuesRSV(r));
        }
        return listArray;
    }   

    public void removeRSV(String id) {
        try {
            int i = 0;
            Integer parseId = Integer.valueOf(id);
            for (Reserva r : MainRunApp.listReserva) {
                if (r.getId().equals(parseId)) {

                    boolean rh = false;
                    for (Habitacion h : MainRunApp.listHBT) {
                        if (h.getNumero().equals(r.getNumHabitacion())) {
                            h.setEstado("MANTENIMIENTO");
                            rh = true;
                            break;
                        }

                    }
                    for (HabitacionVIP h : MainRunApp.listHBTVIP) {
                        if (h.getNumero().equals(r.getNumHabitacion())) {
                            h.setEstado("MANTENIMIENTO");
                            rh = true;
                            break;
                        }

                    }
                    if (rh) {
                        MainRunApp.listReserva.remove(i);
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
     *genera un array con los atributos del modelo
     *return: String[]
     */
    public String[] atributeTable(){
        String[] atribute = {
                            "NUMERO HABITACION", "DOCUMENTO HUESPED", 
                            "NOMBRE HUESPED", "FECHA INGRESO", "FECHA SALIDA", "VIP"
                        };

        return atribute;
    }
}
