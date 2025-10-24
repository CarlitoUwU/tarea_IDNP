# Proyecto: Modificación de Cursos en Jetpack Compose

## Integrantes
- **Carlo Valdivia Luna**
- **Taylor Betanzos**

## Funcionalidad Principal y Descripción del Código

Esta aplicación desarrollada en Jetpack Compose permite modificar el nombre de un curso existente dentro de una lista predefinida de cursos de Ingeniería de Sistemas. El usuario puede ingresar el ID del curso y el nuevo nombre, y al presionar el botón "Modificar", la lista se actualiza automáticamente en la interfaz sin necesidad de recargar la pantalla. Además, cuenta con un botón "Ver/Ocultar Lista" que permite mostrar o esconder la lista completa de cursos de forma dinámica.

## Explicación del Funcionamiento del Código

### 1. Estado Reactivo con mutableStateListOf

La lista de cursos se define mediante `val cursos = remember { mutableStateListOf(...) }`.  
Esto crea una lista reactiva u observable, lo que significa que cualquier modificación dentro de ella (como cambiar el nombre de un curso) será detectada por Jetpack Compose, provocando que la interfaz se recomponga automáticamente.  
Gracias a este comportamiento, la lista mostrada mediante el componente LazyColumn siempre estará sincronizada con los datos actuales sin que el usuario tenga que actualizar manualmente.

### 2. Entradas de Texto con OutlinedTextField

Los campos de texto sirven para capturar los valores que el usuario ingresa:

- **ID del curso**: identifica cuál de los elementos se modificará.
- **Nuevo nombre**: texto que reemplazará el nombre actual del curso.

Cada campo utiliza una variable de estado (`remember { mutableStateOf("") }`), lo que permite que Compose recuerde los valores escritos incluso si la pantalla se recompone. Esto hace que la experiencia del usuario sea fluida, sin perder los datos al interactuar con otros elementos.

### 3. Botón "Modificar"

El botón principal del programa ejecuta una lógica que:

- Verifica si el ID ingresado corresponde a un curso existente.
- Si lo encuentra, actualiza el nombre del curso mediante una copia del objeto (`copy(nombre = nuevoNombre.text)`).
- Reemplaza el elemento dentro de la lista, lo cual actualiza inmediatamente la interfaz gracias al sistema de estados de Compose.
- Finalmente, muestra un mensaje Toast confirmando la modificación o indicando si el ID no existe.

### 4. Botón "Ver/Ocultar Lista"

Este botón controla un valor booleano (`mostrarLista`) que determina si la lista debe visualizarse o no.  
Cada vez que el usuario lo presiona, el valor cambia entre `true` y `false`, lo que hace que la interfaz se actualice instantáneamente mostrando u ocultando la lista sin afectar los datos almacenados.

### 5. Visualización con LazyColumn

La lista de cursos se muestra mediante `LazyColumn { items(cursos) { curso -> ... } }`.  
Este componente es ideal para mostrar grandes cantidades de elementos, ya que solo renderiza los que están visibles en pantalla, mejorando el rendimiento y evitando recargas innecesarias.  
Cuando se modifica un curso, solo ese elemento se recompone, lo que hace que el sistema sea rápido y eficiente incluso con listas extensas.

### 6. Mensajes al Usuario con Toast

El uso de Toast permite brindar retroalimentación inmediata al usuario. Por ejemplo:

- Si el ID no existe, se muestra un mensaje de error.
- Si el cambio se realizó correctamente, aparece una confirmación.

Esto mejora la usabilidad y facilita la interacción con la aplicación.

## Resumen General

Este proyecto demuestra el poder del estado reactivo en Jetpack Compose. Gracias a `mutableStateListOf`, la interfaz se mantiene siempre actualizada con los datos sin necesidad de refrescar manualmente. Además, el uso de `OutlinedTextField`, `Button` y `LazyColumn` permite crear una experiencia moderna y eficiente para gestionar información. El flujo del sistema es simple pero potente: el usuario ingresa datos, modifica información y ve los resultados en tiempo real.
