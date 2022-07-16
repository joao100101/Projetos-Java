package me.joao.bookstore.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class StandardError {
	private Long timestamp;
	private Integer status;
	private String error;

	
	
	
}
