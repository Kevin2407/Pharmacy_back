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

