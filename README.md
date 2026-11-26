# FacturacionTienda

Módulo Java que calcula el importe final a cobrar a los clientes de una tienda:
aplica descuentos según el tipo de cliente, recargo por envío urgente e IVA.

## Estructura

- `src/` — código fuente Java
- `docs/` — documentación Javadoc generada (HTML)
- `tests/` — salida esperada para verificar el comportamiento

## Compilar y ejecutar

```bash
javac -d out src/tienda/*.java
java -cp out tienda.FacturacionTienda
```

Práctica P2.1 — Entornos de Desarrollo (1.º DAW).

## Integración continua

Cada `push` y cada Pull Request lanza el workflow `.github/workflows/ci.yml`, que compila
sin avisos, ejecuta el programa, compara su salida con `tests/salida_esperada.txt` y genera el Javadoc.
