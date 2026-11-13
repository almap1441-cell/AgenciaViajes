package agencia;

//  manejar las cancelaciones fuera del plazo permitido
public class PlazoCancelacionException extends Exception {

    // variable para guardar las horas que se requieren
    private int horasRequeridas;

    // variable para guardar las horas disponibles
    private int horasDisponibles;

    // constructor de la excepcion
    public PlazoCancelacionException(int horasRequeridas, int horasDisponibles) {

        // envia el mensaje a la clase Exception
        super("Se requieren " + horasRequeridas
                + " horas y solamente hay " + horasDisponibles);

        // guarda las horas requeridas
        this.horasRequeridas = horasRequeridas;

        // guarda las horas disponibles
        this.horasDisponibles = horasDisponibles;
    }

    // metodo para obtener las horas requeridas
    public int getHorasRequeridas() {
        return horasRequeridas;
    }

    // metodo para obtener las horas disponibles
    public int getHorasDisponibles() {
        return horasDisponibles;
    }
}
