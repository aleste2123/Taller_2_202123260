package Modulo;

import java.util.ArrayList;

public class Combo {
    private String nombreCombo;
    private double descuento;
    private ArrayList<Producto> itemsCombo;

    public Combo(String nombreCombo, double descuento) {
        this.nombreCombo = nombreCombo;
        this.descuento = descuento;
        this.itemsCombo = new ArrayList<>();
    }

    public void agregarItemACombo(Producto itemCombo) {
        this.itemsCombo.add(itemCombo);
    }

    public int getPrecio() {
        int precioCombo = 0;
        for (Producto itemCombo : this.itemsCombo) {
            precioCombo += itemCombo.getPrecio();
        }
        precioCombo -= (int) (precioCombo * this.descuento);
        return precioCombo;
    }

    public String generarTextoFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombreCombo);
        sb.append(": ");
        for (Producto itemCombo : this.itemsCombo) {
            sb.append(itemCombo.getNombre());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // eliminar la última coma y espacio
        sb.append(" - Precio: $");
        sb.append(getPrecio());
        return sb.toString();
    }

    public String getNombre() {
        return this.nombreCombo;
    }

    public double getDescuento() {
        return this.descuento;
    }
}