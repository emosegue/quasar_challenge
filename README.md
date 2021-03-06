# Operaci贸n Fuego de Quasar

_Han Solo ha sido recientemente nombrado General de la Alianza
Rebelde y busca dar un gran golpe contra el Imperio Gal谩ctico para
reavivar la llama de la resistencia.
El servicio de inteligencia rebelde ha detectado un llamado de auxilio de
una nave portacarga imperial a la deriva en un campo de asteroides. El
manifiesto de la nave es ultra clasificado, pero se rumorea que
transporta raciones y armamento para una legi贸n entera_

![](documents/img.png)


## Comenzando 馃殌

_Estas instrucciones te permitir谩n obtener una copia del proyecto en funcionamiento en tu m谩quina local para prop贸sitos de desarrollo y pruebas._
f

### Pre-requisitos 馃搵

```
JDK 11 o superior
Spring Framework
Maven o utilizar el wrapper embebido en el proyecto
IntelliJ 2021.3 o superior
```

### Instalaci贸n 馃敡

El siguiente instructivo se presenta para un entorno sobre **Windows 10**, aunque en caso de tener algun sistema operativo basado en unix, deber谩s simplemente reemplazar 
```.\mvwn.cmd``` por ```.\mvwn```

_En caso de que se quiera lanzar la aplicaci贸n desde IntelliJ es necesario seguir los siguientes pasos:_

1. _Debemos descargar las dependencias, para ello es posible hacer click sobre el archivo pom.xml, darle click a **Maven** y luego a **Reload Project**, de esta forma se descargar谩n autom谩ticamente todas las dependencias definidas en el pom.xml_

2. _Para levantar el servicio, basta con tocar los comandos Ctrl+F9 para Buildear la aplicaci贸n, y Shift+f10 para lanzarla._

_En caso de que se desee lanzar la aplicaci贸n por consola, es necesario utilizar el wrapper de maven mediante mvwn.cmd, posicionarse sobre el raiz del proyecto y lanzar el comando_

_Para esto, ejecutar el comando:_

```
.\mvwn.cmd spring-boot:run
```

_En este punto es posible acceder al endpoint http://localhost:5000/test para verificar si el servicio est谩 funcionando correctamente._
![](documents/img_1.png)

## Ejecucion

### Nivel 1锔?

Para la ejecuci贸n de las pruebas asociadas a los m茅todos del Nivel 1, 
se sugiere ver la estructura de metodos definidos en el directorio de test.
Para realizar la ejecuci贸n, ver la secci贸n de pruebas de este documento.

### Nivel 2 y 3

Tanto la ejecuci贸n del nivel 2 y 3 pueden realizarse de forma local o de forma remota accediendo a las siguientes URL

[Entorno Local](http://localhost:5000/intelligenceservice/v1/test)

[Entorno Remoto](http://intelligenceservice-env-3.eba-igkzwmap.us-east-2.elasticbeanstalk.com/intelligenceservice/v1/test)


## Ejecutando las pruebas 鈿欙笍

_Para ejecutar los test generados es necesario ubicarse sobre el directorio raiz y lanzar el comando:_

```console
.\mvwn.cmd test
```

## Construido con 馃洜锔?

* [Spring Boot](https://spring.io/) - Framework para web apps en Java
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [AWS Elastic Beanstalk](https://aws.amazon.com/es/elasticbeanstalk/) - Servicio Cloud para aplicaciones


## Wiki 馃摉

Es posible encontrar toda la documentaci贸n asociada al proceso de implementaci贸n, desiciones de dise帽o, ejemplos de problemas encontrados en la [Wiki](https://github.com/emosegue/quasar_challenge/wiki)

## Autores 鉁掞笍

* **Emanuel Mosegue** - *Trabajo Inicial* - [emosegue](https://github.com/emosegue) (emanuelmosegue@gmail.com)

## Licencia 馃搫

Este proyecto est谩 realizado bajo fines de evaluaci贸n como presentaci贸n a un challenge backend para MercadoLibre - Marzo 2022

---
