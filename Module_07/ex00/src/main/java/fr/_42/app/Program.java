/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Program.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/06 18:25:45 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/09 04:31:50 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.app;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {
	private static final String PACKAGE_PATH = "fr/_42/models";
	private static final String PACKAGE_NAME = "fr._42.models";
	private static Scanner scanner = new Scanner(System.in);

	private static Set<Class<?>> getClassesFromPackage() throws ClassNotFoundException {
		Set<Class<?>> classSet = new HashSet<>();
		String path = "target/classes/" + PACKAGE_PATH;

		File folder = new File(path);
		if (!folder.exists())
			throw new RuntimeException("Path does not exist: " + path);

		File[] files = folder.listFiles((dir, name) -> name.endsWith(".class"));
		if (files == null)
			throw new RuntimeException("Failed to list class files in: " + path);
		System.out.println("Classes:");
		for (File file : files) {
			String className = file.getName().replace(".class", "");
			System.out.println(className);
			classSet.add(Class.forName(PACKAGE_NAME + "." + className));
		}
		System.out.println("-----------------------------");
		return classSet;
	}
	
	private static Class<?> printClassInfo(Set<Class<?>> classes) throws ClassNotFoundException {
		Class<?> className;
	
		System.out.println("Enter class name:");
		if (!scanner.hasNext() || !scanner.hasNextLine())
			throw new IllegalArgumentException("Invalid Input?");
		String input = scanner.nextLine();
		className = classes.stream()
				.filter(clazz -> clazz.getSimpleName().equals(input))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Class not found: " + input));
	
		System.out.println("---------------------");
	
		// Print Fields:
		System.out.println("fields:");
		Field[] fields = className.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
		}
	
		// Print Methods:
		System.out.println("methods:");
		var methods = className.getDeclaredMethods();
		for (var method : methods) {
			StringBuilder parameters = new StringBuilder();
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				parameters.append(parameterTypes[i].getSimpleName());
				if (i < parameterTypes.length - 1) {
					parameters.append(", ");
				}
			}
			System.out.println("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(" + parameters + ")");
		}
	
		System.out.println("---------------------");
		return className;
	}

	private static Object createObj(Class<?> className){
		System.out.println("Let's create an object.");
		try {
			Object obj = className.getDeclaredConstructor().newInstance();
			Field[] fields = className.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field.getName() + ":");
				if (!scanner.hasNext() || !scanner.hasNextLine())
					throw new IllegalAccessException("Invalid Arg!");
				String input = scanner.nextLine();
				field.setAccessible(true);
				getType(field, obj, input);
			}
			System.out.println("Object created: " + obj.toString());
			System.out.println("---------------------");
			return obj;
		} catch (Exception e) {
			System.out.println("Error while creating object: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private static void getType(Field field, Object obj, String input) throws IllegalAccessException {
		if (field.getType() == String.class)
			field.set(obj, input);
		else if (field.getType() == Integer.class || field.getType() == int.class)
			field.set(obj, Integer.parseInt(input));
		else if (field.getType() == Double.class || field.getType() == double.class)
			field.set(obj, Double.parseDouble(input));
		else if (field.getType() == Boolean.class || field.getType() == boolean.class)
			field.set(obj, Boolean.parseBoolean(input));
		else if (field.getType() == Long.class || field.getType() == long.class)
			field.set(obj, Long.parseLong(input));
		else
			System.out.println("Unsupported field type: " + field.getType().getSimpleName());
	}

	private static void updateFields(Class<?> className, Object obj) throws Exception {
		System.out.println("---------------------");
		System.out.println("Enter name of the field for changing:");
		if (!scanner.hasNext() || !scanner.hasNextLine())
			throw new IllegalArgumentException("Invalid Arg!");
		String fieldName = scanner.nextLine();
		Field field = className.getDeclaredField(fieldName);
		if (field == null)
			throw new IllegalArgumentException("Field not found: " + fieldName);
		System.out.println("Enter " + field.getType().getSimpleName() + " value:");
		String input = scanner.nextLine();
		field.setAccessible(true);
		getType(field, obj, input);
		System.out.println("Object updated: " + obj.toString());
		System.out.println("---------------------");
	}

	private static void callMethod(Class<?> className, Object obj) throws Exception {
		System.out.println("---------------------");
		System.out.println("Enter name of the method for call:");
		if (!scanner.hasNext() || !scanner.hasNextLine())
			throw new IllegalArgumentException("Invalid Arg!");
		String methodName = scanner.nextLine();
		Method[] methods = className.getDeclaredMethods();
		Method method = java.util.Arrays.stream(methods)
				.filter(m -> m.getName().equals(methodName.split("\\(")[0]))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Method not found: " + methodName));

		Class<?>[] parameterTypes = method.getParameterTypes();
		Object[] args = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			System.out.println("Enter " + parameterTypes[i].getSimpleName() + " value:");
			if (!scanner.hasNext() || !scanner.hasNextLine())
				throw new IllegalArgumentException("Invalid Arg!");
			String input = scanner.nextLine();
	
			if (parameterTypes[i] == String.class)
				args[i] = input;
			else if (parameterTypes[i] == Integer.class || parameterTypes[i] == int.class)
				args[i] = Integer.parseInt(input);
			else if (parameterTypes[i] == Double.class || parameterTypes[i] == double.class)
				args[i] = Double.parseDouble(input);
			else if (parameterTypes[i] == Boolean.class || parameterTypes[i] == boolean.class)
				args[i] = Boolean.parseBoolean(input);
			else if (parameterTypes[i] == Long.class || parameterTypes[i] == long.class)
				args[i] = Long.parseLong(input);
			else
				throw new IllegalArgumentException("Unsupported parameter type: " + parameterTypes[i].getSimpleName());
		}

		Object result = method.invoke(obj, args);
		System.out.println("Method returned:");
		System.out.println(result);
		System.out.println("---------------------");
	}
	public static void main(String[] args) {
		try {
			Set<Class<?>> classes = getClassesFromPackage();
			Class<?> className = printClassInfo(classes);
			Object obj = createObj(className);
			updateFields(className, obj);
			callMethod(className, obj);
			
		} catch (Exception e) {
			scanner.close();
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	

}
