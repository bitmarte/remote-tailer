package org.bitmarte.architecture.utils.remotetailer.services.validator;

/**
 * @author bitmarte
 *
 */
public interface I_Validator {

	public void validate() throws Exception;

	public void setDefaultValue() throws Exception;

}
