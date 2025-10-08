package sistemaseguimientomascotaspaquete;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Duenio> duenios;
    private List<Mascota> mascotas;
    private List<ControlVeterinario> controlesVeterinarios;

    public Sistema() {
        this.duenios = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.controlesVeterinarios = new ArrayList<>();
    }


    public void registrarDuenio(Duenio duenio) {
        if (buscarDuenioPorDocumento(duenio.getDocumento()) != null) {
            System.out.println("Ya existe un dueño con ese documento.");
            return;
        }
        duenios.add(duenio);
    }

    public Duenio buscarDuenioPorDocumento(String documento) {
        for (Duenio d : duenios) {
            if (d.getDocumento().equals(documento)) return d;
        }
        return null;
    }

    public void registrarMascota(Mascota mascota) {
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(mascota.getNombre())
                    && m.getDuenio().getDocumento().equals(mascota.getDuenio().getDocumento())) {
                System.out.println("Ya existe una mascota con ese nombre para ese dueño.");
                return;
            }
        }
        mascotas.add(mascota);
    }

    public Mascota buscarMascotaPorNombre(String nombre) {
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        }
        return null;
    }

    public void registrarControl(Mascota mascota, ControlVeterinario control) {
        if (!mascotas.contains(mascota)) {
            System.out.println("La mascota no está registrada en el sistema.");
            return;
        }
        mascota.addControlVeterinario(control);
        controlesVeterinarios.add(control);
    }

    public void generarResumen(Mascota mascota) {
        if (mascota == null) {
            System.out.println("Mascota nula (no se puede generar resumen).");
            return;
        }
        System.out.println("\n=== RESUMEN MASCOTA ===");
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Especie: " + mascota.getEspecie());
        System.out.println("Edad: " + mascota.getEdad());
        System.out.println("Dueño: " + mascota.getDuenio().getNombreCompleto() + " (Doc: " + mascota.getDuenio().getDocumento() + ")");
        System.out.println("Controles (" + mascota.getControlesVeterinarios().size() + "):");
        for (ControlVeterinario c : mascota.getControlesVeterinarios()) {
            System.out.println(" - " + c);
        }
    }
}

