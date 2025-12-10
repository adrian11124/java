package Controlador;

import Modelo.Historial;
import static dashboardapp.MainApp.StkHistorial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author adrian11124
 */
public class CtrHistorial {

    public static void addHistorial(String data, String tipo_proceso) {
        Historial h = new Historial();
        h.setId(StkHistorial.size() + 1);
        h.setData(data);
        h.setTipo_proceso(tipo_proceso);
        h.setFecha(fecha());
        StkHistorial.push(h);
    }

    public static String fecha() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(
                "dd/MM/yyyy hh:mm a", Locale.ENGLISH
        );
        String fechaHora = ahora.format(formato);

        return fechaHora;
    }
}
