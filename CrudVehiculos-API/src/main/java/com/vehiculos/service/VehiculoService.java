package com.vehiculos.service;

import com.vehiculos.model.Vehiculo;
import com.vehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    
    @Autowired
    private VehiculoRepository vehiculoRepository;
    
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }
    
    public Optional<Vehiculo> obtenerPorId(String id) {
        return vehiculoRepository.findById(id);
    }
    
    public Optional<Vehiculo> obtenerPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }
    
    public List<Vehiculo> obtenerPorMarca(String marca) {
        return vehiculoRepository.findByMarca(marca);
    }
    
    public Vehiculo crear(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }
    
    public Optional<Vehiculo> actualizar(String id, Vehiculo vehiculo) {
        return vehiculoRepository.findById(id)
            .map(v -> {
                v.setMarca(vehiculo.getMarca());
                v.setModelo(vehiculo.getModelo());
                v.setAnio(vehiculo.getAnio());
                v.setColor(vehiculo.getColor());
                v.setPlaca(vehiculo.getPlaca());
                v.setPrecio(vehiculo.getPrecio());
                return vehiculoRepository.save(v);
            });
    }
    
    public boolean eliminar(String id) {
        return vehiculoRepository.findById(id)
            .map(v -> {
                vehiculoRepository.delete(v);
                return true;
            })
            .orElse(false);
    }
}
