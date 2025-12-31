package hotel;

import java.util.ArrayList;
import java.util.List;

import Models.Habitacion;
import Models.HabitacionVIP;
import Models.Huesped;
import Models.Reserva;
import bd.conexion;
import views.Index;

/**
 *
 * @author adrian11124
 */
public class MainRunApp {
    
    public static List<Habitacion> listHBT = new ArrayList<>();
    public static List<HabitacionVIP> listHBTVIP = new ArrayList<>();
    public static List<Huesped> listHuespeds = new ArrayList<>();
    public static List<Reserva> listReserva = new ArrayList<>();
    public static void main(String[] args) {
        conexion.ArrayToLists();
        Index index = new Index();
    }
}
