#!/bin/sh

#comando para compilar el programa que acopla el agente dinámico a la JVM.
#javac <Ruta local de la carpeta Agente-Dinámico>
javac src/agent/java/AttachHelper.java
#comando para ejecutar el programa que acopla el agente dinámico a la JVM.
#java <Ruta local de la carpeta Agente-Dinámico> <Identificador del proceso (PID) de la máquina virtual de Java (JVM de la aplicación a analizar>
java src/agent/java/AttachHelper.java "JVM_PID"
