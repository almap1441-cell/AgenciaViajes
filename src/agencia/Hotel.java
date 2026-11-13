package agencia;

// clase Hotel que hereda de Reservacion
// tambien implementa la interfaz Cancelable
public class Hotel extends Reservacion implements Cancelable {

    // atributo para guardar la cantidad de noches
    private int noches;

    // atributo para guardar la cantidad de habitaciones
    private int habitaciones;

    // constructor de la clase Hotel
    public Hotel(String folio, String cliente, int noches, int habitaciones) {

        // enviam el folio y el cliente a la clase padre
        super(folio, cliente);

        // valida que las noches sean mayores o iguales a uno
        if (noches < 1) {
            throw new IllegalArgumentException("Las noches deben ser minimo 1");
        }

        // valida que las habitaciones sean mayores o iguales a uno
        if (habitaciones < 1) {
            throw new IllegalArgumentException("Las habitaciones deben ser minimo 1");
        }

        // guarda la cantidad de noches
        this.noches = noches;

        // guarda la cantidad de habitaciones
        this.habitaciones = habitaciones;
    }

    // metodo para obtener el tipo de servicio
    @Override
    public String getTipo() {
        return "Hotel";
    }

    // metodo para calcular el cobro del hotel
    @Override
    public double calcularCobro() {

        // calcula el costo de las noches y habitaciones
        double total = 900.0 * noches * habitaciones;

        // revisamos si la reservacion tiene cuatro noches o mas
        if (noches >= 4) {

            // aplicam el descuento del diez por ciento
            total = total - (total * 0.10);
        }

        // devolvemos el total a pagar
        return total;
    }

    // metodo para cancelar la reservacion del hotel
    @Override
    public double cancelar(int horasDisponibles) throws PlazoCancelacionException {

        // revisamos si se cumplen las 48 horas requeridas
        if (horasDisponibles < 48) {

            // lanzamos nuestra excepcion personalizada
            throw new PlazoCancelacionException(48, horasDisponibles);
        }

        // devolvemos el ochenta por ciento del cobro
        return calcularCobro() * 0.80;
    }

}
