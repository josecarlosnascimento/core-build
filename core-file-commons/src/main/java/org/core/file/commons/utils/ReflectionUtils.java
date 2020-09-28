package org.core.file.commons.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class ReflectionUtils {

	public static ArrayList<Class<?>> getClassForPackage(Package pack){
		
		ArrayList<Class<?>> listClass = new ArrayList<Class<?>>();
		String packageName = pack.getName();
		String realPath = packageName.replace(".", "/");
		
		URL resource = ClassLoader.getSystemClassLoader().getResource(realPath);
		
		if(resource == null) {
			throw new RuntimeException("O recurso "+realPath+" não foi encontrado");
		}
		
		resource.getPath();
		
		processDirectory(new File(resource.getPath()), packageName, listClass);
		
		return listClass;
		
	}

	private static void processDirectory(File directory, String packageName, ArrayList<Class<?>> listClass) {

		String[] files = directory.list();
		
		for (String file : files) {
			
			String fileName = file;
			String className = null;
			
			if(fileName.endsWith(".class")) {
				className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
			}
			
			if(className != null) {
				listClass.add(loadClass(className));
			}
			
			File subDir = new File(directory, fileName);
			
			if(subDir.isDirectory()) {
				processDirectory(directory, packageName, listClass);
			}
			
		}
		
	}

	private static Class<?> loadClass(String className) {

		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Não foi possível carregar a classe "+className);
		}
		
	}
	
	
}
