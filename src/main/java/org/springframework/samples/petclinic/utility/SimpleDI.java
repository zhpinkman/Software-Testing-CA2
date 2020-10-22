package org.springframework.samples.petclinic.utility;

import org.springframework.samples.petclinic.owner.PetRepository;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * this simple class shows the main idea behind a Dependency Injection library
 */
public abstract class SimpleDI {

	public static SimpleDI getDIContainer() throws Exception {
		// todo return the singleton instance of your implementation of dependency injection container
		return new SimpleDI() {

			private HashMap<Class<?>, Object> container = new HashMap<>();

			@Override
			public void provideByInstance(Class<?> typeClass, Object instanceOfType) {
				container.put(typeClass, instanceOfType);
			}

			@Override
			public void provideByAConstructorFunction(Class<?> typeClass, Callable<Object> providerFunction) {
				container.put(typeClass, providerFunction);
			}

			@Override
			public Object getInstanceOf(Class<?> requiredType) throws Exception {
				if (container.containsKey(requiredType)) {
					return container.get(requiredType);
				}
				throw new Exception("class type not found!!");
			}
		};
	}

	public abstract void provideByInstance(Class<?> typeClass, Object instanceOfType);

	public abstract void provideByAConstructorFunction(Class<?> typeClass, Callable<Object> providerFunction);

	public abstract Object getInstanceOf(Class<?> requiredType) throws Exception;
}
