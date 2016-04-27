package org.bitmarte.architecture.utils.remotetailer.services.validator.exceptions;

/**
 * @author bitmarte
 *
 */
@SuppressWarnings("serial")
public class ValidatorException extends Exception {

	public ValidatorException(String errorMessage) {
		super(errorMessage);
	}

	public ValidatorException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}
