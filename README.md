Los alumnos de 3A y en particular aquellos que están matriculados de DSA están muy
concienciados con la situación actual provocada por la pandemia del covid-19 y, han decidido
aportar su grano de arena, desarrollando una aplicación que permita hacer seguimiento de los
casos detectados de covid-19.
Después una lluvia de ideas, se ha propuesto implementar esta aplicación
SE PIDE:
PARTE I: 5 puntos
1.- Especificación de un componente (Covid19Manager.java,<<interfaz>>) que tiene las
siguientes operaciones:

● Crear un nuevo brote. De esta manera, se podrán analizar los casos del brote
existente y los futuros rebrotes.

● Listar los brotes disponibles.

● Añadir un caso sobre un brote. Un caso tiene las siguientes propiedades:
nombre, apellidos, identificador de caso, fecha de nacimiento, fecha del informe, nivel
de riesgo (alto/medio/bajo), género, correo electrónico, teléfono, dirección, clasificación
(sospechoso, confirmado, no es un caso)

● Listado de casos de un determinado brote ordenado por clasificación (confirmado,
sospechoso y no caso) y dentro de la clasificación por orden de fecha del informe

2.- Implementación de una Fachada (patrón de diseño) que implemente el interfaz
definido previamente (Covid19ManagerImpl.java, clase Java).

2.1 Elección de las estructuras de datos. El contenedor de brotes debe
implementarse como un diccionario (<clave, valor>). El contenedor de casos
dentro de un brote debe implementarse utilizando una lista.

2.3 La fachada deberá implementarse como un Singleton .

2.4 Todos los métodos deberán tener una TRAZA (a nivel de INFO) de LOG4J
que muestre el valor de los parámetros al inicio de los métodos y al final.
También debe contemplarse trazas de otros niveles (ERROR o FATAL)

3.- Implementación de un test (JUNIT) sobre el componente (Covid19Manager) con los
siguientes puntos:

- método setUp que inicializa la estructura de datos y crea un juego de pruebas
inicial.

- método tearDown que libera los recursos. Tened en cuenta que al tratarse de
un singleton, debéis ofrecer una operación para inicializar/limpiar las estructuras de
datos

- método de test para crear un nuevo brote

- método de test para añadir un caso sobre un brote

PARTE II: 5 puntos

1.- Definir (servicio, operaciones, rutas, métodos HTTP, peticiones, respuestas, códigos
de respuesta) e implementar un servicio REST que permita realizar las operaciones
especificadas en la primera parte del ejercicio. Todas las operaciones deben retornar
“objetos de transferencia” y evitar ciclos/relaciones

NOTA: El servicio debe utilizar el componente construido en el punto anterior
(Covid19Manager)
