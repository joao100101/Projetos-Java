package me.joao.bookstore.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

	private List<FieldMessage> messages = new ArrayList<>();

	public void addErrors(String fieldName, String message) {
		this.messages.add(new FieldMessage(fieldName, message));
	}

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}
	
	
}
