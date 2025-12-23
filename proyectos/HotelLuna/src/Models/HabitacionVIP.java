
package Models;

/**
 *
 * @author adrian11124
 */
public class HabitacionVIP extends Habitacion{
    private String serviciosExtras;
    
    public HabitacionVIP() {
    }

    public HabitacionVIP(Integer id, String numero, Integer precio, String tipo, String estado, String serviciosExtras) {
        super(id, numero, precio, tipo, estado);
        this.serviciosExtras = serviciosExtras;
    }
    
    public String getServiciosExtras() {
        return serviciosExtras;
    }

    public void setServiciosExtras(String serviciosExtras) {
        this.serviciosExtras = serviciosExtras;
    }

    @Override
    public String toString() {
        return super.toString().concat(", serviciosExtras="+serviciosExtras);
    }
    
}
