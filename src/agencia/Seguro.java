package agencia;

// clase Seguro que hereda de Servicio
// tambien implementa la interfaz Cancelable
public class Seguro extends Servicio implements Cancelable {

    // atributo para guardar la cantidad de personas
    private int personas;

    // atributo para guardar la cantidad de semanas
    private int semanas;

    // constructor de la clase Seguro
    public Seguro(String folio, String cliente, int personas, int semanas) {

        // envia el folio y el cliente a la clase padre
        super(folio, cliente);

        // valida que la cantidad de personas sea minimo uno
        if (personas < 1) {
            throw new IllegalArgumentException("Las personas deben ser minimo 1");
        }

        // valida que la cantidad de semanas sea minimo uno
        if (semanas < 1) {
            throw new IllegalArgumentException("Las semanas deben ser minimo 1");
        }

        // guarda la cantidad de personas
        this.personas = personas;

        // guarda la cantidad de semanas
        this.semanas = semanas;
    }

    // metodo para obtener el tipo de servicio
    @Override
    public String getTipo() {
        return "Seguro";
    }

    // metodo para calcular el cobro del seguro
    @Override
    public double calcularCobro() {

        // calculamos el costo por personas y semanas
        double total = 120.0 * personas * semanas;

        // devuelve  el total a pagar
        return total;
    }

    // metodo para cancelar el seguro
    @Override
    public double cancelar(int horasDisponibles) throws PlazoCancelacionException {

        // revisasi se cumplen las 72 horas requeridas
        if (horasDisponibles < 72) {

            // lanza nuestra excepcion personalizada
            throw new PlazoCancelacionException(72, horasDisponibles);
        }

        // devueleve  el cien por ciento del cobro
        return calcularCobro();
    }

}
