# 📚 Estructura de Datos - Trabajos Prácticos en Java

Repositorio académico que recopila soluciones algorítmicas y proyectos interactivos desarrollados en **Java**, correspondientes a la cátedra de **Estructura de Datos**.

---

## 🛠️ Tecnologías Empleadas
* **Lenguaje:** Java 
* **Gestor de dependencias / Estructura:** Apache Maven
* **IDE:** IntelliJ IDEA
* **Interfaz Gráfica:** Java Swing / AWT

---

## 📂 Contenido del Repositorio

A continuación se detalla cada ejercicio implementado en el paquete `org.example`, acompañado de una analogía sencilla para comprender su lógica de fondo:

### 🔹 Ejercicio 1: Vector Dinámico de Notas (`Ejercicio_1.java`)
* **¿Qué hace?:** Solicita la cantidad $n$ de notas, reserva un vector con ese tamaño exacto, calcula la nota más alta y el promedio general.
* **Explicación simple:** Es como preparar una lista con la cantidad justa de casilleros para no desperdiciar papel y luego buscar con una regla cuál fue el número más alto.

### 🔹 Ejercicio 2: Contador de Aprobados y Desaprobados (`Ejercicio_2.java`)
* **¿Qué hace?:** Carga un vector de notas y clasifica cuántos alumnos obtuvieron una nota $\ge 6$ y cuántos $< 6$.
* **Explicación simple:** Funciona como un filtro de aduana que cuenta cuántas personas pasan de largo con sello verde (aprobados) y cuántas van a revisión (desaprobados).

### 🔹 Ejercicio 3: Inventario con Vectores Paralelos (`Ejercicio_3.java`)
* **¿Qué hace?:** Maneja dos vectores sincronizados por el mismo índice (uno de cantidades y otro de costos). Calcula el subtotal por producto, el total general y resalta aquellos que superan los $1000.
* **Explicación simple:** Es el ticket de compra del supermercado: toma cantidad de paquetes $\times$ precio unitario y te avisa qué compras salieron más caras.

### 🔹 Ejercicio 4: Registro de Salida de Transporte (`Ejercicio_4.java`)
* **¿Qué hace?:** Modela mediante vectores paralelos el egreso de una flota de camiones (patente, chofer, hora y carga), contabilizando cuántos transportaron "té".
* **Explicación simple:** Funciona como la garita de seguridad de una fábrica donde el guardia anota cada camión en una planilla y al final del día cuenta los cargamentos específicos.

### 🔹 Ejercicio 5: Facturación de Servicios de Internet (`Ejercicio_5.java`)
* **¿Qué hace?:** Calcula el cobro mensual según el plan contratado (30, 50 o 100 megas), aplicando un 5% de bonificación en los planes de mayor velocidad.
* **Explicación simple:** Es una calculadora de caja que te cobra según el combo que elegiste y te aplica un cupón de descuento automático si elegiste el combo grande.

### 🔹 Ejercicio 6: Cronometraje y Búsqueda del Mínimo (`Ejercicio_6.java`)
* **¿Qué hace?:** Registra autos de carrera y sus marcas cronométricas para determinar cuál completó la pista en el menor tiempo.
* **Explicación simple:** Es el cronómetro de una pista de carreras: busca el número más pequeño de todos, porque el que tarda menos segundos es el que gana.

### 🔹 Ejercicio 7: Procesamiento Estadístico de Censo (`Ejercicio_7.java`)
* **¿Qué hace?:** Utiliza un bucle interactivo centinela (finaliza con DNI 0) para calcular totales por género, porcentaje de población activa (16 a 65 años) y datos de la persona más longeva.
* **Explicación simple:** Como contar personas una por una en una fila sin saber cuántas van a llegar, guardando en un bloc de notas quién fue el abuelo de mayor edad.

---

## 🎬 Proyecto Especial: Simulador de Butacas de Cine GUI (`Ejercicio_Cine.java`)

Aplicación visual con interfaz gráfica (**Java Swing**) que gestiona una sala de 100 asientos dispuesta en una matriz bidimensional ($10 \times 10$).

* **Estructura:** Matriz de objetos `Cliente[][] sala = new Cliente[10][10]`.
* **Funcionalidades:**
    * 🟢 **Verde:** Asiento libre.
    * 🔴 **Rojo:** Asiento reservado (al hacer clic muestra los datos del titular).
    * 🟡 **Amarillo:** Asiento seleccionado para interactuar.
    * **Operaciones:** Alta de reservas, liberación de butacas y reubicación de clientes entre asientos sin duplicar datos.

---

