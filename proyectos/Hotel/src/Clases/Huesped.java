package Clases;

public class Huesped {
    private Integer id;
    private Integer numDocumento;
    private String nombre;

    public Huesped() {
    }
    public Huesped(Integer id, Integer numDocumento, String nombre) {
        this.id = id;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(Integer numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "id=" + id + ", numDocumento=" + numDocumento + ", nombre=" + nombre;
    }
    
    
}
