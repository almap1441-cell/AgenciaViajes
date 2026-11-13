package agencia;

// interfaz para los servicios que permiten realizar cancelaciones
public interface Cancelable {

    // metodo para cancelar un servicio y calcular su reembolso
    double cancelar(int horasDisponibles) throws PlazoCancelacionException;

}
