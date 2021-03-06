package com.borayu.exception;

public class UnknownItemException extends RuntimeException {

	private final Class<?> clazz;
	private final Object id;
	
	public UnknownItemException(Class<?> clazz, Object id) {
		super(clazz.getName() + " with id=" + id + " doesn't exist.");
		
		this.clazz = clazz;
		this.id = id;
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public Object getId() {
		return id;
	}
	
}
