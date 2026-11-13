package agencia;

// clase abstracta para los servicios que son reservaciones
// hereda los datos y metodos de la clase Servicio
public abstract class Reservacion extends Servicio {

    // Constructor de la clase Reservacion
    public Reservacion(String folio, String cliente) {

        // Enviamos el folio y el cliente al constructor de Servicio
        super(folio, cliente);
    }

}