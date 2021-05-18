package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jDemo {
	
	private static Logger logger = LogManager.getLogger(Log4jDemo.class);

	public static void main(String[] args) {
		
		System.out.println("Inicia el programa...");
		
		logger.trace("Este es un mensaje trace");
		logger.info("Este es un mensaje de información");
		logger.error("Este es un mensaje de error");
		logger.warn("Este es un mensaje de advertencia");
		logger.fatal("Este es un mensaje fatal");
		
		System.out.println("Termina el programa.");
	}

}
