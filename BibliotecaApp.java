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
        System.out.println("=== Biblioteca: Gestión de Préstamos ===");
        System.out.println("1. Registrar nuevo préstamo");
        System.out.println("2. Mostrar todos los préstamos");
        System.out.println("3. Buscar préstamo por ID");
        System.out.println("4. Actualizar un préstamo");
        System.out.println("5. Eliminar un préstamo");
        System.out.println("6. Calcular total de multas");
        System.out.println("7. Salir");
    }

    // ====== CRUD (por implementar) ======
    static void registrarPrestamo() {
        int idPrestamo = generarIdUnico();
        String nombreUsuario = leerTexto("Nombre del usuario: ");
        String tituloLibro = leerTexto("Título del libro: ");
        int diasPrestamo = leerEntero("Días de préstamo: ");
        double multaPorDia = leerDouble("Multa por día (en $): ");

        ArrayList<Object> nuevoPrestamo = new ArrayList<>();
        nuevoPrestamo.add(idPrestamo);
        nuevoPrestamo.add(nombreUsuario);
        nuevoPrestamo.add(tituloLibro);
        nuevoPrestamo.add(diasPrestamo);
        nuevoPrestamo.add(multaPorDia);

        prestamos.add(nuevoPrestamo);
        System.out.println("✓ Préstamo registrado con ID: " + idPrestamo);
    }

    static void mostrarPrestamos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        System.out.println("\n=== PRÉSTAMOS REGISTRADOS ===");
        for (ArrayList<Object> prestamo : prestamos) {
            int idPrestamo = (int) prestamo.get(0);
            String nombreUsuario = (String) prestamo.get(1);
            String tituloLibro = (String) prestamo.get(2);
            int diasPrestamo = (int) prestamo.get(3);
            double multaPorDia = (double) prestamo.get(4);
            double multa = diasPrestamo * multaPorDia;

            System.out.println("ID: " + idPrestamo);
            System.out.println("  Usuario: " + nombreUsuario);
            System.out.println("  Libro: " + tituloLibro);
            System.out.println("  Días: " + diasPrestamo);
            System.out.println("  Multa/día: $" + multaPorDia);
            System.out.println("  Multa total: $" + multa);
            System.out.println();
        }
    }

    static void buscarPrestamoPorId() {
        int id = leerEntero("Ingrese el ID del préstamo: ");

        for (ArrayList<Object> prestamo : prestamos) {
            if ((int) prestamo.get(0) == id) {
                System.out.println("\n=== PRÉSTAMO ENCONTRADO ===");
                System.out.println("ID: " + prestamo.get(0));
                System.out.println("Usuario: " + prestamo.get(1));
                System.out.println("Libro: " + prestamo.get(2));
                System.out.println("Días: " + prestamo.get(3));
                System.out.println("Multa/día: $" + prestamo.get(4));
                double multa = (int) prestamo.get(3) * (double) prestamo.get(4);
                System.out.println("Multa total: $" + multa);
                return;
            }
        }

        System.out.println("✗ Préstamo con ID " + id + " no encontrado.");
    }

    static void actualizarPrestamo() {
        int id = leerEntero("Ingrese el ID del préstamo a actualizar: ");

        for (ArrayList<Object> prestamo : prestamos) {
            if ((int) prestamo.get(0) == id) {
                System.out.println("Deje en blanco para no cambiar:");
                
                String nuevoUsuario = leerTexto("Nuevo nombre del usuario: ");
                if (!nuevoUsuario.isEmpty()) {
                    prestamo.set(1, nuevoUsuario);
                }

                String nuevoLibro = leerTexto("Nuevo título del libro: ");
                if (!nuevoLibro.isEmpty()) {
                    prestamo.set(2, nuevoLibro);
                }

                String diasStr = leerTexto("Nuevos días de préstamo: ");
                if (!diasStr.isEmpty()) {
                    try {
                        prestamo.set(3, Integer.parseInt(diasStr));
                    } catch (Exception e) {
                        System.out.println("Valor inválido, no se actualizó.");
                    }
                }

                String multaStr = leerTexto("Nueva multa por día: ");
                if (!multaStr.isEmpty()) {
                    try {
                        prestamo.set(4, Double.parseDouble(multaStr));
                    } catch (Exception e) {
                        System.out.println("Valor inválido, no se actualizó.");
                    }
                }

                System.out.println("✓ Préstamo actualizado.");
                return;
            }
        }

        System.out.println("✗ Préstamo con ID " + id + " no encontrado.");
    }

    static void eliminarPrestamo() {
        int id = leerEntero("Ingrese el ID del préstamo a eliminar: ");

        for (int i = 0; i < prestamos.size(); i++) {
            if ((int) prestamos.get(i).get(0) == id) {
                prestamos.remove(i);
                System.out.println("✓ Préstamo eliminado.");
                return;
            }
        }

        System.out.println("✗ Préstamo con ID " + id + " no encontrado.");
    }

    // ====== Cálculo (por implementar) ======
    static void calcularTotalMultas() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        double totalMultas = 0;
        System.out.println("\n=== CÁLCULO DE MULTAS ===");
        
        for (ArrayList<Object> prestamo : prestamos) {
            int id = (int) prestamo.get(0);
            String usuario = (String) prestamo.get(1);
            int dias = (int) prestamo.get(3);
            double multaPorDia = (double) prestamo.get(4);
            double multa = dias * multaPorDia;
            
            System.out.println("ID " + id + " (" + usuario + "): $" + multa);
            totalMultas += multa;
        }
        
        System.out.println("\nTOTAL DE MULTAS: $" + totalMultas);
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
}