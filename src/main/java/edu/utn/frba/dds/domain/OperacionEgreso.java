package edu.utn.frba.dds.domain;

import edu.utn.frba.dds.exceptions.CantidadNegativaException;
import edu.utn.frba.dds.exceptions.OperacionCerradaException;
import edu.utn.frba.dds.exceptions.PrecioNegativoException;

import java.util.ArrayList;
import java.util.List;


public class OperacionEgreso {
    private boolean estaCerrada = false;
    private double precio;
    List<Item> items = new ArrayList();
    Remito remito;

    public void cerrarOperacion(){
        this.estaCerrada = true;
    }

    public boolean isEstaCerrada() {
        return estaCerrada;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getPrecio() {
        return precio;
    }

    public Remito getRemito() {
        return remito;
    }

    public void calcularPrecio(){
        this.precio = this.items.stream().mapToDouble(item -> item.getPrecioTotal()).sum();
    }

    public void agregarNuevoArticulo(double cantidad, double precioUnitario){
        if(this.estaCerrada == false) {
            if (cantidad > 0.0 && precioUnitario > 0.0) {
                items.add(new Articulo(cantidad, precioUnitario));
            } else if (cantidad <= 0.0) {
                throw new CantidadNegativaException();
            } else {
                throw new PrecioNegativoException();
            }
        } else {
            throw new OperacionCerradaException();
        }
    }

    public void agregarNuevoServicio(double cantidad, double precioUnitario){
        if (this.estaCerrada == false) {
            if (cantidad > 0.0 && precioUnitario > 0.0) {
                items.add(new Servicio(cantidad, precioUnitario));
            } else if (cantidad <= 0.0) {
                throw new CantidadNegativaException();
            } else {
                throw new PrecioNegativoException();
            }
        } else {
            throw new OperacionCerradaException();
        }
    }

    public void modificarPrecioItem(int nroOrden, double nuevoPrecio){
        if (this.estaCerrada == false) {
            this.items.get(nroOrden - 1).setPrecioUnitario(nuevoPrecio);
        } else {
            throw new OperacionCerradaException();
        }
    }

    private boolean sonTodosArticulos(List<Item> items){
        return items.stream().allMatch(item -> item.isEsArticulo() == true);
    }

    public void generarRemito(OperacionEgreso operacionEgreso){
        if (operacionEgreso.sonTodosArticulos(operacionEgreso.getItems()) && operacionEgreso.isEstaCerrada()){
            operacionEgreso.remito = new Remito();
        }

    }

}
