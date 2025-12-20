package Controlador;

import Modelo.Venta;
import dashboardapp.MainApp;

/**
 *
 * @author adrian11124
 */
public class CtrVenta {

    /**
     *permite guardar la venta de un plato
     */
    public static void vender(String plato, String valor) {
        Venta v = new Venta();
        v.setPlato(plato);
        v.setValor(valor);
        v.setFecha(CtrHistorial.fecha());
        MainApp.StkCompras.push(v);

        CtrHistorial.addHistorial(v.toString(), "COMPRAR");
    }
}
