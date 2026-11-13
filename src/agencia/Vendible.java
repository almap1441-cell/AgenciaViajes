package agencia;

// interfaz  que representa todos los servicios que se pueden vender
public interface Vendible {

    // metodo para obtener el folio  que es  unico de la venta
    String getFolio();

    // metodo para obtener el nombre del cliente
    String getCliente();

    // metodo para obtener el tipo de servicio
    String getTipo();

    // metodo para calcular el cobro de cada servicio
    double calcularCobro();

}
