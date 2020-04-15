package edu.utn.frba.dds.domain;

import edu.utn.frba.dds.exceptions.CantidadNegativaException;
import edu.utn.frba.dds.exceptions.ListaVaciaException;
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
        if (!noHayItems()) {
            this.precio = this.items.stream().mapToDouble(item -> item.getPrecioTotal()).sum();
        } else {
            throw new ListaVaciaException();
        }
    }

    public boolean seCumplenCondicionesParaNuevoItem(double cantidad, double precio){
        if(this.isEstaCerrada()){
            throw new OperacionCerradaException();
        }
        if(cantidad <= 0.0) {
            throw new CantidadNegativaException();
        } else if(precio <= 0.0) {
            throw new PrecioNegativoException();
        } else {
            return true;
        }
    }

    public void agregarNuevoArticulo(double cantidad, double precioUnitario){
        if(seCumplenCondicionesParaNuevoItem(cantidad, precioUnitario)) {
            items.add(new Articulo(cantidad, precioUnitario));
        }
    }

    public void agregarNuevoServicio(double cantidad, double precioUnitario){
        if(seCumplenCondicionesParaNuevoItem(cantidad, precioUnitario)) {
            items.add(new Servicio(cantidad, precioUnitario));
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
            operacionEgreso.remito = new Remito(operacionEgreso.getItems());
        }
    }

    public void vaciarListaItems(){
        this.items.clear();
    }

    public boolean noHayItems(){
        return this.items.isEmpty();
    }

}
