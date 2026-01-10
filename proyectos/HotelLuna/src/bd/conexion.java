package bd;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.List;

import Models.Habitacion;
import Models.HabitacionVIP;
import Models.Huesped;
import Models.Reserva;
import java.io.File;
import java.io.FileReader;
import java.io.Writer;
import java.net.URL;

/**
 *
 * @author adrian11124
 */
public class conexion {

    /**
     * @param url
     * @param id_table
     * @return 
    */
    public String[] fileReadObject(String url, int id_table) {
        String[] arrayObject = fileReadObjectOne(url, id_table).split("_");
        return arrayObject;
    }

    /**
     * @param url
     * @param id_table
     * @return 
    */
    public String fileReadObjectOne(String url, int id_table) {
        String[] arrayObjects =  fileRead( url);
        String dataString = arrayObjects[id_table];
        return dataString;
    }

    /**
    *Leer archivo de texto
    *return: arreglo
     * @param url
     * @return 
    */
    public String[] fileRead(String url) {
        String lineaTotal = "";
        URL fileUrl = conexion.class.getResource(url);
        String path = fileUrl.getPath();
        String pathReplace = path.replace("build/classes", "src");
        File file = new File(pathReplace);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineaTotal += linea;
            }
            br.close();
        } catch (Exception e) {
            lineaTotal = " FAILED:: " + e;
        }
        
        String lineaTotalThwo =  lineaTotal.substring(0, lineaTotal.length() - 1);
        String[] arrayOne = lineaTotalThwo.split(";");
        
        return arrayOne;
    }

    /**
     * @param url
     * @param data
     * @return 
     * Actualiza en archivo
    */
    public boolean fileUpdate(String url, String data) {
        URL fileName = conexion.class.getResource("archivo.txt");
        String path = fileName.getPath();
        String pathReplace = path.replace("build/classes", "src");
        try (Writer bw = new FileWriter(pathReplace)){
            bw.write(data);
            bw.close();
        } catch (Exception e) {
            System.out.println("FAILED:: conexion.fileUpdate \n\n "+e);
        }
        return false;
    }

    /**
    
     * @param listHuesped
     * @param listHabitacion
     * @param listHabitacionVIP
     * @param listReserva
     * status: active
     * ordena los datos en una cadena String para guardarlos
    */
    public void saveProcess(
        List<Huesped> listHuesped,
        List<Habitacion> listHabitacion,
        List<HabitacionVIP> listHabitacionVIP,
        List<Reserva> listReserva
    ) {
        String data = "";
        try {
            Integer id_huesped = 1;
            if(listHuesped == null){
                data += fileReadObjectOne("/bd/archivo.txt", 0);
            }else{
                if (!listHuesped.isEmpty()) {
                    for (Huesped hpd : listHuesped) {
                        data += ((id_huesped++) + "," + hpd.getNombre().toUpperCase() + "," + hpd.getNumDocumento() + "_");
                    }
                }
            }
            
            Integer id_habitacion = 1;
            String substring1 = data;
            data = substring1 + ";";
            if(listHabitacion == null){
                data += fileReadObjectOne("/bd/archivo.txt", 1);
            }else{
                if (!listHabitacion.isEmpty()) {
                    for (Habitacion hbt : listHabitacion) {
                        data += ((id_habitacion++) + "," + hbt.getNumero() + "," + hbt.getPrecio()
                                + "," + hbt.getTipo().toUpperCase() + "," + hbt.getEstado().toUpperCase() + "_");
                    }
                }
            }

            Integer id_habitacionVip = 1;
            String substring2 = data;
            data = substring2 + ";";
            if(listHabitacionVIP == null){
                data += fileReadObjectOne("/bd/archivo.txt", 2);
            }else{
                if (!listHabitacionVIP.isEmpty()) {
                    for (HabitacionVIP hpd : listHabitacionVIP) {
                        data += ((id_habitacionVip++) + "," + hpd.getNumero() + "," + hpd.getPrecio()
                                + "," + hpd.getTipo().toUpperCase() + "," + hpd.getEstado().toUpperCase() + "," + hpd.getServiciosExtras() + "_");
                    }
                }
            }

            Integer id_reserva = 1;
            String substring3 = data;
            data = substring3 + ";";
            if(listReserva == null){
                data += fileReadObjectOne("/bd/archivo.txt", 3);
            }else{
                if (!listReserva.isEmpty()) {
                    for (Reserva r : listReserva) {
                        data += ((id_reserva++) + "," + r.getNumHabitacion() + "," + r.getHuesped()
                                + "," + r.getNombreHuesped().toUpperCase() + "," + r.getVip() + "," + r.getFechaIngreso() + "," + r.getFechaSalida() + "_");
                    }
                }
            }
            String substring4 = data;
            data = substring4 + ";";
            
            fileUpdate("/bd/archivo.txt", data);
        } catch (Exception e) {
            System.out.println("No se pudo guardar proceso en documento en texto \n"+e+"\n");
        }
    }
    
}
