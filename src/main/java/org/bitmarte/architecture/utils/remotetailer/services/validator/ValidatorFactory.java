package org.bitmarte.architecture.utils.remotetailer.services.validator;

import org.bitmarte.architecture.utils.remotetailer.beans.Config;
import org.bitmarte.architecture.utils.remotetailer.services.validator.exceptions.ValidatorException;
import org.bitmarte.architecture.utils.remotetailer.services.validator.impl.ConfigValidator;

/**
 * @author bitmarte
 *
 */
public class ValidatorFactory {

	public static I_Validator getInstance(Object inValidation) throws Exception {
		if (inValidation instanceof Config) {
			return new ConfigValidator((Config) inValidation);
		}

		throw new ValidatorException("Unknown object type: " + inValidation.getClass().getName());
	}
}
