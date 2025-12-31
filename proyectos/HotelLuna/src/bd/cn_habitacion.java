package bd;

import java.util.ArrayList;
import java.util.List;

import Models.Habitacion;

/**
 *
 * @author adrian11124
 */
public class cn_habitacion {

    /**
     *genera una lista de arrays
     *return: List<String[]>
     */
    public List<String[]> arrayData(){
        List<String[]> listArray = new ArrayList<>();
        String[] array = fileReadObject();
        for (String registro : array) {
            String[] atrs = registro.split(",");
            Habitacion hbt = new Habitacion();
            hbt.setId(Integer.valueOf(atrs[0]));
            hbt.setNumero(atrs[1]);
            hbt.setPrecio(Integer.valueOf(atrs[2]));
            hbt.setTipo(atrs[3]);
            hbt.setEstado(atrs[4]);
            listArray.add(getValues(hbt));
        }

        return listArray;
    }

    /**
     *genera una lista de Habitacion
     *return: List<Habitacion>
     */
    public List<Habitacion> listHabitacion(){
        List<Habitacion> listArray = new ArrayList<>();
        String[] array = fileReadObject();
        for (String registro : array) {
            String[] atrs = registro.split(",");
            Habitacion hbt = new Habitacion();
            hbt.setId(Integer.valueOf(atrs[0]));
            hbt.setNumero(atrs[1]);
            hbt.setPrecio(Integer.valueOf(atrs[2]));
            hbt.setTipo(atrs[3]);
            hbt.setEstado(atrs[4]);
            listArray.add(hbt);
        }

        return listArray;
    }


    public String[] fileReadObject(){
        String[] array = conexion.fileReadObject( "archivo.txt", 1).split("_");
        return array;
    }
   

    /**
    * genera un array
    * return: String[]
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
}
