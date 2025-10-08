package sistemaseguimientomascotaspaquete;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Interfaz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema();
        int opcion = -1;
        DateTimeFormatter fYYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE;

        do {
            System.out.println("\n=== SISTEMA DE SEGUIMIENTO DE MASCOTAS ===");
            System.out.println("1. Registrar dueño");
            System.out.println("2. Registrar mascota");
            System.out.println("3. Registrar control veterinario");
            System.out.println("4. Consultar historial médico de una mascota");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String linea = sc.nextLine().trim();
            try {
                opcion = Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n--- Registrar dueño ---");
                    System.out.print("Nombre completo: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Documento: ");
                    String documento = sc.nextLine().trim();
                    System.out.print("Telefono: ");
                    String telefono = sc.nextLine().trim();

                    Duenio duenio = new Duenio(nombre, documento, telefono);
                    sistema.registrarDuenio(duenio);
                    System.out.println("Dueño registrado.");
                }

                case 2 -> {
                    System.out.println("\n--- Registrar mascota ---");
                    System.out.print("Documento del dueño: ");
                    String documento = sc.nextLine().trim();
                    Duenio duenio = sistema.buscarDuenioPorDocumento(documento);
                    if (duenio == null) {
                        System.out.println("No se encontró el dueño. Registre primero al duenio.");
                        break;
                    }

                    System.out.print("Nombre de la mascota: ");
                    String nombreMascota = sc.nextLine().trim();

                    System.out.println("Especies disponibles:");
                    for (Especie e : Especie.values()) {
                        System.out.println(" - " + e);
                    }
                    System.out.print("Escriba la especie (tal cual aparece): ");
                    String especieStr = sc.nextLine().trim().toUpperCase();

                    Especie especie;
                    try {
                        especie = Especie.valueOf(especieStr);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Especie inválida. Operación cancelada.");
                        break;
                    }

                    System.out.print("Edad (años): ");
                    int edad;
                    try {
                        edad = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("Edad inválida. Operación cancelada.");
                        break;
                    }

                    Mascota mascota = new Mascota(nombreMascota, especie, edad, duenio);
                    sistema.registrarMascota(mascota);
                    System.out.println("Mascota registrada.");
                }

                case 3 -> {
                    System.out.println("\n--- Registrar control veterinario ---");
                    System.out.print("Nombre de la mascota: ");
                    String nombreMascota = sc.nextLine().trim();
                    Mascota mascota = sistema.buscarMascotaPorNombre(nombreMascota);
                    if (mascota == null) {
                        System.out.println("Mascota no encontrada.");
                        break;
                    }

                    System.out.print("Fecha del control (YYYY-MM-DD o YYYYMMDD) [enter = hoy]: ");
                    String fechaInput = sc.nextLine().trim();
                    LocalDate fecha;
                    if (fechaInput.isEmpty()) {
                        fecha = LocalDate.now();
                    } else {
                        try {
                            if (fechaInput.matches("\\d{8}")) {
                                fecha = LocalDate.parse(fechaInput, fYYYYMMDD);
                            } else {
                                fecha = LocalDate.parse(fechaInput, iso);
                            }
                        } catch (DateTimeParseException ex) {
                            System.out.println("Formato de fecha inválido. Operación cancelada.");
                            break;
                        }
                    }

                    System.out.println("Tipos de control:");
                    TipoControl[] tipos = TipoControl.values();
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.println((i + 1) + ". " + tipos[i]);
                    }
                    System.out.print("Seleccione número del tipo: ");
                    int idx;
                    try {
                        idx = Integer.parseInt(sc.nextLine().trim());
                        if (idx < 1 || idx > tipos.length) {
                            System.out.println("Opción fuera de rango.");
                            break;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    TipoControl tipoSeleccionado = tipos[idx - 1];

                    System.out.print("Observaciones: ");
                    String obs = sc.nextLine().trim();

                    ControlVeterinario control = new ControlVeterinario(fecha, tipoSeleccionado, obs);
                    sistema.registrarControl(mascota, control);
                    System.out.println("Control registrado.");
                }

                case 4 -> {
                    System.out.println("\n--- Consultar historial médico ---");
                    System.out.print("Nombre de la mascota: ");
                    String nombreMascota = sc.nextLine().trim();
                    Mascota mascota = sistema.buscarMascotaPorNombre(nombreMascota);
                    if (mascota == null) {
                        System.out.println("Mascota no encontrada.");
                        break;
                    }
                    sistema.generarResumen(mascota);
                }

                case 5 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
