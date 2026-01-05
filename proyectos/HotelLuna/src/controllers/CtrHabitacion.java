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
        return arrayData();
    }

    /**
     *genera una lista de habitaciones(habitaciones normales)
     *return: List<Habitacion>
     */
    public List<Habitacion> listHabitacion(){
        return MainRunApp.listHBT;
    }

    /**
     *genera una lista de arrays
     *return: List<String[]>
     */
    public List<String[]> arrayData(){
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion registro : listHabitacion()) {
            listArray.add(getValues(registro));
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
        for (Habitacion h : listHabitacion()) {
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
    public List<String[]> buscarHBTVIP(String nameColumn, Object value) {
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
    public void actualizarHBTVIP(HabitacionVIP hbt) {
         for (HabitacionVIP h : MainRunApp.listHBTVIP) {
            if (h.getNumero().equals(hbt.getNumero())) {
                h.setNumero(hbt.getNumero());
                h.setPrecio(hbt.getPrecio());
                h.setEstado(hbt.getEstado());
                h.setTipo(hbt.getTipo());
                h.setTipo(hbt.getServiciosExtras());
                break;
            }
        }
    }

    /**
     *genera el total de habitaciones existentes
     *return: dato numerico(suma)
     */
    public int cantidadHabitaciones() {
        return MainRunApp.listHBT.size() + MainRunApp.listHBTVIP.size();
    }

    /**
     *genera un array con los atributos del modelo
     *return: String[]
     */
    public String[] atributeTable(){
        String[] atribute = {"# HABITACION",
            "PRECIO",
            "TIPO",
            "ESTADO"
        };

        return atribute;
    }
    /**
     *genera un array con los atributos del modelo
     *return: String[]
     */
    public String[] atributeTableTwo(){
        String[] atribute = {"# HABITACION",
            "PRECIO",
            "TIPO",
            "ESTADO",
            "SERVICIO EXTRA"
        };

        return atribute;
    }

    public Habitacion searhHabitacionByNumHabitacion(String numHabitacion){
        Habitacion mdl = new Habitacion();
        for (Habitacion h : getListHBT()) {
                if (h.getNumero().equals(numHabitacion)) {
                    mdl = h;
                    break;
                }
        }
        return mdl;
    }

    public HabitacionVIP searhHabitacionVIPByNumHabitacion(String numHabitacion){
        HabitacionVIP mdl = new HabitacionVIP();
        System.out.println("numero habitacion String :: "+numHabitacion+"\n\n");
        for (HabitacionVIP h : getListHBTVIP()) {
            System.out.println("\nRecorriendo Habitacion:: "+h.getNumero()+"\n\n");
                if (h.getNumero().equals(numHabitacion)) {
                    mdl = h;
                     
                    break;
                }
        }
        System.out.println("numero habitacion "+mdl.getNumero()+"\n\n");
        return mdl;
    }

    public String[] charOnes(String one){
        String[] charOnes = {one, "A", "B", "C", "D", "E", "F", "K", "M"};
        return charOnes;
    }

    public String[] arrayTipo(String tipoSelected){
        String[] tipo = {
                tipoSelected,
                "INDIVIDUAL",
                "DOBLE",
                "SUITE",
                "FAMILIAR",
                "PRESIDENCIAL",
                "ECONOMICA"};
        return tipo;
    }

    public String[] arrayEstado(String estadoSeleted){
        String[] estado = {
                estadoSeleted,
                "DISPONIBLE",
                "OCUPADA",
                "RESERVADA",
                "MANTENIMIENTO"
            };
        return estado;
    }
}
