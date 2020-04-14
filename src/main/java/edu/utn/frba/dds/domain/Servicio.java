package edu.utn.frba.dds.domain;

public class Servicio extends Item {

    public Servicio(double cantidad, double precioUnitario) {
        super(cantidad, precioUnitario);
    }

    @Override
    public void setEsArticulo() {
        this.esArticulo = false;
    }
}
