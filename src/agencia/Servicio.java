package agencia;

// clase abstracta que representa los servicios de la agencia
// implementa la interfaz Vendible
public abstract class Servicio implements Vendible {

    // atributo privado para guardar el folio
    private String folio;

    // atributo privado para guardar el nombre del cliente
    private String cliente;

    // constructor de la clase Servicio
    public Servicio(String folio, String cliente) {

        // valida que el folio no sea nulo ni este vacio
        if (folio == null || folio.trim().isEmpty()) {
            throw new IllegalArgumentException("El folio no puede estar vacio");
        }

        // valida que el cliente no sea nulo ni este vacio
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente no puede estar vacio");
        }

        // guarda el folio sin espacios al principio ni al final
        this.folio = folio.trim();

        // guarda el nombre del cliente
        this.cliente = cliente.trim();
    }

    // metodo para obtener el folio
    @Override
    public String getFolio() {
        return folio;
    }

    // metodo para obtener el nombre del cliente
    @Override
    public String getCliente() {
        return cliente;
    }

    // metodo abstracto para obtener el tipo de servicio
    @Override
    public abstract String getTipo();

    // metodo abstracto para calcular el cobro
    @Override
    public abstract double calcularCobro();

}
