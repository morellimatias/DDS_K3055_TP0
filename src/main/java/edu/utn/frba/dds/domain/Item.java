package edu.utn.frba.dds.domain;

public class Item {
    protected double cantidad;
    protected double precioUnitario;
    protected double precioTotal = 1.0;
    protected boolean esArticulo;

    public Item(double cantidad, double precioUnitario){
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.calcularPrecioTotal();
        this.setEsArticulo();
    }

    public boolean isEsArticulo() {
        return esArticulo;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.calcularPrecioTotal();
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
        this.calcularPrecioTotal();
    }

    public void setEsArticulo() {
    }

    public void calcularPrecioTotal(){
        this.precioTotal = this.precioUnitario * this.cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }
}

