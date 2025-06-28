# Sistema de Gestión de Pedidos de Pizzería

Esta aplicación permite gestionar pedidos de pizza utilizando los patrones de diseño **State** , **Observer** y **Decorator**. Los usuarios pueden armar un pedido, cambiar su estado y recibir notificaciones automáticas cuando el estado de un pedido cambia.

---

## Requisitos

- **Java 8 o superior**
- **MySQL** (o el gestor de base de datos que utilice tu proyecto)
- IDE recomendado: **NetBeans**, **IntelliJ IDEA** o **Eclipse**
- Librerías JDBC para conexión a la base de datos

---

## Instalación

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/tu-repo-pizzeria.git
   cd tu-repo-pizzeria
   ```

2. **Configura la base de datos:**
   - Crea una base de datos llamada `pizzeria` (o el nombre que utilices en tu archivo de conexión).
   - Ejecuta los scripts SQL en la carpeta `/sql` para crear las tablas necesarias (`clientes`, `pedidos`, etc).
   - Asegúrate de que los datos de conexión (usuario, contraseña, URL) en la clase `conexion.java` sean correctos.

3. **Importa el proyecto en tu IDE:**
   - Abre tu IDE preferido.
   - Importa el proyecto como proyecto Java o Maven según corresponda.

4. **Agrega el conector JDBC a tu proyecto:**
   - Descarga el [conector MySQL JDBC](https://dev.mysql.com/downloads/connector/j/).
   - Agrega el `.jar` a las librerías del proyecto.

---

## Ejecución

1. **Compila el proyecto** desde tu IDE o usando:
   ```bash
   javac -d bin src/**/*.java
   ```

2. **Ejecuta la clase principal** (usualmente la pantalla principal o `Main.java`):
   ```bash
   java -cp bin Main
   ```

3. **Uso básico de la aplicación:**
   - Agregar un Pedido de pizza
   - asignar nombre del cliente y pizza decorada
   - Ingresa el ID de cliente y busca su pedido.
   - Visualiza los detalles del pedido.
   - Cambia el estado del pedido desde el menú correspondiente.
   - Al cambiar de estado, se notificará automáticamente a los usuarios observadores (puede mostrarse un mensaje en consola o ventana emergente).

---

## Estructura de Carpetas

```
src/
  ├─ controller/
  │    └─ PedidosController.java
  │    └─ ClienteController.java
  ├─ observer/
  │    └─ IUsuario.java
  │    └─ Usuario.java
  ├─ state/
  │    └─ IEstadoPizza.java
  │    └─ Recibido.java
  │    └─ EnPreparacion.java
  │    └─ Entregado.java
  ├─ decorator/
  │    └─ IPizza.java
  │    └─ extraBarbeque.java
  │    └─ extraQueso.java
  │    └─ ingredienteBase.java
  │    └─ pizzaNapolitana.java
  │    └─ pizzaPepperoni.java
  ├─ ConexionBs/
  │    └─ conexion.java
  └─ Principal/
       └─ Principal.java
```

---

## Notas
- El patrón **Decorator** permite agregarle extras a la pizza final con la descripcion y precio actualizado dependiendo los agregados que se elijan
- El patrón **Observer** permite que la UI y/o usuarios sean notificados automáticamente cuando el estado del pedido cambia.
- El patrón **State** controla la lógica de transición entre estados válidos del pedido.
- Para pruebas, puedes modificar los mensajes de notificación en la clase `Usuario` para mostrar ventanas emergentes (`JOptionPane`) o imprimir en consola.

---

## Soporte

Si tienes dudas o problemas, abre un issue en este repositorio o contacta al autor.

