package tienda;

/**
 * Calcula el importe final a cobrar a los clientes de una tienda.
 *
 * <p>El cálculo se realiza en este orden:</p>
 * <ol>
 *   <li>Subtotal = precio base × unidades.</li>
 *   <li>Descuento según el tipo de cliente.</li>
 *   <li>Recargo por envío urgente a domicilio (opcional).</li>
 *   <li>IVA general del 21&nbsp;%.</li>
 * </ol>
 *
 * @author Iker Extremera De Manuel
 * @version 1.0
 */
public final class FacturacionTienda {

    /** Código del tipo de cliente estudiante (descuento del 10&nbsp;%). */
    public static final int CLIENTE_ESTUDIANTE = 1;

    /** Código del tipo de cliente jubilado (descuento del 15&nbsp;%). */
    public static final int CLIENTE_JUBILADO = 2;

    /** Código del tipo de cliente familia numerosa (descuento del 12&nbsp;%). */
    public static final int CLIENTE_FAMILIA_NUMEROSA = 4;

    /** Porcentaje de descuento para estudiantes (10&nbsp;%). */
    private static final double DESCUENTO_ESTUDIANTE = 0.10;

    /** Porcentaje de descuento para jubilados (15&nbsp;%). */
    private static final double DESCUENTO_JUBILADO = 0.15;

    /** Porcentaje de descuento para familia numerosa (12&nbsp;%). */
    private static final double DESCUENTO_FAMILIA_NUMEROSA = 0.12;

    /** Porcentaje aplicado a clientes sin descuento. */
    private static final double SIN_DESCUENTO = 0.0;

    /** Recargo fijo en euros por envío urgente a domicilio. */
    private static final double GASTOS_ENVIO_URGENTE = 4.95;

    /** Multiplicador del IVA general (1 + 21&nbsp;%). */
    private static final double FACTOR_IVA = 1.21;

    /**
     * Constructor privado: es una clase de utilidad y no debe instanciarse.
     */
    private FacturacionTienda() {
        // Clase de utilidad: no se instancia
    }

    /**
     * Punto de entrada del programa. Ejecuta una prueba de funcionamiento
     * con 2 unidades de 100&nbsp;€ para un estudiante con envío urgente.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(final String[] args) {
        double importeTotal = calcularImporteTotal(100.0, 2, CLIENTE_ESTUDIANTE, true);
        System.out.println("Importe total a pagar: " + importeTotal);
    }

    /**
     * Calcula el importe total a pagar aplicando descuento, envío e IVA.
     *
     * @param precioBase  precio unitario del producto en euros
     * @param unidades    número de unidades compradas
     * @param tipoCliente tipo de cliente: {@link #CLIENTE_ESTUDIANTE},
     *                    {@link #CLIENTE_JUBILADO} o cualquier otro valor
     *                    para un cliente sin descuento
     * @param conEnvio    {@code true} si se añade el envío urgente a domicilio
     * @return importe total en euros, con IVA incluido
     */
    public static double calcularImporteTotal(final double precioBase, final int unidades,
            final int tipoCliente, final boolean conEnvio) {
        double subtotal = calcularSubtotal(precioBase, unidades);
        subtotal = aplicarDescuento(subtotal, tipoCliente);
        subtotal = aplicarGastosEnvio(subtotal, conEnvio);
        return aplicarIva(subtotal);
    }

    /**
     * Calcula el subtotal bruto de la compra.
     *
     * @param precioBase precio unitario en euros
     * @param unidades   número de unidades
     * @return precio base multiplicado por las unidades
     */
    private static double calcularSubtotal(final double precioBase, final int unidades) {
        return precioBase * unidades;
    }

    /**
     * Devuelve el porcentaje de descuento que corresponde a un tipo de cliente.
     *
     * @param tipoCliente código del tipo de cliente
     * @return porcentaje de descuento expresado entre 0 y 1
     */
    private static double obtenerPorcentajeDescuento(final int tipoCliente) {
        return switch (tipoCliente) {
            case CLIENTE_ESTUDIANTE -> DESCUENTO_ESTUDIANTE;
            case CLIENTE_JUBILADO -> DESCUENTO_JUBILADO;
            case CLIENTE_FAMILIA_NUMEROSA -> DESCUENTO_FAMILIA_NUMEROSA;
            default -> SIN_DESCUENTO;
        };
    }

    /**
     * Aplica al subtotal el descuento del tipo de cliente.
     *
     * @param subtotal    importe antes del descuento
     * @param tipoCliente código del tipo de cliente
     * @return importe con el descuento aplicado
     */
    private static double aplicarDescuento(final double subtotal, final int tipoCliente) {
        return subtotal - subtotal * obtenerPorcentajeDescuento(tipoCliente);
    }

    /**
     * Suma los gastos de envío urgente si el cliente los ha solicitado.
     *
     * @param subtotal importe antes del envío
     * @param conEnvio {@code true} si se añade el envío urgente
     * @return importe con o sin gastos de envío
     */
    private static double aplicarGastosEnvio(final double subtotal, final boolean conEnvio) {
        return conEnvio ? subtotal + GASTOS_ENVIO_URGENTE : subtotal;
    }

    /**
     * Aplica el IVA general al importe.
     *
     * @param subtotal importe sin impuestos
     * @return importe con IVA incluido
     */
    private static double aplicarIva(final double subtotal) {
        return subtotal * FACTOR_IVA;
    }
}
