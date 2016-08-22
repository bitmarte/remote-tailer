package org.bitmarte.architecture.utils.remotetailer.services.validator.impl;

import org.bitmarte.architecture.utils.remotetailer.beans.Config;
import org.bitmarte.architecture.utils.remotetailer.beans.Tailer;
import org.bitmarte.architecture.utils.remotetailer.beans.outputs.RollingFileAppenderOutput;
import org.bitmarte.architecture.utils.remotetailer.services.validator.A_Validator;
import org.bitmarte.architecture.utils.remotetailer.services.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class ConfigValidator extends A_Validator {

	public ConfigValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	public void validate() throws Exception {
		Config toValidate = (Config) this.inValidation;

		// tailer required
		if (toValidate.getTailers() == null) {
			throw new ValidatorException("No tailers configurated!");
		}

		for (Tailer tailer : toValidate.getTailers()) {
			// input required
			if (tailer.getInput() == null) {
				throw new ValidatorException("No input configurated!");
			} else {
				if (tailer.getInput().getHost() == null) {
					throw new ValidatorException("No host configurated for input!");
				}
				if (tailer.getInput().getPassword() == null) {
					throw new ValidatorException("No password configurated for input!");
				}
				if (tailer.getInput().getFilePath() == null) {
					throw new ValidatorException("No filePath configurated for input!");
				}
			}

			// output required
			if (tailer.getOutput() == null || tailer.getOutput().isEmpty()) {
				throw new ValidatorException("No output configurated!");
			} else {
				if (tailer.getOutput().size() > 1) {
					throw new ValidatorException("More than one output configured!");
				}
			}
		}
	}

	public void setDefaultValue() throws Exception {
		Config toValidate = (Config) this.inValidation;

		for (Tailer tailer : toValidate.getTailers()) {
			if (tailer.getInput().getPort() == 0) {
				LOG.warn("No port configured for input, set default to 22");
				tailer.getInput().setPort(22);
			}
			// TODO: more than output could be configured
			if (tailer.getOutput().get(0) instanceof RollingFileAppenderOutput) {
				if (((RollingFileAppenderOutput) tailer.getOutput().get(0)).getLayoutPattern() == null) {
					((RollingFileAppenderOutput) tailer.getOutput().get(0)).setLayoutPattern("%msg");
				}
			}
		}
	}

}
