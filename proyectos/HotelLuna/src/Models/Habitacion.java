
package Models;

/**
 *
 * @author adrian11124
 */
public class Habitacion {
    private Integer id;
    private String numero;
    private Integer precio;
    private String tipo;
    private String estado;
    
    public Habitacion() {
    }

    public Habitacion(Integer id, String numero, Integer precio, String tipo, String estado) {
        this.id = id;
        this.numero = numero;
        this.precio = precio;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    } 

    @Override
    public String toString() {
        return "id=" + id + ", numero=" + numero + ", precio=" + precio + ", tipo=" + tipo + ", estado=" + estado;
    }
    
    
}
