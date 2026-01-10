package controllers;

import java.util.ArrayList;
import java.util.List;

import Models.Habitacion;
import Models.HabitacionVIP;
import bd.conexion;

/**
 *
 * @author adrian11124
 */
public class CtrHabitacion {

    /**
     * @return 
     * genera una lista de habitaciones(habitaciones normales)
     */
    public List<Habitacion> listHabitacion(){
        List<Habitacion> listHabitacion = new ArrayList<>();
        String[] array = fileReadObjectOne();
        for (String registro : array) {
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
    }

    /**
     * @return
     * genera una lista de habitaciones(habitaciones VIP)
     */
    public List<HabitacionVIP> listHabitacionVIP(){
        List<HabitacionVIP> listHabitacionVIP = new ArrayList<>();
        String[] array = fileReadObjectTwo();
        for (String registro : array) {
            String[] atrs = registro.split(",");
            HabitacionVIP hbt = new HabitacionVIP();
            hbt.setId(Integer.valueOf(atrs[0]));
            hbt.setNumero(atrs[1]);
            hbt.setPrecio(Integer.valueOf(atrs[2]));
            hbt.setTipo(atrs[3]);
            hbt.setEstado(atrs[4]);
            hbt.setServiciosExtras(atrs[5]);
            listHabitacionVIP.add(hbt);
        }

        return listHabitacionVIP;
    }

    public String[] fileReadObjectOne() {
        conexion cn = new conexion();
        return cn.fileReadObject("/bd/archivo.txt", 1);
    }
    public String[] fileReadObjectTwo() {
        conexion cn = new conexion();
        return cn.fileReadObject("/bd/archivo.txt", 2);
    }

    public void saveProcessOne(List<Habitacion> listHabitacion){
        conexion cn = new conexion();
        cn.saveProcess(null, listHabitacion, null, null);
    }
    public void saveProcessTwo(List<HabitacionVIP> listHabitacionVip){
        conexion cn = new conexion();
        cn.saveProcess(null, null, listHabitacionVip, null);
    }
    /**
     * Genera un array de habitaciones disponibles
     * @return 
     */
    public String[] opListHBT() {
        String list = "";
        for (Habitacion hbt : listHabitacion()) {
            list += hbt.getEstado().equals("Disponible") ? hbt.getNumero() + "," : "";
        }
        return list.split(",");
    }

    /**
     * Genera un array de habitaciones vip disponibles
     * @return 
     */
    public String[] opListHBTVIP() {
        String list = "";
        for (HabitacionVIP hbt : listHabitacionVIP()) {
            list += hbt.getEstado().equals("Disponible") ? hbt.getNumero() + "," : "";
        }
        return list.split(",");
    }

    /**
     * se toma un objeto el cual termina siendo una cadena String, se procesa y se obtiene solo
     * los valores, agregados a un array
     * @param object
     * @return 
     */
    public static String[] getValues(Object object) {
        String[] habitacion = object.toString().split(",");
        String cadena = "";
        for (String atr : habitacion) {
            String[] value = atr.split("=");
            cadena += value[1] + ",";
        }
        return cadena.substring(0, cadena.length() - 1).split(",");
    }

    /**
     * genera una union de habitacion normales con vip, en una sola lista
     * @return 
     */
    public List<String[]> getDataHBTALL() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : listHabitacion()) {
            listArray.add(getValues(hbt));
        }
        for (HabitacionVIP hbt : listHabitacionVIP()) {
            listArray.add(getValues(hbt));
        }
        return listArray;
    }

    /**
     * genera una lista de arrays
     * @return 
     */
    public List<String[]> arrayDataHabitacion(){
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion registro : listHabitacion()) {
            listArray.add(getValues(registro));
        }
        return listArray;
    }

    /**
     * genera una lista de arrays
     * @return 
     */
    public List<String[]> arrayDataHabitacionVip(){
        List<String[]> listArray = new ArrayList<>();
        for (HabitacionVIP registro : listHabitacionVIP()) {
            listArray.add(getValues(registro));
        }
        return listArray;
    }
    
    /**
     * genera una lista de arrays(habitaciones) disponibles
     * @return 
     */
    public List<String[]> listToArrayHabitacionDisponible() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : listHabitacion()) {
            if (hbt.getEstado().equals("DISPONIBLE")) {
                listArray.add(getValues(hbt));
            }
        }
        return listArray;
    }

    /**
     * genera una lista de arrays(Habitaciones vip) disponibles
     * @return 
     */
    public List<String[]> listToArrayDataVip() {
        List<String[]> listArray = new ArrayList<>();
        for (HabitacionVIP vip : listHabitacionVIP()) {
            listArray.add(getValues(vip));
        }
        return listArray;
    }

    /**
     * genera una lista de arrays(Habitaciones VIP) disponibles
     * @return 
     */
    public List<String[]> listToArrayDataVipDisponible() {
        List<String[]> listArray = new ArrayList<>();
        for (HabitacionVIP vip : listHabitacionVIP()) {
            if (vip.getEstado().equals("DISPONIBLE")) {
                listArray.add(getValues(vip));
            }
        }
        return listArray;
    }

    /**
     * genera lista de habitaciones que coinciden con el formato a buscar
     * @param nameColumn
     * @param value
     * @return 
     */
    public List<String[]> searchHabitacion(String nameColumn, Object value) {
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
     * genera lista de arrays(habitaciones VIP) que coinciden con el formato a buscar
     * @param nameColumn
     * @param value
     * @return 
     */
    public List<String[]> searchHabitacionVIP(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (HabitacionVIP h : listHabitacionVIP()) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValues(h));
            }
        }
        return listBusqueda;
    }

    /**
     * actualiza una habitacion
     * @param hbt
     */
    public void updateHabitacion(Habitacion hbt) {
        List<Habitacion> listHabitacion = listHabitacion();
        for (Habitacion h : listHabitacion) {
            if (h.getNumero().equals(hbt.getNumero())) {
                h.setNumero(hbt.getNumero());
                h.setPrecio(hbt.getPrecio());
                h.setEstado(hbt.getEstado());
                h.setTipo(hbt.getTipo());
                break;
            }
        }
        saveProcessOne(listHabitacion);
    }

    /**
     * actualiza una habitacion VIP
     * @param hbt
     */
    public void updateHabitacionVIP(HabitacionVIP hbt) {
        List<HabitacionVIP> listHabitacionVIP = listHabitacionVIP();
         for (HabitacionVIP h : listHabitacionVIP) {
            if (h.getNumero().equals(hbt.getNumero())) {
                h.setNumero(hbt.getNumero());
                h.setPrecio(hbt.getPrecio());
                h.setEstado(hbt.getEstado());
                h.setTipo(hbt.getTipo());
                h.setServiciosExtras(hbt.getServiciosExtras());
                break;
            }
        }
        saveProcessTwo(listHabitacionVIP);
    }

    /**
     * genera el total de habitaciones existentes
     * @return 
     */
    public int cantidadHabitaciones() {
        return listHabitacion().size() + listHabitacionVIP().size();
    }

    /**
     * genera un array con los atributos del modelo
     * @return 
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
     * genera un array con los atributos del modelo
     * @return 
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
        for (Habitacion h : listHabitacion()) {
                if (h.getNumero().equals(numHabitacion)) {
                    mdl = h;
                    break;
                }
        }
        return mdl;
    }

    public HabitacionVIP searhHabitacionVIPByNumHabitacion(String numHabitacion){
        HabitacionVIP mdl = new HabitacionVIP();
        for (HabitacionVIP h : listHabitacionVIP()) {
            if (h.getNumero().equals(numHabitacion)) {
                mdl = h;
                break;
            }
        }
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
