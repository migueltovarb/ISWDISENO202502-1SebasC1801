# API REST de Vehículos - Spring Boot + MongoDB

API REST para gestionar un CRUD de vehículos usando Java, Spring Boot y MongoDB.

## Requisitos

- Java 17 o superior
- Maven 3.6+
- MongoDB Desktop (corriendo en localhost:27017)

## Configuración

La aplicación se conecta a MongoDB en:
- Host: localhost
- Puerto: 27017
- Base de datos: vehiculos_db

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

## Endpoints

### Obtener todos los vehículos
```
GET /api/vehiculos
```

### Obtener vehículo por ID
```
GET /api/vehiculos/{id}
```

### Obtener vehículo por placa
```
GET /api/vehiculos/placa/{placa}
```

### Obtener vehículos por marca
```
GET /api/vehiculos/marca/{marca}
```

### Crear un vehículo
```
POST /api/vehiculos
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2023,
  "color": "Blanco",
  "placa": "ABC123",
  "precio": 25000.00
}
```

### Actualizar un vehículo
```
PUT /api/vehiculos/{id}
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2024,
  "color": "Negro",
  "placa": "ABC123",
  "precio": 27000.00
}
```

### Eliminar un vehículo
```
DELETE /api/vehiculos/{id}
```

## Estructura del proyecto

```
src/
├── main/
│   ├── java/com/vehiculos/
│   │   ├── VehiculosApiApplication.java
│   │   ├── controller/
│   │   │   └── VehiculoController.java
│   │   ├── model/
│   │   │   └── Vehiculo.java
│   │   ├── repository/
│   │   │   └── VehiculoRepository.java
│   │   └── service/
│   │       └── VehiculoService.java
│   └── resources/
│       └── application.properties
```

## Modelo de datos

```json
{
  "id": "string (generado automáticamente)",
  "marca": "string (requerido)",
  "modelo": "string (requerido)",
  "anio": "integer (requerido, positivo)",
  "color": "string (requerido)",
  "placa": "string (requerido)",
  "precio": "double (requerido, positivo)"
}
```
