
package Modelo;

/**
 *
 * @author adrian11124
 */
public class Historial {
    private int id;
    private String tipo_proceso;
    private String data;
    private String fecha;

    public Historial() {
    }
    public Historial(int id, String tipo_proceso, String data, String fecha) {
        this.id = id;
        this.tipo_proceso = tipo_proceso;
        this.data = data;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_proceso() {
        return tipo_proceso;
    }

    public void setTipo_proceso(String tipo_proceso) {
        this.tipo_proceso = tipo_proceso;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Historial{" + "id=" + id + ", tipo_proceso=" + tipo_proceso + ", data=" + data + ", fecha=" + fecha + '}';
    }
    
    
    
}
