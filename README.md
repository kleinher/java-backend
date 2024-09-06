# Proyecto Spring Boot con Maven

## Descripción
Este proyecto es una aplicación web construida con Spring Boot y Maven. 
La aplicación es un servicio REST que permite consultar precios de productos en una base de datos H2.

## Base de datos local

La aplicación utiliza una base de datos H2 en memoria. 
Al iniciar la aplicación, se cargan los datos de prueba en la base de datos.

## Requisitos

- Java 21 o superior
- Maven 3.9.9 o superior

## Testing

- Se utiliza JUnit 5 y Mockito para las pruebas unitarias
- Se utiliza Spring Boot Test para las pruebas de integración. Las cuales depende de la misma base de datos H2 que se utiliza en la aplicación.
- Para poder ejecturar las pruebas, es necesario ejecutar el siguiente comando desde el directorio prices:
  - `mvn test`
- Para verificar la cobertura se utiliza jacoco, luego de ejecutar las pruebas se puede obtener el reporte en el directorio
  - `target/site/jacoco/index.html`

## Estructura del Proyecto

- `src/main/java`: Código fuente de la aplicación
- `src/main/resources`: Archivos de configuración y recursos estáticos
- `src/test/java`: Pruebas unitarias y de integración

## Contacto:
- [LinkedIn](https://www.linkedin.com/in/hernan-kleinubing-a04685149/)
- [Correo](mailto:hernan.k97@gmail.com)


