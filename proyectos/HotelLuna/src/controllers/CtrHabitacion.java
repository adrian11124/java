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
    public static String[] getValues(Object hbt) {
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
    public static List<String[]> getDataHBT() {
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
    public static List<String[]> getDataHBTDisponible() {
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
    public static List<String[]> getDataHBTVIP() {
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
    public static List<String[]> getDataHBTVIPDisponible() {
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
    public static List<String[]> buscarHBT(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (Habitacion h : MainRunApp.listHBT) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValues(h));
            }
        }
        return listBusqueda;
    }

    /**
     *genera lista de arrays(habitaciones VIP) que coinciden con el formato a buscar
     *return: List<String[]>
     */
    public static List<String[]> buscarHBTVIP(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (HabitacionVIP h : MainRunApp.listHBTVIP) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValues(h));
            }
        }
        return listBusqueda;
    }

    /**
     *actualiza una habitacion
     *return: n/a
     */
    public void actualizarHBT(Habitacion hbt) {
        for (Habitacion h : MainRunApp.listHBT) {
            if (h.getNumero().equals(hbt.getNumero())) {
                h.setNumero(hbt.getNumero());
                System.out.println("actualizarHBT__Precio::: " + hbt.getPrecio());
                h.setPrecio(hbt.getPrecio());
                h.setEstado(hbt.getEstado());
                h.setTipo(hbt.getTipo());
                break;
            }
        }
    }

    /**
     *actualiza una habitacion VIP
     *return: n/a
     */
    public void actualizarHBTVIP(HabitacionVIP vip) {
        try {
            for (HabitacionVIP h : MainRunApp.listHBTVIP) {
                if (h.getNumero().equals(vip.getNumero())) {
                    h.setNumero(vip.getNumero());
                    h.setPrecio(vip.getPrecio());
                    h.setEstado(vip.getEstado());
                    h.setTipo(vip.getTipo());
                    h.setServiciosExtras(vip.getServiciosExtras());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    /**
     *genera el total de habitaciones existentes
     *return: dato numerico(suma)
     */
    public int cantidadHabitaciones() {
        return MainRunApp.listHBT.size() + MainRunApp.listHBTVIP.size();
    }
}
