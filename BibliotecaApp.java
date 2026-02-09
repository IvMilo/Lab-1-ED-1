import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    // prestamo = [idPrestamo, nombreUsuario, tituloLibro, diasPrestamo, multaPorDia]
    static ArrayList<ArrayList<Object>> prestamos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 2:
                    mostrarPrestamos();
                    break;
                case 3:
                    buscarPrestamoPorId();
                    break;
                case 4:
                    actualizarPrestamo();
                    break;
                case 5:
                    eliminarPrestamo();
                    break;
                case 6:
                    calcularTotalMultas();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 7);

        sc.close();
    }

    static void mostrarMenu() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║     BIBLIOTECA: GESTIÓN DE PRÉSTAMOS DE LIBROS     ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║                                                    ║");
        System.out.println("║  ┌────────────────────────────────────────────┐    ║");
        System.out.println("║  │ 1. Registrar nuevo préstamo                │    ║");
        System.out.println("║  │ 2. Mostrar todos los préstamos             │    ║");
        System.out.println("║  │ 3. Buscar préstamo por ID                  │    ║");
        System.out.println("║  │ 4. Actualizar un préstamo                  │    ║");
        System.out.println("║  │ 5. Eliminar un préstamo                    │    ║");
        System.out.println("║  │ 6. Calcular total de multas                │    ║");
        System.out.println("║  │ 7. Salir del programa                      │    ║");
        System.out.println("║  └────────────────────────────────────────────┘    ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    // ====== CRUD (por implementar) ======
    static void registrarPrestamo() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║         REGISTRAR NUEVO PRÉSTAMO DE LIBRO          ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║                                                    ║");
        
        int idPrestamo = generarIdUnico();
        String nombreUsuario = leerTexto("║  Nombre del usuario: ");
        String tituloLibro = leerTexto("║  Título del libro: ");
        int diasPrestamo = leerEntero("║  Días de préstamo: ");
        double multaPorDia = leerDouble("║  Multa por día ($): ");

        ArrayList<Object> nuevoPrestamo = new ArrayList<>();
        nuevoPrestamo.add(idPrestamo);
        nuevoPrestamo.add(nombreUsuario);
        nuevoPrestamo.add(tituloLibro);
        nuevoPrestamo.add(diasPrestamo);
        nuevoPrestamo.add(multaPorDia);

        prestamos.add(nuevoPrestamo);
        System.out.println("║                                                    ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
        System.out.println("║  │ [OK] Préstamo registrado exitosamente!      │  ║");
        System.out.println("║  │ ID asignado: #" + String.format("%-35d", idPrestamo) + "│  ║");
        System.out.println("║  └──────────────────────────────────────────────┘  ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        pausa();
    }

    static void mostrarPrestamos() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║             LISTA DE PRÉSTAMOS REGISTRADOS         ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        
        if (prestamos.isEmpty()) {
            System.out.println("║                                                    ║");
            System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
            System.out.println("║  │ [ALERTA] No hay préstamos registrados        │  ║");
            System.out.println("║  └──────────────────────────────────────────────┘  ║");
            System.out.println("║                                                    ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println();
            pausa();
            return;
        }

        System.out.println("║                                                    ║");
        System.out.println("║  ┌────┬──────────────┬──────┬────────┬──────────┐  ║");
        System.out.println("║  │ ID │ Usuario      │ Libro│ Días   │ Multa    │  ║");
        System.out.println("║  ├────┼──────────────┼──────┼────────┼──────────┤  ║");
        
        for (ArrayList<Object> prestamo : prestamos) {
            int idPrestamo = (int) prestamo.get(0);
            String nombreUsuario = (String) prestamo.get(1);
            String tituloLibro = (String) prestamo.get(2);
            int diasPrestamo = (int) prestamo.get(3);
            double multaPorDia = (double) prestamo.get(4);
            double multa = diasPrestamo * multaPorDia;

            System.out.printf("║  │ %2d │ %-12s │ %4d │ $%6.2f │ $%7.2f │  ║%n",
                idPrestamo,
                truncar(nombreUsuario, 12),
                diasPrestamo,
                multaPorDia,
                multa);
        }
        System.out.println("║  └────┴──────────────┴──────┴────────┴──────────┘  ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        pausa();
    }

    static void buscarPrestamoPorId() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║            BUSCAR PRÉSTAMO POR IDENTIFICADOR       ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║                                                    ║");
        
        int id = leerEntero("║  Ingrese el ID del préstamo: ");

        for (ArrayList<Object> prestamo : prestamos) {
            if ((int) prestamo.get(0) == id) {
                System.out.println("║                                                    ║");
                System.out.println("╠════════════════════════════════════════════════════╣");
                System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
                System.out.println("║  │ [OK] Préstamo encontrado exitosamente        │  ║");
                System.out.println("║  ├──────────────────────────────────────────────┤  ║");
                System.out.printf("║  │ ID: #%-42d│  ║%n", prestamo.get(0));
                System.out.printf("║  │ Usuario: %-36s│  ║%n", truncar((String) prestamo.get(1), 36));
                System.out.printf("║  │ Libro: %-38s│  ║%n", truncar((String) prestamo.get(2), 38));
                System.out.printf("║  │ Días: %-39d│  ║%n", prestamo.get(3));
                double multa = (int) prestamo.get(3) * (double) prestamo.get(4);
                System.out.printf("║  │ Multa/día: $%-34.2f│  ║%n", prestamo.get(4));
                System.out.printf("║  │ Multa total: $%-32.2f│  ║%n", multa);
                System.out.println("║  └──────────────────────────────────────────────┘  ║");
                System.out.println("║                                                    ║");
                System.out.println("╚════════════════════════════════════════════════════╝");
                System.out.println();
                pausa();
                return;
            }
        }

        System.out.println("║                                                    ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
        System.out.println("║  │ [ERROR] Préstamo con ID " + String.format("%-18d", id) + "│  ║");
        System.out.println("║  │ no encontrado en la base de datos           │  ║");
        System.out.println("║  └──────────────────────────────────────────────┘  ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        pausa();
    }

    static void actualizarPrestamo() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║             ACTUALIZAR UN PRÉSTAMO EXISTENTE       ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║                                                    ║");
        
        int id = leerEntero("║  Ingrese el ID del préstamo a actualizar: ");

        for (ArrayList<Object> prestamo : prestamos) {
            if ((int) prestamo.get(0) == id) {
                System.out.println("║                                                    ║");
                System.out.println("║  [INFO] Deje en blanco para no cambiar:             ║");
                System.out.println("║                                                    ║");
                
                int cambios = 0;
                
                String nuevoUsuario = leerTexto("║  Nuevo nombre del usuario: ");
                if (!nuevoUsuario.isEmpty()) {
                    prestamo.set(1, nuevoUsuario);
                    cambios++;
                }

                String nuevoLibro = leerTexto("║  Nuevo título del libro: ");
                if (!nuevoLibro.isEmpty()) {
                    prestamo.set(2, nuevoLibro);
                    cambios++;
                }

                String diasStr = leerTexto("║  Nuevos días de préstamo: ");
                if (!diasStr.isEmpty()) {
                    try {
                        prestamo.set(3, Integer.parseInt(diasStr));
                        cambios++;
                    } catch (Exception e) {
                        System.out.println("║  [ERROR] Valor inválido, no se actualizó.");
                    }
                }

                String multaStr = leerTexto("║  Nueva multa por día ($): ");
                if (!multaStr.isEmpty()) {
                    try {
                        prestamo.set(4, Double.parseDouble(multaStr));
                        cambios++;
                    } catch (Exception e) {
                        System.out.println("║  [ERROR] Valor inválido, no se actualizó.");
                    }
                }

                System.out.println("║                                                    ║");
                System.out.println("╠════════════════════════════════════════════════════╣");
                System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
                if (cambios > 0) {
                    System.out.println("║  │ [OK] Préstamo actualizado exitosamente      │  ║");
                    System.out.printf("║  │ Cambios realizados: %-24d│  ║%n", cambios);
                } else {
                    System.out.println("║  │ [INFO] No se realizaron cambios              │  ║");
                }
                System.out.println("║  └──────────────────────────────────────────────┘  ║");
                System.out.println("║                                                    ║");
                System.out.println("╚════════════════════════════════════════════════════╝");
                System.out.println();
                pausa();
                return;
            }
        }

        System.out.println("║                                                    ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
        System.out.println("║  │ [ERROR] Préstamo con ID " + String.format("%-18d", id) + "│  ║");
        System.out.println("║  │ no encontrado en la base de datos           │  ║");
        System.out.println("║  └──────────────────────────────────────────────┘  ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        pausa();
    }

    static void eliminarPrestamo() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║            ELIMINAR UN PRÉSTAMO DE LA BASE         ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║                                                    ║");
        
        int id = leerEntero("║  Ingrese el ID del préstamo a eliminar: ");

        for (int i = 0; i < prestamos.size(); i++) {
            if ((int) prestamos.get(i).get(0) == id) {
                String usuario = (String) prestamos.get(i).get(1);
                String libro = (String) prestamos.get(i).get(2);
                
                System.out.println("║                                                    ║");
                System.out.println("║  [ALERTA] Confirme la elimación del préstamo:     ║");
                System.out.printf("║  Usuario: %-38s║%n", truncar(usuario, 38));
                System.out.printf("║  Libro: %-40s║%n", truncar(libro, 40));
                System.out.println("║                                                    ║");
                
                String respuesta = leerTexto("║  Escriba 'si' para confirmar: ");
                
                if (respuesta.equalsIgnoreCase("si")) {
                    prestamos.remove(i);
                    System.out.println("║                                                    ║");
                    System.out.println("╠════════════════════════════════════════════════════╣");
                    System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
                    System.out.println("║  │ [OK] Préstamo eliminado exitosamente         │  ║");
                    System.out.println("║  └──────────────────────────────────────────────┘  ║");
                    System.out.println("║                                                    ║");
                } else {
                    System.out.println("║                                                    ║");
                    System.out.println("╠════════════════════════════════════════════════════╣");
                    System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
                    System.out.println("║  │ [INFO] Eliminación cancelada por el usuario  │  ║");
                    System.out.println("║  └──────────────────────────────────────────────┘  ║");
                    System.out.println("║                                                    ║");
                }
                System.out.println("╚════════════════════════════════════════════════════╝");
                System.out.println();
                pausa();
                return;
            }
        }

        System.out.println("║                                                    ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
        System.out.println("║  │ [ERROR] Préstamo con ID " + String.format("%-18d", id) + "│  ║");
        System.out.println("║  │ no encontrado en la base de datos           │  ║");
        System.out.println("║  └──────────────────────────────────────────────┘  ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        pausa();
    }

    // ====== Cálculo (por implementar) ======
    static void calcularTotalMultas() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║          CÁLCULO DE MULTAS POR RETRASO DE LIBROS   ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        
        if (prestamos.isEmpty()) {
            System.out.println("║                                                    ║");
            System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
            System.out.println("║  │ [ALERTA] No hay préstamos registrados        │  ║");
            System.out.println("║  └──────────────────────────────────────────────┘  ║");
            System.out.println("║                                                    ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println();
            pausa();
            return;
        }

        double totalMultas = 0;
        System.out.println("║                                                    ║");
        System.out.println("║  ┌────┬──────────────┬──────────┬──────────────┐  ║");
        System.out.println("║  │ ID │ Usuario      │ Multa/día│ Multa Total  │  ║");
        System.out.println("║  ├────┼──────────────┼──────────┼──────────────┤  ║");
        
        for (ArrayList<Object> prestamo : prestamos) {
            int id = (int) prestamo.get(0);
            String usuario = (String) prestamo.get(1);
            int dias = (int) prestamo.get(3);
            double multaPorDia = (double) prestamo.get(4);
            double multa = dias * multaPorDia;
            
            System.out.printf("║  │ %2d │ %-12s │ $%7.2f │ $%10.2f  │  ║%n",
                id,
                truncar(usuario, 12),
                multaPorDia,
                multa);
            totalMultas += multa;
        }
        
        System.out.println("║  └────┴──────────────┴──────────┴──────────────┘  ║");
        System.out.println("║                                                    ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ┌──────────────────────────────────────────────┐  ║");
        System.out.printf("║  │ TOTAL DE MULTAS: $%-29.2f│  ║%n", totalMultas);
        System.out.println("║  └──────────────────────────────────────────────┘  ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        pausa();
    }

    // ====== Utilidades mínimas ======
    static int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ingrese un entero válido.");
            }
        }
    }

    static String leerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static double leerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    static int generarIdUnico() {
        if (prestamos.isEmpty()) {
            return 1;
        }
        int maxId = 0;
        for (ArrayList<Object> prestamo : prestamos) {
            int id = (int) prestamo.get(0);
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }

    // ====== Métodos auxiliares para mejorar interfaz ======
    static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.print("  Presione Enter para continuar...");
        sc.nextLine();
    }

    static String truncar(String texto, int longitud) {
        if (texto.length() > longitud) {
            return texto.substring(0, longitud - 3) + "...";
        }
        return texto;
    }
}