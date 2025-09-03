package CONTROL_ASISTENCIA_ESTUDIANTES_PACKAGE;

import java.util.Scanner;

public class CONTROL_ASISTENCIA_ESTUDIANTES {
    
    static final int DIAS_SEMANA = 5;
    static final int NUM_ESTUDIANTES = 4;
    static String[][] asistencia = new String[NUM_ESTUDIANTES][DIAS_SEMANA];
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        registrarAsistencia();
        menuOpciones();        
    }

    static void registrarAsistencia() {
        System.out.println("=== Registro de asistencia ===");
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            System.out.println("Estudiante " + (i + 1) + ":");
            for (int j = 0; j < DIAS_SEMANA; j++) {
                String letraConfirmacionAsistencia;
                do {
                    System.out.print("  Día " + (j + 1) + " (P/A): ");
                    letraConfirmacionAsistencia = scanner.next();
                } while (!(letraConfirmacionAsistencia.equals("P") || letraConfirmacionAsistencia.equals("p") || letraConfirmacionAsistencia.equals("A") || letraConfirmacionAsistencia.equals("a")));
                
                if (letraConfirmacionAsistencia.equals("p")) letraConfirmacionAsistencia = "P";
                if (letraConfirmacionAsistencia.equals("a")) letraConfirmacionAsistencia = "A";

                asistencia[i][j] = letraConfirmacionAsistencia;
            }
        }
    }

    static void menuOpciones() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ver asistencia individual");
            System.out.println("2. Ver resumen general");
            System.out.println("3. Volver a registrar");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    verAsistenciaIndividual();
                    break;
                case 2:
                    verResumenGeneral();
                    break;
                case 3:
                    registrarAsistencia();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    static void verAsistenciaIndividual() {
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            System.out.print("Estudiante " + (i + 1) + ": ");
            for (int j = 0; j < DIAS_SEMANA; j++) {
                System.out.print(asistencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void verResumenGeneral() {
        int[] ausenciasPorDia = new int[DIAS_SEMANA];
        System.out.println("\nResumen de asistencia:");

        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            int totalPresente = 0;
            for (int j = 0; j < DIAS_SEMANA; j++) {
                if (asistencia[i][j].equals("P")) {
                    totalPresente++;
                } else {
                    ausenciasPorDia[j]++;
                }
            }
            System.out.println("Estudiante " + (i + 1) + ": " + totalPresente + " asistencias");
        }

        System.out.print("Estudiantes con asistencia perfecta: ");
        boolean hayPerfectos = false;
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            boolean perfecto = true;
            for (int j = 0; j < DIAS_SEMANA; j++) {
                if (!asistencia[i][j].equals("P")) {
                    perfecto = false;
                    break;
                }
            }
            if (perfecto) {
                System.out.print((i + 1) + " ");
                hayPerfectos = true;
            }
        }
        if (!hayPerfectos) System.out.print("Ninguno");
        System.out.println();

        int maxAusencias = 0, diaMaxAusencias = 0;
        for (int j = 0; j < DIAS_SEMANA; j++) {
            if (ausenciasPorDia[j] > maxAusencias) {
                maxAusencias = ausenciasPorDia[j];
                diaMaxAusencias = j;
            }
        }
        System.out.println("Día con más ausencias: Día " + (diaMaxAusencias + 1));
        
    } 
}


