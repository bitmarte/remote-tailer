package org.bitmarte.architecture.utils.remotetailer.services.output.impl;

import org.bitmarte.architecture.utils.remotetailer.beans.outputs.StdOutAppenderOutput;
import org.bitmarte.architecture.utils.remotetailer.services.executor.TailerRunnable;
import org.bitmarte.architecture.utils.remotetailer.services.output.A_LoggerBuilder;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

/**
 * @author bitmarte
 *
 */
public class StdOutAppenderBuilder extends A_LoggerBuilder {

	private StdOutAppenderOutput output = null;

	public StdOutAppenderBuilder(StdOutAppenderOutput output) {
		this.output = output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.remotetailer.services.output.
	 * I_LoggerOutput#createLogger(org.bitmarte.architecture.utils.remotetailer.
	 * beans.outputs.A_Output)
	 */
	public Logger createLogger() throws Exception {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		PatternLayoutEncoder ple = new PatternLayoutEncoder();
		ple.setPattern(this.output.getLayoutPattern());
		ple.setContext(lc);
		ple.start();

		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<ILoggingEvent>();
		consoleAppender.setName(TailerRunnable.class.getName());
		consoleAppender.setEncoder(ple);
		consoleAppender.setContext(lc);
		consoleAppender.start();

		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(Thread.currentThread().getName());
		logger.addAppender(consoleAppender);
		logger.setLevel(Level.INFO);
		logger.setAdditive(true); /* set to true if root should log too */

		return logger;
	}

}
