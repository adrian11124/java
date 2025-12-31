package controllers;

import java.util.ArrayList;
import java.util.List;

import Models.Habitacion;
import Models.HabitacionVIP;
import bd.conexion;
import bd.cn_habitacion;
import hotel.MainRunApp;

/**
 *
 * @author adrian11124
 */
public class CtrHabitacion {

    private static cn_habitacion cn = new cn_habitacion();

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
    public static String[] getValues(Object obj) {
        String[] habitacion = obj.toString().split(",");
        String cadena = "";
        for (String atr : habitacion) {
            String[] value = atr.split("=");
            cadena += value[1] + ",";
        }
        return cadena.substring(0, cadena.length() - 1).split(",");
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
        return cn.arrayData();
    }

    /**
    *------------------------------------------------------------
    *return: arreglo
    */
    public static List<Object> ArrayToObject(int id_table) {
        try {
            List<Object>  listHabitacion = new ArrayList<>();
            String[] arrHBT = conexion.fileReadObject("archivo.txt", id_table).split("_");
            for (String registro : arrHBT) {
                String[] atrs = registro.split(",");
                Habitacion hbt = new Habitacion();
                hbt.setId(Integer.valueOf(atrs[0]));
                hbt.setNumero(atrs[1]);
                hbt.setPrecio(Integer.valueOf(atrs[2]));
                hbt.setTipo(atrs[3]);
                hbt.setEstado(atrs[4]);
                listHabitacion.add(hbt);
            }
            return listHabitacion;

        } catch (NumberFormatException e) {
            System.out.println("No se pudo leer el archivo :: " + e);
            return null;
            
        }
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
        for (Habitacion h : cn.listHabitacion()) {
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

    public String[] atributeTable(){
        String[] atribute = {"# HABITACION",
            "PRECIO",
            "TIPO",
            "ESTADO"
        };

        return atribute;
    }
}
