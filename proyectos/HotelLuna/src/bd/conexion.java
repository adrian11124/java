package bd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import Models.Habitacion;
import Models.HabitacionVIP;
import Models.Huesped;
import Models.Reserva;
import hotel.MainRunApp;

/**
 *
 * @author adrian11124
 */
public class conexion {

    /**
    *Leer archivo de texto
    *return: arreglo
    */
    public static String[] fileRead(String url) {
        String lineaTotal = "";
        InputStream fileName = conexion.class.getResourceAsStream(url);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineaTotal += linea;
            }

        } catch (Exception e) {
            lineaTotal = " FAILED:: " + e;
        }
        return lineaTotal.substring(0, lineaTotal.length() - 1).split(";");
    }

    /**
    *Actualiza en archivo
    *return: boolean
    */
    public boolean fileUpdate(String url, String data) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(url))) {
            bw.write(data);
        } catch (Exception e) {
        }
        return false;
    }

    /**
    *Convierte un array a listas(modelo)
    *return: boolean
    */
    public static void ArrayToLists() {
        try {

            String[] allData = conexion.fileRead("archivo.txt");
            for (int i = 0; i < allData.length; i++) {
                if (i == 0) {
                    String[] arrHuesped = allData[i].split("_");
                    for (String registro : arrHuesped) {
                        String[] atrs = registro.split(",");
                        Huesped hpd = new Huesped();
                        hpd.setId(Integer.valueOf(atrs[0]));
                        hpd.setNombre(atrs[1]);
                        hpd.setNumDocumento(Integer.valueOf(atrs[2]));
                        MainRunApp.listHuespeds.add(hpd);
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
                        MainRunApp.listHBT.add(hbt);
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
                        MainRunApp.listHBTVIP.add(vip);
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
                        MainRunApp.listReserva.add(rsv);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("No se pudo leer el archivo :: " + e);
        }
    }

    public static void main(String[] args) {
        
        String[] maca = fileRead("archivo.txt");
        for (String string : maca) {
            System.out.println(string);
        }

    }
}
