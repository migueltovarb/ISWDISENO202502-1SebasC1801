package sistemaseguimientomascotaspaquete;

import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private String nombre;
    private Especie especie;
    private int edad;
    private Duenio duenio;
    private List<ControlVeterinario> controlesVeterinarios;

    public Mascota(String nombre, Especie especie, int edad, Duenio duenio) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.duenio = duenio;
        this.controlesVeterinarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Especie getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public List<ControlVeterinario> getControlesVeterinarios() {
        return controlesVeterinarios;
    }

    public void addControlVeterinario(ControlVeterinario control) {
        controlesVeterinarios.add(control);
    }

    @Override
    public String toString() {
        return "Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad +
               " | Dueño: " + duenio.getNombreCompleto() + " | #controles: " + controlesVeterinarios.size();
    }
}




