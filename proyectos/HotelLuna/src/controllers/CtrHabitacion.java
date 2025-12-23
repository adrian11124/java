package controllers;

import java.util.ArrayList;
import java.util.List;

import Models.Habitacion;
import Models.HabitacionVIP;
import hotel.MainRunApp;

/**
 *
 * @author adrian11124
 */
public class CtrHabitacion {

    /**
     *Genera un array de habitaciones disponibles
     *return: String[]
     */
    public String[] opListHBT() {
        String list = "";
        for (Habitacion hbt : MainRunApp.listHBTVIP) {
            list += hbt.getEstado().equals("Disponible") ? hbt.getNumero() + "," : "";
        }
        return list.split(",");
    }

    /**
     *se toma un objeto el cual termina siendo una cadena String, se procesa y se obtiene solo
     *los valores, agregados a un array
     *return: String[]
     */
    public String[] getValues(Object hbt) {
        String[] habitacion = hbt.toString().split(",");
        String h = "";
        for (String atr : habitacion) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    /**
     *genera una union de habitacion normales con vip, en una sola lista
     *return: List<String[]>
     */
    public List<String[]> getDataHBTALL() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : MainRunApp.listHBT) {
            listArray.add(getValues(hbt));
        }
        for (HabitacionVIP hbt : MainRunApp.listHBTVIP) {
            listArray.add(getValues(hbt));
        }
        return listArray;
    }

    /**
     *genera una lista de arrays(habitaciones normales)
     *return: List<String[]>
     */
    public List<String[]> getDataHBT() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : MainRunApp.listHBT) {
            listArray.add(getValues(hbt));
        }
        return listArray;
    }

    /**
     *genera una lista de arrays(habitaciones) disponibles
     *return: List<String[]>
     */
    public List<String[]> getDataHBTDisponible() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : MainRunApp.listHBT) {
            if (hbt.getEstado().equals("DISPONIBLE")) {
                listArray.add(getValues(hbt));
            }

        }
        return listArray;
    }

    /**
     *genera una lista de arrays(Habitaciones) disponibles
     *return: List<String[]>
     */
    public List<String[]> getDataHBTVIP() {
        List<String[]> listArray = new ArrayList<>();
        for (HabitacionVIP vip : MainRunApp.listHBTVIP) {
            listArray.add(getValues(vip));
        }
        return listArray;
    }

    /**
     *genera una lista de arrays(Habitaciones VIP) disponibles
     *return: List<String[]>
     */
    public List<String[]> getDataHBTVIPDisponible() {
        List<String[]> listArray = new ArrayList<>();
        for (HabitacionVIP vip : MainRunApp.listHBTVIP) {
            if (vip.getEstado().equals("DISPONIBLE")) {
                listArray.add(getValues(vip));
            }
        }
        return listArray;
    }

    /**
     *genera una lista de habitaciones
     *return: List<Habitacion>
     */
    public List<Habitacion> getListHBT() {
        return MainRunApp.listHBT;
    }

    /**
     *genera una lista de habitaciones VIP
     *return: List<HabitacionVIP>
     */
    public List<HabitacionVIP> getListHBTVIP() {
        return MainRunApp.listHBTVIP;
    }

    /**
     *genera lista de habitaciones que coinciden con el formato a buscar
     *return: List<String[]>
     */
    public List<String[]> buscarHBT(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (Habitacion h : MainRunApp.listHBT) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValues(h));
            }
        }
        return listBusqueda;
    }
}
