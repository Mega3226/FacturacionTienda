package tienda;

public final class FacturacionTienda {

    public static final int CLIENTE_ESTUDIANTE = 1;

    public static final int CLIENTE_JUBILADO = 2;

    private static final double DESCUENTO_ESTUDIANTE = 0.10;

    private static final double DESCUENTO_JUBILADO = 0.15;

    private static final double SIN_DESCUENTO = 0.0;

    private static final double GASTOS_ENVIO_URGENTE = 4.95;

    private static final double FACTOR_IVA = 1.21;

    private FacturacionTienda() {
        // Clase de utilidad: no se instancia
    }

    public static void main(final String[] args) {
        double importeTotal = calcularImporteTotal(100.0, 2, CLIENTE_ESTUDIANTE, true);
        System.out.println("Importe total a pagar: " + importeTotal);
    }

    public static double calcularImporteTotal(final double precioBase, final int unidades,
            final int tipoCliente, final boolean conEnvio) {
        double subtotal = calcularSubtotal(precioBase, unidades);
        subtotal = aplicarDescuento(subtotal, tipoCliente);
        subtotal = aplicarGastosEnvio(subtotal, conEnvio);
        return aplicarIva(subtotal);
    }

    private static double calcularSubtotal(final double precioBase, final int unidades) {
        return precioBase * unidades;
    }

    private static double obtenerPorcentajeDescuento(final int tipoCliente) {
        return switch (tipoCliente) {
            case CLIENTE_ESTUDIANTE -> DESCUENTO_ESTUDIANTE;
            case CLIENTE_JUBILADO -> DESCUENTO_JUBILADO;
            default -> SIN_DESCUENTO;
        };
    }

    private static double aplicarDescuento(final double subtotal, final int tipoCliente) {
        return subtotal - subtotal * obtenerPorcentajeDescuento(tipoCliente);
    }

    private static double aplicarGastosEnvio(final double subtotal, final boolean conEnvio) {
        return conEnvio ? subtotal + GASTOS_ENVIO_URGENTE : subtotal;
    }

    private static double aplicarIva(final double subtotal) {
        return subtotal * FACTOR_IVA;
    }
}
