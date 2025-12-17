package Process;

import Clases.Habitacion;
import Clases.HabitacionVIP;
import Clases.Huesped;
import Clases.Reserva;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Inventario {

    private final List<Habitacion> listHBT = new ArrayList<>();
    private final List<HabitacionVIP> listHBTVIP = new ArrayList<>();
    private final List<Huesped> listHuespeds = new ArrayList<>();
    private final List<Reserva> listReserva = new ArrayList<>();

    public boolean fileUpdate(String url, String data) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(url))) {
            bw.write(data);
        } catch (Exception e) {
        }
        return false;
    }

    public String registrarHuesped(String nombre, String documento) {
        String message = "";
        try {
            if (!nombre.equals("") && !documento.equals("")) {
                Huesped hp = new Huesped();
                hp.setId(listHuespeds.size() + 1);
                hp.setNombre(nombre);
                hp.setNumDocumento(Integer.valueOf(documento));
                listHuespeds.add(hp);
            } else {
                message = "Validar que los campos no esten vacios";
            }
        } catch (NumberFormatException e) {
            System.out.println("" + e);
        }
        return message;
    }

    public String[] fileRead(String url) {
        String lineaTotal = "";
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            String linea;

            while ((linea = br.readLine()) != null) {

                lineaTotal += linea;
            }

        } catch (Exception e) {
            lineaTotal = "FAILED:: " + e;
        }
        return lineaTotal.substring(0, lineaTotal.length() - 1).split(";");
    }

    public void reservarHabitacion(Reserva r) {
        listReserva.add(r);

    }

    public String[] opListHBT() {
        String list = "";
        for (Habitacion hbt : listHBTVIP) {
            list += hbt.getEstado().equals("Disponible") ? hbt.getNumero() + "," : "";
        }
        return list.split(",");
    }

    public String[] getValues(Object hbt) {
        String[] habitacion = hbt.toString().split(",");
        String h = "";
        for (String atr : habitacion) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    public String[] getValuesHPD(Huesped hpd) {
        String[] huesped = hpd.toString().split(",");
        String h = "";
        for (String atr : huesped) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    public String[] getValuesRSV(Reserva hpd) {
        String[] huesped = hpd.toString().split(",");
        String h = "";
        for (String atr : huesped) {
            String[] value = atr.split("=");
            h += value[1] + ",";
        }
        return h.substring(0, h.length() - 1).split(",");
    }

    public List<String[]> getDataHBTALL() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : listHBT) {
            listArray.add(getValues(hbt));
        }
        for (HabitacionVIP hbt : listHBTVIP) {
            listArray.add(getValues(hbt));
        }
        return listArray;
    }

    public List<String[]> getDataHBT() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : listHBT) {
            listArray.add(getValues(hbt));
        }
        return listArray;
    }

    public List<String[]> getDataHBTDisponible() {
        List<String[]> listArray = new ArrayList<>();
        for (Habitacion hbt : listHBT) {
            if (hbt.getEstado().equals("DISPONIBLE")) {
                listArray.add(getValues(hbt));
            }

        }
        return listArray;
    }

    public List<String[]> getDataHBTVIP() {
        List<String[]> listArray = new ArrayList<>();
        for (HabitacionVIP vip : listHBTVIP) {
            listArray.add(getValues(vip));
        }
        return listArray;
    }

    public List<String[]> getDataHBTVIPDisponible() {
        List<String[]> listArray = new ArrayList<>();
        for (HabitacionVIP vip : listHBTVIP) {
            if (vip.getEstado().equals("DISPONIBLE")) {
                listArray.add(getValues(vip));
            }
        }
        return listArray;
    }

    public List<Habitacion> getListHBT() {
        return listHBT;
    }

    public List<HabitacionVIP> getListHBTVIP() {
        return listHBTVIP;
    }

    public List<Huesped> getListHPD() {
        return listHuespeds;
    }

    public void generarReserva(List<String> datosHuesped, Inventario inv) {
        Reserva r = new Reserva();
        r.setId(inv.cantidadRSV() + 1);
        r.setHuesped(datosHuesped.get(0));
        r.setNombreHuesped(datosHuesped.get(1));
        r.setVip(datosHuesped.get(2));
        r.setNumHabitacion(datosHuesped.get(3));
        r.setFechaIngreso(datosHuesped.get(4));
        r.setFechaSalida(datosHuesped.get(5));
        inv.reservarHabitacion(r);
        if (r.getVip().equals("SI")) {
            for (Habitacion hbt : listHBTVIP) {
                if (hbt.getNumero().equals(r.getNumHabitacion())) {
                    hbt.setEstado("Reservada");
                }
            }
        } else {
            for (Habitacion hbt : listHBT) {
                if (hbt.getNumero().equals(r.getNumHabitacion())) {
                    hbt.setEstado("Reservada");
                }
            }
        }
    }

    public List<String[]> getDataHPD() {
        List<String[]> listArray = new ArrayList<>();
        for (Huesped hbt : listHuespeds) {
            listArray.add(getValuesHPD(hbt));
        }
        return listArray;
    }

    public List<String[]> getDataRSV() {
        List<String[]> listArray = new ArrayList<>();
        for (Reserva r : listReserva) {
            listArray.add(getValuesRSV(r));
        }
        return listArray;
    }

    public List<String[]> buscarHBT(String nameColumn, Object value) {
        List<String[]> listBusqueda = new ArrayList<>();
        for (Habitacion h : listHBT) {
            String cl = h.toString();
            if (cl.contains(nameColumn + "=" + value)) {
                listBusqueda.add(getValues(h));
            }
        }
        return listBusqueda;
    }

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

    public int cantidadRSV() {
        return listReserva.size();
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
            fileUpdate("Files\\archivo.txt", data);
        } catch (Exception e) {
            System.out.println("No se pudo guardar proceso en documento en texto");
        }
    }

    public void leerArchivo() {
        try {
            String[] allData = fileRead("Files\\archivo.txt");
            for (int i = 0; i < allData.length; i++) {
                if (i == 0) {
                    String[] arrHuesped = allData[i].split("_");
                    for (String registro : arrHuesped) {
                        String[] atrs = registro.split(",");
                        Huesped hpd = new Huesped();
                        hpd.setId(Integer.valueOf(atrs[0]));
                        hpd.setNombre(atrs[1]);
                        hpd.setNumDocumento(Integer.valueOf(atrs[2]));
                        listHuespeds.add(hpd);
                    }

                }
                if (i == 1) {
                    String[] arrHBT = allData[i].split("_");
                    for (String registro : arrHBT) {
                        String[] atrs = registro.split(",");
                        Habitacion hbt = new Habitacion();
                        hbt.setId(Integer.valueOf(atrs[0]));
                        hbt.setNumero(atrs[1]);
                        hbt.setPrecio(Integer.valueOf(atrs[2]));
                        hbt.setTipo(atrs[3]);
                        hbt.setEstado(atrs[4]);
                        listHBT.add(hbt);
                    }
                }
                if (i == 2) {
                    String[] arrVIP = allData[i].split("_");
                    for (String registro : arrVIP) {
                        String[] atrs = registro.split(",");
                        HabitacionVIP vip = new HabitacionVIP();
                        vip.setId(Integer.valueOf(atrs[0]));
                        vip.setNumero(atrs[1]);
                        vip.setPrecio(Integer.valueOf(atrs[2]));
                        vip.setTipo(atrs[3]);
                        vip.setEstado(atrs[4]);
                        vip.setServiciosExtras(atrs[5]);
                        listHBTVIP.add(vip);
                    }
                }
                if (i == 3) {
                    String[] arrRSV = allData[i].split("_");
                    for (String registro : arrRSV) {
                        String[] atrs = registro.split(",");
                        Reserva rsv = new Reserva();
                        rsv.setId(Integer.valueOf(atrs[0]));
                        rsv.setNumHabitacion(atrs[1]);
                        rsv.setHuesped(atrs[2]);
                        rsv.setNombreHuesped(atrs[3]);
                        rsv.setVip(atrs[4]);
                        rsv.setFechaIngreso(atrs[5]);
                        rsv.setFechaSalida(atrs[6]);
                        listReserva.add(rsv);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("No se pudo leer el archivo :: " + e);
        }
    }
}
