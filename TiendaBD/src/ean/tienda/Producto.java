package ean.tienda;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Producto{
    @DatabaseField(id = true)
    private int codigo;
    @DatabaseField(canBeNull = false)
    private String nombre;
    @DatabaseField(canBeNull = false)
    private String materiales;
    @DatabaseField(canBeNull = false)
    private int cantidad;
    @DatabaseField(canBeNull = false)
    private double precio;
    @DatabaseField(canBeNull = false)
    private double costo_de_produccion;
    @DatabaseField(canBeNull = false)
    private double timepo_de_produccion;

    public Producto(){}

    public Producto(int codigo, String nombre, String materiales, int cantidad, double precio, double costo_de_produccion, double timepo_de_produccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.materiales = materiales;
        this.cantidad = cantidad;
        this.precio = precio;
        this.costo_de_produccion = costo_de_produccion;
        this.timepo_de_produccion = timepo_de_produccion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMateriales() {
        return materiales;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getCosto_de_produccion() {
        return costo_de_produccion;
    }

    public double getTimepo_de_produccion() {
        return timepo_de_produccion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCosto_de_produccion(double costo_de_produccion) {
        this.costo_de_produccion = costo_de_produccion;
    }

    public void setTimepo_de_produccion(double timepo_de_produccion) {
        this.timepo_de_produccion = timepo_de_produccion;
    }
}