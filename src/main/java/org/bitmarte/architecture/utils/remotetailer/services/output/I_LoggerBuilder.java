package org.bitmarte.architecture.utils.remotetailer.services.output;

import ch.qos.logback.classic.Logger;

/**
 * @author bitmarte
 *
 */
public interface I_LoggerBuilder {

	public Logger createLogger() throws Exception;
}
