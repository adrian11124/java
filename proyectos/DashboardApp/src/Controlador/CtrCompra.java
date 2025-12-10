package Controlador;

import Modelo.Compra;
import dashboardapp.MainApp;

/**
 *
 * @author adrian11124
 */
public class CtrCompra {

    public static void Comprar(String plato, String valor) {
        Compra c = new Compra();
        c.setPlato(plato);
        c.setValor(valor);
        c.setFecha(CtrHistorial.fecha());
        MainApp.StkCompras.push(c);

        CtrHistorial.addHistorial(c.toString(), "COMPRAR");
    }
}
