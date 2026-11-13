package agencia;

// clase Tour que hereda de Reservacion
// tambien implementa la interfaz Cancelable
public class Tour extends Reservacion implements Cancelable {

    // atributo para guardar la cantidad de personas
    private int personas;

    // constructor de la clase Tour
    public Tour(String folio, String cliente, int personas) {

        // envia el folio y el cliente a la clase padre
        super(folio, cliente);

        // valida que la cantidad de personas sea minimo uno
        if (personas < 1) {
            throw new IllegalArgumentException("Las personas deben ser minimo 1");
        }

        // guardamos la cantidad de personas
        this.personas = personas;
    }

    // metodo para obtener el tipo de servicio
    @Override
    public String getTipo() {
        return "Tour";
    }

    // metodo para calcular el cobro del tour
    @Override
    public double calcularCobro() {

        // calculamos el costo de las personas
        double total = 450.0 * personas;

        // revisamos si el grupo tiene menos de ocho personas
        if (personas < 8) {

            // agregamos el costo del guia
            total = total + 200.0;
        }

        // devolvemos el total a pagar
        return total;
    }

    // metodo para cancelar el tour
    @Override
    public double cancelar(int horasDisponibles) throws PlazoCancelacionException {

        // revisa si se cumplen las 24 horas requeridas
        if (horasDisponibles < 24) {

            // lanzas nuestra excepcion personalizada
            throw new PlazoCancelacionException(24, horasDisponibles);
        }

        // devueleve el cincuenta por ciento del cobro
        return calcularCobro() * 0.50;
    }

}
