package org.sofka.demo.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * [
 *  ErrorMessage
 *  atributos los mensajes error que se usan en  ControllerExceptionHandler
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Data
@AllArgsConstructor
public class ErrorMessage {
	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;
}
