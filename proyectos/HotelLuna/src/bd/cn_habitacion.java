package bd;

import java.util.ArrayList;
import java.util.List;

import Models.Habitacion;

public class cn_habitacion {

    public List<String[]> arrayData(int id_table){
        List<String[]> listArray = new ArrayList<>();
        String[] array = conexion.fileReadObject( "archivo.txt", id_table).split("_");
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
