# Tarea 3

Esperando que este informe sea mejor que el anterior (ahora si) se detallará que y como se hizo esta tarea.
Esta vez voy a asumir de frente que tiene cosas raras y hermosas de kotlin
como las reflections de funciones, que en un principio pueden ser dificiles de
entender, pero dejé link a la documentación junto al código para las cosas más complejas.

Al imprimir el árbol este usa posfijo solo para evitar los casos en los que se hace distinto
 (como min y max vs suma y resta)

En esta carpeta hay un archivo main.kt que tendrá punteros a los
ejercicios realizados, que hasta ahora es uno, y genera un grafico bonito.

Este corresponde al ejercicio sin limite de reposición con
una poblacion de 600, 15 de profundidad, y ratio de mutacion de 0.2 

<center>
 <img src="https://github.com/BastyZ/NeuralPerceptron/blob/master/src/assignment3/images/noRepLimit.jpg" alt="Ejercicio 1">
</center>
 
Además hice el analisis, pero no tengo la mas minima idea de como hacer un heatmap.
 De todas formas la matriz existe asique podrian hacerlo si saben  (?)

Así que deje un mensaje con la mejor combinación encontrada según el tiempo
que se demoraba en entontrar la respuesta 10 veces o llegar a las 2000 generaciones.
Imprime mucho pero es básicamente porque se demora bastante (su media hora
(¿alguien dijo logaritmos..?)), asíque en el tiempo que 
tardaba le hice una clase de UI bonita.
```
La mejor combinación es (con profundidad 15): 
	 población: 50 
	 ratio de mutacion: 0.4 
	 con un tiempo de 219
```