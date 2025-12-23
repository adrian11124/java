package controllers;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import Models.Habitacion;
import Models.HabitacionVIP;
import Models.Huesped;
import Models.Reserva;
import bd.conexion;

/**
 *
 * @author adrian11124
 */
public class Inventario {
    public static conexion cn = new conexion();
    private final List<Habitacion> listHBT = new ArrayList<>();
    private final List<HabitacionVIP> listHBTVIP = new ArrayList<>();
    private final List<Huesped> listHuespeds = new ArrayList<>();
    private final List<Reserva> listReserva = new ArrayList<>();



    

    

    

    

    

    

    

    

    

    

    

    

    

    


    

    public List<String[]> buscarHBTVIP(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (HabitacionVIP h : listHBTVIP) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValues(h));
            }
        }
        return listBusqueda;
    }

    public List<String[]> buscarHPD(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (Huesped h : listHuespeds) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValues(h));
            }
        }
        return listBusqueda;
    }

    public void actualizarHBT(Habitacion hbt) {
        for (Habitacion h : listHBT) {
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

    public void actualizarHBTVIP(HabitacionVIP vip) {
        try {
            for (HabitacionVIP h : listHBTVIP) {
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

    public void actualizarHPD(Huesped hpd) {
        try {
            for (Huesped h : listHuespeds) {
                if (h.getNumDocumento().equals(hpd.getNumDocumento())) {
                    h.setNumDocumento(hpd.getNumDocumento());
                    h.setNombre(hpd.getNombre());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    public void removeHPD(String documento) {
        try {
            int i = 0;
            Integer parseDocument = Integer.valueOf(documento);
            for (Huesped h : listHuespeds) {
                if (h.getNumDocumento().equals(parseDocument)) {

                    boolean rh = false;
                    for (Reserva rsv : listReserva) {

                        if (rsv.getHuesped().equals(documento)) {
                            JOptionPane.showMessageDialog(null, "El huesped tiene una reserva");
                            rh = true;
                        }
                    }
                    if (!rh) {
                        listHuespeds.remove(i);
                    }
                    break;
                }
                i++;
            }
        } catch (HeadlessException e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    public void removeRSV(String id) {
        try {
            int i = 0;
            Integer parseId = Integer.valueOf(id);
            for (Reserva r : listReserva) {
                if (r.getId().equals(parseId)) {

                    boolean rh = false;
                    for (Habitacion h : listHBT) {
                        if (h.getNumero().equals(r.getNumHabitacion())) {
                            h.setEstado("MANTENIMIENTO");
                            rh = true;
                            break;
                        }

                    }
                    for (HabitacionVIP h : listHBTVIP) {
                        if (h.getNumero().equals(r.getNumHabitacion())) {
                            h.setEstado("MANTENIMIENTO");
                            rh = true;
                            break;
                        }

                    }
                    if (rh) {
                        listReserva.remove(i);
                        break;
                    }

                }
                i++;
            }
        } catch (HeadlessException e) {
            System.out.println("No se puede actualizar usuario, por favor valide haber seleccionado un registro");
        }
    }

    public int cantidadHabitaciones() {
        return listHBT.size() + listHBTVIP.size();
    }

    

    public int cantidadHuesped() {
        return listHuespeds.size();
    }

    public void guardarProceso() {
        String data = "";
        try {
            Integer id = 1;
            if (!listHuespeds.isEmpty()) {
                for (Huesped hpd : listHuespeds) {
                    data += ((id++) + "," + hpd.getNombre().toUpperCase() + "," + hpd.getNumDocumento() + "_");
                }
            }
            String substring1 = data.substring(0, data.length() - 1);
            data = substring1 + ";";
            for (Habitacion hbt : listHBT) {
                data += (hbt.getId() + "," + hbt.getNumero() + "," + hbt.getPrecio()
                        + "," + hbt.getTipo().toUpperCase() + "," + hbt.getEstado().toUpperCase() + "_");
            }
            String substring2 = data.substring(0, data.length() - 1);
            data = substring2 + ";";
            for (HabitacionVIP hpd : listHBTVIP) {
                data += (hpd.getId() + "," + hpd.getNumero() + "," + hpd.getPrecio()
                        + "," + hpd.getTipo().toUpperCase() + "," + hpd.getEstado().toUpperCase() + "," + hpd.getServiciosExtras() + "_");
            }
            String substring3 = data.substring(0, data.length() - 1);
            data = substring3 + ";";
            if (!listReserva.isEmpty()) {
                id = 1;
                for (Reserva r : listReserva) {
                    data += ((id++) + "," + r.getNumHabitacion() + "," + r.getHuesped()
                            + "," + r.getNombreHuesped().toUpperCase() + "," + r.getVip() + "," + r.getFechaIngreso() + "," + r.getFechaSalida() + "_");
                }
            }
            String substring4 = data.substring(0, data.length() - 1);
            data = substring4 + ";";
            cn.fileUpdate("archivo.txt", data);
        } catch (Exception e) {
            System.out.println("No se pudo guardar proceso en documento en texto");
        }
    }

    
}
