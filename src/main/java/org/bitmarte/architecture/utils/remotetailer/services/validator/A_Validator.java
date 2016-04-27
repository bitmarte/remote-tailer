package org.bitmarte.architecture.utils.remotetailer.services.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public abstract class A_Validator implements I_Validator {

	protected Object inValidation;

	protected static final Logger LOG = LoggerFactory.getLogger(A_Validator.class);

	public A_Validator(Object inValidation) throws Exception {
		this.inValidation = inValidation;
	}

}
