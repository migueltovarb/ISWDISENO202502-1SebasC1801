package sistemaseguimientomascotaspaquete;

import java.time.LocalDate;

public class ControlVeterinario {
    private LocalDate fecha;
    private TipoControl tipoControl;
    private String observaciones;

    public ControlVeterinario(LocalDate fecha, TipoControl tipoControl, String observaciones) {
        this.fecha = fecha;
        this.tipoControl = tipoControl;
        this.observaciones = observaciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public TipoControl getTipoControl() {
        return tipoControl;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTipoControl(TipoControl tipoControl) {
        this.tipoControl = tipoControl;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + " | Tipo: " + tipoControl + " | Obs: " + observaciones;
    }
}




