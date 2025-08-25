# PharmacyAPI

PharmacyAPI es un sistema de gestión de inventario y movimientos de stock para farmacias, desarrollado en Java con Spring Boot. Permite administrar productos, categorías, proveedores, ventas, usuarios y movimientos de stock, facilitando el control y trazabilidad de los medicamentos y productos farmacéuticos.

## Características principales
- Gestión de productos y categorías
- Control de stock y movimientos de inventario
- Registro de ventas y proveedores
- Gestión de usuarios y roles
- Auditoría de acciones
- API documentada con OpenAPI/Swagger

## Tecnologías utilizadas
- Java 17
- Spring Boot
- Maven
- MySQL
- Spring Data JPA
- OpenAPI/Swagger

## Requisitos previos
- Java 17 instalado
- Maven 3.x instalado
- MySQL instalado y en ejecución

## Configuración e instalación

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/Kevin2407/Pharmacy_back.git
   cd Pharmacy_back
   ```

2. **Configura la base de datos:**
   - Crea una base de datos en MySQL, por ejemplo:
     ```sql
     CREATE DATABASE pharmacydb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
     ```
   - Modifica el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/pharmacydb
     spring.datasource.username=TU_USUARIO
     spring.datasource.password=TU_PASSWORD
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Compila y ejecuta la aplicación:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   La API estará disponible en: `http://localhost:8080`

4. **Accede a la documentación de la API:**
   - Swagger UI: `http://localhost:8080/swagger-ui.html` o `http://localhost:8080/swagger-ui/index.html`

## Estructura del proyecto
- `src/main/java/com/kevin/pharmacyapi/pharmacyapi/` - Código fuente principal
- `entities/` - Entidades JPA (Producto, Categoría, Usuario, etc.)
- `config/` - Configuraciones de seguridad, auditoría y OpenAPI
- `relations/` - Relaciones entre entidades
- `resources/` - Archivos de configuración y recursos estáticos


## Script para agregar datos de prueba

### Eliminación de datos existentes

Primero borramos datos existentes para evitar duplicados
```sql
DELETE FROM stock_movement;
DELETE FROM product;
DELETE FROM category;
```

### Inserción de categorías

```sql
INSERT INTO category (id, name, description) VALUES 
(1, 'Analgésicos', 'Medicamentos para aliviar el dolor'),
(2, 'Antibióticos', 'Medicamentos para tratar infecciones bacterianas'),
(3, 'Dermatológicos', 'Productos para el cuidado de la piel'),
(4, 'Vitaminas', 'Suplementos vitamínicos y minerales'),
(5, 'Gastrointestinales', 'Medicamentos para problemas digestivos');
```

### Inserción de productos con stock inicial

```sql
INSERT INTO product (id, name, description, price, stock, category_id) VALUES 
(1, 'Paracetamol 500mg', 'Tabletas para dolor y fiebre', 5.50, 100, 1),
(2, 'Ibuprofeno 400mg', 'Antiinflamatorio no esteroideo', 7.80, 80, 1),
(3, 'Amoxicilina 500mg', 'Antibiótico de amplio espectro', 12.30, 50, 2),
(4, 'Azitromicina 500mg', 'Antibiótico macrólido', 15.60, 40, 2),
(5, 'Crema hidratante', 'Para piel seca y sensible', 8.90, 30, 3),
(6, 'Protector solar FPS 50', 'Protección contra rayos UVA/UVB', 18.50, 25, 3),
(7, 'Complejo B', 'Suplemento de vitaminas B', 9.70, 60, 4),
(8, 'Vitamina C 1000mg', 'Refuerzo para el sistema inmunológico', 11.40, 70, 4),
(9, 'Omeprazol 20mg', 'Inhibidor de la bomba de protones', 13.20, 45, 5),
(10, 'Loperamida 2mg', 'Antidiarreico', 6.80, 55, 5);
```

### Inserción de movimientos de stock iniciales

```sql
INSERT INTO stock_movement (id, product_id, quantity, movement_type, date, description) VALUES 
(1, 1, 100, 'ENTRADA', NOW(), 'Stock inicial'),
(2, 2, 80, 'ENTRADA', NOW(), 'Stock inicial'),
(3, 3, 50, 'ENTRADA', NOW(), 'Stock inicial'),
(4, 4, 40, 'ENTRADA', NOW(), 'Stock inicial'),
(5, 5, 30, 'ENTRADA', NOW(), 'Stock inicial'),
(6, 6, 25, 'ENTRADA', NOW(), 'Stock inicial'),
(7, 7, 60, 'ENTRADA', NOW(), 'Stock inicial'),
(8, 8, 70, 'ENTRADA', NOW(), 'Stock inicial'),
(9, 9, 45, 'ENTRADA', NOW(), 'Stock inicial'),
(10, 10, 55, 'ENTRADA', NOW(), 'Stock inicial');
```
```
