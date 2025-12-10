package Controlador;

import Modelo.Plato;
import dashboardapp.MainApp;
import static dashboardapp.MainApp.mpPlato;

/**
 *
 * @author adrian11124
 */
public class CtrPlato {
    
    public static void addPlato(String id, String nombre, String precio, String descripcion){
        Plato plato = new Plato();
        plato.setId(Integer.parseInt(id));
        plato.setNombre(nombre);
        plato.setPrecio(Double.parseDouble(precio));
        plato.setDescripcion(descripcion);
        mpPlato.put(nombre, plato);
        String data = plato.toString();
        String tipo_proceso = "CREAR PLATO";
        CtrHistorial.addHistorial(data, tipo_proceso);
    }
    
    public static void deletePlato(String nombre){
        CtrHistorial.addHistorial(MainApp.mpPlato.get(nombre).toString(), "ELIMINAR");
        MainApp.mpPlato.remove(nombre);
        
    }
    
    public static Plato getOnePlato(String key){
        return MainApp.mpPlato.get(key);
    }
    
    public static void platoPorDefecto(){
        addPlato(String.valueOf(mpPlato.size()+1),"Bandeja Paisa", "13500", """
                                                                          incluyen frijoles rojos 
                                                                          (especialmente las variedades lima y cargamanto),
                                                                           arroz blanco, chicharr\u00f3n, carne en polvo, chorizo antioque\u00f1o, 
                                                                          morcilla, huevo frito, tajadas de pl\u00e1tano maduro, aguacate y arepa antioque\u00f1a""");
        addPlato(String.valueOf(mpPlato.size()+1),"Arroz Chino Original", "13500",
                "arroz blanco,\n raíces chinas, \nhuevo y pollo");
        addPlato(String.valueOf(mpPlato.size()+1),"Sancocho de Gallina", "25900.90",
                "Gallina campesina, \n Maiz, \n Rico y sustancioso");
    
    }
}
