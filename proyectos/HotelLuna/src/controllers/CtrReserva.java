package controllers;

import java.util.ArrayList;
import java.util.List;

import Models.Habitacion;
import Models.Reserva;
import hotel.MainRunApp;

/**
 *
 * @author adrian11124
 */
public class CtrReserva {

    /**
     * 
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
    public void generarReserva(List<String> datosHuesped) {
        Reserva r = new Reserva();
        r.setId(cantidadRSV() + 1);
        r.setHuesped(datosHuesped.get(0));
        r.setNombreHuesped(datosHuesped.get(1));
        r.setVip(datosHuesped.get(2));
        r.setNumHabitacion(datosHuesped.get(3));
        r.setFechaIngreso(datosHuesped.get(4));
        r.setFechaSalida(datosHuesped.get(5));
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
    public int cantidadRSV() {
        return MainRunApp.listReserva.size();
    }

    public List<String[]> getDataRSV() {
        List<String[]> listArray = new ArrayList<>();
        for (Reserva r : MainRunApp.listReserva) {
            listArray.add(getValuesRSV(r));
        }
        return listArray;
    }   
}
