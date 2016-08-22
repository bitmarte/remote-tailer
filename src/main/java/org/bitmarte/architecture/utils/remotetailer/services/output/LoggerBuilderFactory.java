package org.bitmarte.architecture.utils.remotetailer.services.output;

import org.bitmarte.architecture.utils.remotetailer.beans.outputs.A_Output;
import org.bitmarte.architecture.utils.remotetailer.beans.outputs.RollingFileAppenderOutput;
import org.bitmarte.architecture.utils.remotetailer.services.output.impl.RollingFileAppenderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class LoggerBuilderFactory {

	private static final Logger LOG = LoggerFactory.getLogger(LoggerBuilderFactory.class);

	public static I_LoggerBuilder getInstance(A_Output output) throws Exception {
		I_LoggerBuilder i_LoggerBuilder = null;

		LOG.debug("instantiate LoggerBuilder for " + output.getClass().getSimpleName());

		if (output instanceof RollingFileAppenderOutput) {
			i_LoggerBuilder = new RollingFileAppenderBuilder((RollingFileAppenderOutput) output);
		}

		return i_LoggerBuilder;
	}
}
