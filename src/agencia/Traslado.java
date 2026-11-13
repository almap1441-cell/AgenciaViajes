package agencia;

// clase Traslado que hereda de Reservacion
// no implementa Cancelable porque este servicio no se puede cancelar
public class Traslado extends Reservacion {

    // atributo para guardar la cantidad de personas
    private int personas;

    // atributo para indicar si el traslado es de ida y vuelta
    private boolean idaVuelta;

    // xonstructor de la clase Traslado
    public Traslado(String folio, String cliente, int personas, boolean idaVuelta) {

        // envia el folio y el cliente a la clase padre
        super(folio, cliente);

        // valida que la cantidad de personas sea minimo uno
        if (personas < 1) {
            throw new IllegalArgumentException("Las personas deben ser minimo 1");
        }

        // guarda la cantidad de personas
        this.personas = personas;

        // guarda si el traslado es de ida y vuelta
        this.idaVuelta = idaVuelta;
    }

    // metodo para obtener el tipo de servicio
    @Override
    public String getTipo() {
        return "Traslado";
    }

    // metodo para calcular el cobro del traslado
    @Override
    public double calcularCobro() {

        // calculamos el costo de las personas
        double total = 220.0 * personas;

        // revisamos si el traslado es de ida y vuelta
        if (idaVuelta) {

            // multiplicamos el costo por dos
            total = total * 2;
        }

        // devolvemos el total a pagar
        return total;
    }

}
