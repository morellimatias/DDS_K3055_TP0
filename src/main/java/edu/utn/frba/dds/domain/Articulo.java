package edu.utn.frba.dds.domain;

public class Articulo extends Item{

    public Articulo(double cantidad, double precioUnitario) {
        super(cantidad, precioUnitario);
    }

    @Override
    public void setEsArticulo() {
        this.esArticulo = true;
    }
}
