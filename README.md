## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Taller – Principio de Inversión de dependencias, Contenedores Livianos e Inyección de dependencias.]
Parte I. Ejercicio básico.

Para ilustrar el uso del framework Spring, y el ambiente de desarrollo para el uso del mismo a través de Maven (y NetBeans), se hará la configuración de una aplicación de análisis de textos, que hace uso de un verificador gramatical que requiere de un corrector ortográfico. A dicho verificador gramatical se le inyectará, en tiempo de ejecución, el corrector ortográfico que se requiera (por ahora, hay dos disponibles: inglés y español).

1. Abra el los fuentes del proyecto en NetBeans.

2. Revise el archivo de configuración de Spring ya incluido en el proyecto (src/main/resources). El mismo indica que Spring buscará automáticamente los 'Beans' disponibles en el paquete indicado.

3. Haciendo uso de la [configuración de Spring basada en anotaciones](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html) marque con las anotaciones @Autowired y @Service las dependencias que deben inyectarse, y los 'beans' candidatos a ser inyectadas -respectivamente-:

	* GrammarChecker será un bean, que tiene como dependencia algo de tipo 'SpellChecker'.
	![image](https://github.com/user-attachments/assets/75961a13-a9f0-454c-a6a6-aa1ed876b480)

	* EnglishSpellChecker y SpanishSpellChecker son los dos posibles candidatos a ser inyectados. Se debe seleccionar uno, u otro, mas NO ambos (habría conflicto de resolución de dependencias). Por ahora haga que se use EnglishSpellChecker.
	![image](https://github.com/user-attachments/assets/a1b117af-89b2-43b9-8d9d-8d1ffccffecd)

 
4.	Haga un programa de prueba, donde se cree una instancia de GrammarChecker mediante Spring, y se haga uso de la misma:

	```java
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		GrammarChecker gc=ac.getBean(GrammarChecker.class);
		System.out.println(gc.check("la la la "));
	}
	```
	
	![image](https://github.com/user-attachments/assets/c9752345-a67c-46f7-aa2b-ad365cb87b9e)


5.	Modifique la configuración con anotaciones para que el Bean ‘GrammarChecker‘ ahora haga uso del  la clase SpanishSpellChecker (para que a GrammarChecker se le inyecte SpanishSpellChecker en lugar de  EnglishSpellChecker. Verifique el nuevo resultado.

	![image](https://github.com/user-attachments/assets/2c3737b1-15f7-4916-8078-2ebea3f9029a)

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.

	![image](https://github.com/user-attachments/assets/1c330655-bcba-4262-bc1e-0e5134dfa727)

	* Agregar la configuración de Spring.

	![image](https://github.com/user-attachments/assets/beb77c90-9f7c-4f3a-9cdd-08e61640cb2c)

	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.
	
	![image](https://github.com/user-attachments/assets/94a90884-56da-4fc5-924c-dd80034711b6)
	![image](https://github.com/user-attachments/assets/e5d5eec8-aa7c-4cfa-9af9-fcfff2bef828)

2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

	![image](https://github.com/user-attachments/assets/dd61071d-26e3-4126-8d35-d0cdfabe618e)
	![image](https://github.com/user-attachments/assets/e354aecd-3b9e-44cf-bd2a-def4c15a8a3b)
	![image](https://github.com/user-attachments/assets/ddae4459-04d0-442c-a584-7af40bad8df9)

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

	![image](https://github.com/user-attachments/assets/14812b93-bef2-4a70-9190-5c011684c91a)
	
4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

	![image](https://github.com/user-attachments/assets/17dd381d-545f-49f1-b824-835e806a4bed)
	![image](https://github.com/user-attachments/assets/eb3e4cd7-c626-4c96-a48b-9890f78dfb3e)
	![image](https://github.com/user-attachments/assets/696ec583-36d2-4eb7-90ae-d2ae1cd9f7d8)
	![image](https://github.com/user-attachments/assets/80b3625d-0a66-4417-a147-5e7e2ad15da4)

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 

	![image](https://github.com/user-attachments/assets/c1b857d0-0505-4111-8416-cbd0aa204b65)
	![image](https://github.com/user-attachments/assets/9f2cc450-442d-4bbc-b7bb-80d6ca649eac)

	- Cambiando la anotación para aplicar el filtro de Redundancia

	![image](https://github.com/user-attachments/assets/efa4b65d-01ec-4a29-82d4-5caae38bf7ec)

	- Cambiando la anotación para aplicar el filtro de Subsampling

	![image](https://github.com/user-attachments/assets/904ab8ec-e11d-4b24-9b43-4fe84b4a67f5)	
