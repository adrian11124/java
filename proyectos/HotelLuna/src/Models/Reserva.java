
package Models;

/**
 *
 * @author adrian11124
 */
public class Reserva {
    private Integer id;
    private String numHabitacion;
    private String Huesped;
    private String nombreHuesped;
    private String vip;
    private String fechaIngreso;
    private String fechaSalida;

    public Reserva() {
    }
    
    public Reserva(Integer id, String numHabitacion, String Huesped, String nombreHuesped, String fechaIngreso, String fechaSalida, String vip) {
        this.id = id;
        this.numHabitacion = numHabitacion;
        this.Huesped = Huesped;
        this.nombreHuesped = nombreHuesped;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.vip = vip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getHuesped() {
        return Huesped;
    }

    public void setHuesped(String Huesped) {
        this.Huesped = Huesped;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public void setNombreHuesped(String nombreHuesped) {
        this.nombreHuesped = nombreHuesped;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
    

    @Override
    public String toString() {
        return "id=" + id + ", numHabitacion=" + numHabitacion + ", Huesped=" + Huesped + ", nombreHuesped=" + nombreHuesped + ", fechaIngreso=" + fechaIngreso + ", fechaSalida=" + fechaSalida+ ", vip=" + vip;
    }
    
    
    
}
