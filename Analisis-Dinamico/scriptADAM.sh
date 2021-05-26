#!/bin/sh

#comando para la Ejecuccion del script de  generacion de carga de la aplicacion a analizar
jmeter --nongui --testfile /Users/williambaquero/Documents/GitHub/A.D.A.M/Usuario-Simulado/Script-2/Script2.jmx
#Ejecuccion del Analisis Dinamico y de la aplicacion a analizar
#java -javaagent:<Ruta archivo jar del Analisis Dinamico>=<Nombre paquete raiz de la aplicacion a analizar> -jar <ruta archivo jar de la aplicacion a analizar>
#Comando para ejecutar el agente con petClinic
java -javaagent:/Users/williambaquero/Documents/GitHub/A.D.A.M/Analisis-Dinamico/Agente-Estatico/target/analisis-dinamico-1.0-SNAPSHOT.jar=org.springframework.samples -jar /Users/williambaquero/Documents/GitHub/spring-petclinic/target/spring-petclinic-2.1.0.BUILD-SNAPSHOT.jar

#comando para la Ejecuccion del script de  generacion de carga de la aplicacion a analizar
#jmeter -n -t /Users/williambaquero/Downloads/A.D.A.M-ADAM-RE-V1/Usuario Simulado/Script-2/Script2.jmx -l
