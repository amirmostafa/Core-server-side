package com.core.controllers;

import com.core.models.BasicModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Base class for all controllers<br/>
 * 
 * @author Amostafa
 *
 */
public class AbstractController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handles status message of response based on {@link BasicModel#getReplyCode()}
	 * 
	 */
	public <T extends BasicModel> ResponseEntity<T> handle(T data) {
		return ResponseEntity.status(data.getReplyCode()).body(data);
	}

	/**
	 * {@link HttpStatus} for general error
	 */
	public <T extends BasicModel> ResponseEntity<T> error(T data) {
		data.setReplyCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
	}

}
