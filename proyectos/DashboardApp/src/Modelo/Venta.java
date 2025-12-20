
package Modelo;

/**
 *
 * @author adrian11124
 */
public class Venta {
    private String plato;
    private String valor;
    private String fecha;

    public Venta() {
    }
    
    public Venta(String plato, String valor, String fecha) {
        this.plato = plato;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Compra{" + "plato=" + plato + ", valor=" + valor + ", fecha=" + fecha + '}';
    }
    
    
}
