package Modulo;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado {
    private ProductoMenu base;
    private List<Ingrediente> ingredientes;

    public ProductoAjustado(ProductoMenu base) {
        this.base = base;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public String getNombre() {
        String nombre = base.getNombre();
        for (Ingrediente ingrediente : ingredientes) {
            nombre += " con " + ingrediente.getNombre();
        }
        return nombre;
    }

    public int getPrecio() {
        int precio = base.getPrecio();
        for (Ingrediente ingrediente : ingredientes) {
            precio += ingrediente.getCostoAdicional();
        }
        return precio;
    }

    public String generarTextoFactura() {
        String textoFactura = base.getNombre();
        if (ingredientes.size() > 0) {
            textoFactura += " con ";
            for (int i = 0; i < ingredientes.size(); i++) {
                Ingrediente ingrediente = ingredientes.get(i);
                textoFactura += ingrediente.getNombre();
                if (i < ingredientes.size() - 1) {
                    textoFactura += ", ";
                }
            }
        }
        textoFactura += ": $" + getPrecio();
        return textoFactura;
    }
}
