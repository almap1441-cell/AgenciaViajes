package agencia;

import java.util.ArrayList;

// clase principal de la agencia de viajes
public class AgenciaViajes {

    // metodo para registrar una venta en la lista
    public static void registrarVenta(ArrayList<Vendible> ventas, Vendible venta) {

        // recorre las ventas para revisar si el folio ya existe
        for (Vendible actual : ventas) {

            // compara el folio de la venta nueva con los registrados
            if (actual.getFolio().equalsIgnoreCase(venta.getFolio())) {

                // avisa que el folio ya esta registrado
                throw new IllegalArgumentException("El folio ya existe: " + venta.getFolio());
            }
        }

        // agrega la venta a la lista
        ventas.add(venta);

        // muestra que el registro fue correcto
        System.out.println("Venta registrada: " + venta.getFolio());
    }

    // metodo para mostrar las ventas y generar el reporte
    public static void mostrarReporte(ArrayList<Vendible> ventas) {

        // variables para guardar los resultados del reporte
        double totalGeneral = 0;
        double cobroMayor = -1;
        String folioMayor = "";
        String tipoMayor = "";

        // listas para contar los servicios sin depender de su clase concreta
        ArrayList<String> tipos = new ArrayList<String>();
        ArrayList<Integer> cantidades = new ArrayList<Integer>();

        // mostrar el encabezado del reporte
        System.out.println("\n..............REPORTE DE VENTAS..........");

        // recorre todas las ventas una sola vez
        for (Vendible venta : ventas) {

            // obtiene  el cobro de la venta actual
            double cobro = venta.calcularCobro();

            // suma el cobro al total general
            totalGeneral = totalGeneral + cobro;

            // mostra los datos de la venta
            System.out.println("Folio: " + venta.getFolio()
                    + " | Tipo: " + venta.getTipo()
                    + " | Cliente: " + venta.getCliente()
                    + " | Cobro: $" + String.format("%.2f", cobro));

            // revisa si este cobro es el mayor
            if (cobro > cobroMayor) {

                // guarda el cobro mayor
                cobroMayor = cobro;

                // guarda el folio de la venta mayor
                folioMayor = venta.getFolio();

                // guarda el tipo de servicio mayor
                tipoMayor = venta.getTipo();
            }

            // variable para saber si el tipo ya esta en la lista
            int posicion = -1;

            // busca el tipo de servicio en la lista de tipos
            for (int i = 0; i < tipos.size(); i++) {

                // compara el tipo actual con el tipo de la venta
                if (tipos.get(i).equals(venta.getTipo())) {

                    // guarda la posicion encontrada
                    posicion = i;
                    break;
                }
            }

            // revisa si el tipo todavia no existe
            if (posicion == -1) {

                // agrega el nuevo tipo
                tipos.add(venta.getTipo());

                // inicia su contador en uno
                cantidades.add(1);

            } else {

                // aumenta el contador del tipo encontrado
                cantidades.set(posicion, cantidades.get(posicion) + 1);
            }
        }

        // muestra las cantidades por tipo de servicio
        System.out.println("\n...........CANTIDAD POR TIPO...........");

        // recorre la lista de tipos y sus cantidades
        for (int i = 0; i < tipos.size(); i++) {

            // muestra el tipo y la cantidad registrada
            System.out.println(tipos.get(i) + ": " + cantidades.get(i));
        }

        // muestra el total general
        System.out.println("\nTotal general: $" + String.format("%.2f", totalGeneral));

        // revis si hay ventas para mostrar el cobro mayor
        if (ventas.size() > 0) {

            // muestralos datos de la venta con mayor cobro
            System.out.println("Mayor cobro: " + tipoMayor
                    + " | Folio: " + folioMayor
                    + " | Importe: $" + String.format("%.2f", cobroMayor));

        } else {

            // avisa que no hay ventas registradas
            System.out.println("No hay ventas registradas");
        }

        // muestra el total de registros
        System.out.println("Cantidad de ventas: " + ventas.size());
    }

    // metodo para intentar cancelar una venta por su folio
    public static void intentarCancelar(ArrayList<Vendible> ventas, String folio, int horasDisponibles) {

        // variable para guardar la venta encontrada
        Vendible encontrada = null;

        // busca la venta por su folio
        for (Vendible venta : ventas) {

            // compara el folio solicitado con el folio registrado
            if (venta.getFolio().equalsIgnoreCase(folio)) {

                // guarda la venta encontrada
                encontrada = venta;
                break;
            }
        }

        // revisa si el folio no existe
        if (encontrada == null) {

            // muestra el mensaje de folio no encontrado
            System.out.println("No existe el folio: " + folio);
            return;
        }

        // revisam si la venta permite cancelaciones
        if (!(encontrada instanceof Cancelable)) {

            // muestraque el servicio no se puede cancelar
            System.out.println("El servicio " + folio + " no permite cancelacion");
            return;
        }

        // intenta realizar la cancelacion
        try {

            // convierte la venta a la interfaz Cancelable
            Cancelable cancelable = (Cancelable) encontrada;

            // calcula el reembolso correspondiente
            double reembolso = cancelable.cancelar(horasDisponibles);

            // elimina la venta de la lista despues de cancelar correctamente
            ventas.remove(encontrada);

            // muestra el resultado de la cancelacion
            System.out.println("Cancelacion aceptada para " + folio);

            // muestras el importe del reembolso
            System.out.println("Reembolso: $" + String.format("%.2f", reembolso));

        } catch (PlazoCancelacionException e) {

            // mostra el motivo por el que no se pudo cancelar
            System.out.println("Cancelacion rechazada: " + e.getMessage());
        }
    }

    // metodo para calcular los reembolsos posibles sin modificar las ventas
    public static void mostrarReembolsosPosibles(ArrayList<Vendible> ventas) {

        // variable para acumular los reembolsos
        double totalReembolsos = 0;

        // ecorre las ventas una sola vez
        for (Vendible venta : ventas) {

            // revisa si la venta permite cancelaciones
            if (venta instanceof Cancelable) {

                // convierte la venta a la interfaz Cancelable
                Cancelable cancelable = (Cancelable) venta;

                // calcula el reembolso usando cien horas disponibles
                try {

                    // suma el reembolso al total
                    totalReembolsos = totalReembolsos + cancelable.cancelar(100);

                } catch (PlazoCancelacionException e) {

                    // muesra un mensaje si alguna cancelacion no cumple el plazo
                    System.out.println(e.getMessage());
                }
            }
        }

        // muestras el total de reembolsos posibles
        System.out.println("\nTotal de reembolsos posibles: $"
                + String.format("%.2f", totalReembolsos));
    }

    // Metodo principal del programa
    public static void main(String[] args) {

        // Crea la unica lista que guarda todas las ventas
        ArrayList<Vendible> ventas = new ArrayList<Vendible>();

        // muestra el nombre de la agencia
        System.out.println(".......................................");
        System.out.println("       AGENCIA RUTAS DEL BAJIO");
        System.out.println("........................................");

        // Registra la primera venta de hotel
        try {
            registrarVenta(ventas, new Hotel("H001", "Ana", 4, 2));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Registra la segunda venta de hotel
        try {
            registrarVenta(ventas, new Hotel("H002", "Luis", 2, 1));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Registra la primera venta de tour
        try {
            registrarVenta(ventas, new Tour("T001", "Maria", 5));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Registra la segunda venta de tour
        try {
            registrarVenta(ventas, new Tour("T002", "Pedro", 8));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Registra la primera venta de traslado
        try {
            registrarVenta(ventas, new Traslado("TR001", "Sofia", 3, true));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Registra la segunda venta de traslado
        try {
            registrarVenta(ventas, new Traslado("TR002", "Diego", 2, false));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Registra la primera venta de seguro
        try {
            registrarVenta(ventas, new Seguro("S001", "Elena", 2, 3));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Registrs la segunda venta de seguro
        try {
            registrarVenta(ventas, new Seguro("S002", "Carlos", 1, 2));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Prueab un registro con noches invalidas
        try {
            registrarVenta(ventas, new Hotel("H003", "Laura", 0, 2));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Prueba un registro con semanas invalidas
        try {
            registrarVenta(ventas, new Seguro("S003", "Jorge", 2, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // Prueba  un registro con un folio repetido
        try {
            registrarVenta(ventas, new Hotel("H001", "Otro cliente", 2, 1));
        } catch (IllegalArgumentException e) {
            System.out.println("Registro rechazado: " + e.getMessage());
        }

        // muestra el reporte inicial
        mostrarReporte(ventas);

        // intent una cancelacion aceptada
        intentarCancelar(ventas, "H002", 50);

        // intenta una cancelacion fuera del plazo
        intentarCancelar(ventas, "T001", 12);

        // intenta cancelar un servicio que no permite cancelaciones
        intentarCancelar(ventas, "TR001", 100);

        // intenta cancelar un folio que no existe
        intentarCancelar(ventas, "NOEXISTE", 100);

        // mostra el reporte despues de las cancelaciones
        mostrarReporte(ventas);

        // calcula los reembolsos posibles sin eliminar ventas
        mostrarReembolsosPosibles(ventas);
    }

}
