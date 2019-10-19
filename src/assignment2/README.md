# Tarea 2

## Agregar XChart para correr la tarea
Para hacer el build de la tarea se debe agregar XChar a las librerías externas, esto se puede 
realizar en File -> Project Structure (ctrl + alt + mayús + s),
donde haciendo click en el icono `+` puede agregar el `.jar`, puede descargar
Xcart desde https://knowm.org/open-source/XChart/.

Además se debe tener google-java-format.xml el cual viene incluido en el proyecto
sin el cual XChar no corre

## Correr el proyecto
Para esto debe ir a la carpeta Assignment1, y correr la función
`main()` en `BitsExercise.kt`, `PhraseExercise.kt` y `unboundBag.kt` si usa intlliJ, dado que Kotlin viene incluido,
para instrucciones en otros IDE o para linea de comandos revisar https://kotlinlang.org

# Análisis Sobre la tarea

Curiosamente el algoritmo en los ejercicios demora más que uno parecido hecho
por Daniel Aguirre en python un año anterior, tomando approx un 20% más generaciones.

Exceptuando esto funciona bastante bien, encontrando soluciones en tiempos razonables y
que dependen principalmente del largo de la solución (en el caso de los ejercicios,
 en caracteres)
 
 Para el caso de la mochila el algoritmo es particularmente rápido,
 supongo que esto es dada la poca cantidad de casos, teniendose que en poblaciones
 del orden de 100 casi siempre la respuesta es encontrada en la primera generación
