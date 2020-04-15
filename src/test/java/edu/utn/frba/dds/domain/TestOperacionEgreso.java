package edu.utn.frba.dds.domain;

import edu.utn.frba.dds.exceptions.CantidadNegativaException;
import edu.utn.frba.dds.exceptions.ListaVaciaException;
import edu.utn.frba.dds.exceptions.OperacionCerradaException;
import edu.utn.frba.dds.exceptions.PrecioNegativoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestOperacionEgreso {
    private OperacionEgreso operacionEgreso;

    @BeforeEach
    public void init(){
        this.operacionEgreso = new OperacionEgreso();
        this.operacionEgreso.agregarNuevoArticulo(4, 50.5);
        this.operacionEgreso.agregarNuevoArticulo(2, 100);
        this.operacionEgreso.agregarNuevoArticulo(25, 45.0);
    }

    @Test
    public void calcularPrecioTotalOperacion(){
        try {
            this.operacionEgreso.calcularPrecio();
            Assertions.assertEquals(1527, this.operacionEgreso.getPrecio());
        }
        catch (CantidadNegativaException exception) {
            System.out.print(exception.getMessage());
            Assertions.fail("El test falló porque ocurrió una excepción");
        }
        catch (PrecioNegativoException exception) {
            System.out.print(exception.getMessage());
            Assertions.fail("El test falló porque ocurrió una excepción");
        }
    }

    @Test
    public void calcularPrecioDeUnaOperacionSinItems(){
        try {
            this.operacionEgreso.vaciarListaItems();
            this.operacionEgreso.calcularPrecio();
            Assertions.fail("El test falló porque la operación tenía ítems cargados");
        }
        catch (ListaVaciaException exception) {
            Assertions.assertEquals(ListaVaciaException.class, exception.getClass());

        }
    }

    @Test
    public void calcularPrecioTotalOperacionCambiandoPrecioDeUnArticulo(){
        try {
            this.operacionEgreso.modificarPrecioItem(3, 720);
            this.operacionEgreso.calcularPrecio();
            Assertions.assertEquals(18402, this.operacionEgreso.getPrecio());
        }
        catch (CantidadNegativaException exception) {
            System.out.print(exception.getMessage());
            Assertions.fail("El test falló porque ocurrió una excepción");
        }
        catch (PrecioNegativoException exception) {
            System.out.print(exception.getMessage());
            Assertions.fail("El test falló porque ocurrió una excepción");
        }
    }

    @Test
    public void cambiarPrecioItemCuandoLaOperacionEstaCerrada(){
        try {
            this.operacionEgreso.cerrarOperacion();
            this.operacionEgreso.modificarPrecioItem(1, 67);
            Assertions.fail("El test falló porque la operación no estaba cerrada");
        }
        catch (OperacionCerradaException exception){
            Assertions.assertEquals(OperacionCerradaException.class, exception.getClass());
        }
    }

    @Test
    public void seGeneraRemitoCuandoSonTodosArticulos(){
        this.operacionEgreso.cerrarOperacion();
        this.operacionEgreso.calcularPrecio();
        this.operacionEgreso.generarRemito(this.operacionEgreso);
        Assertions.assertEquals(Remito.class, this.operacionEgreso.getRemito().getClass());
    }

    @Test
    public void noSeGeneraRemitoCuandoHayServicios(){
        this.operacionEgreso.agregarNuevoServicio(30, 540);
        this.operacionEgreso.cerrarOperacion();
        this.operacionEgreso.calcularPrecio();
        this.operacionEgreso.generarRemito(this.operacionEgreso);
        Assertions.assertNull(this.operacionEgreso.getRemito());

    }

}
