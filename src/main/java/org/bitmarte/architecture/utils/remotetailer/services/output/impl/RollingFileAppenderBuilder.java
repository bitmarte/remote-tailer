package org.bitmarte.architecture.utils.remotetailer.services.output.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.remotetailer.beans.outputs.RollingFileAppenderOutput;
import org.bitmarte.architecture.utils.remotetailer.services.executor.TailerRunnable;
import org.bitmarte.architecture.utils.remotetailer.services.output.A_LoggerBuilder;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

/**
 * @author bitmarte
 *
 */
public class RollingFileAppenderBuilder extends A_LoggerBuilder {

	private RollingFileAppenderOutput output = null;

	public RollingFileAppenderBuilder(RollingFileAppenderOutput output) {
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
		if (this.output.isDeleteOnStart()) {
			try {
				LOG.info("delete on start rootPath: " + this.output.getFileRootPath());
				FileUtils.deleteQuietly(new File(this.output.getFileRootPath()));
			} catch (Exception e) {
				LOG.warn("Output directory not found!", e);
			}
		}

		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		PatternLayoutEncoder ple = new PatternLayoutEncoder();
		ple.setPattern(this.output.getLayoutPattern());
		ple.setContext(lc);
		ple.start();

		RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<ILoggingEvent>();
		rollingFileAppender.setName(TailerRunnable.class.getName());
		rollingFileAppender.setAppend(true);
		rollingFileAppender.setEncoder(ple);
		rollingFileAppender.setContext(lc);
		rollingFileAppender.setPrudent(true);

		TimeBasedRollingPolicy<?> timeBasedRollingPolicy = new TimeBasedRollingPolicy<Object>();
		timeBasedRollingPolicy
				.setFileNamePattern(this.output.getFileRootPath() + "/" + this.output.getFileNamePattern());
		timeBasedRollingPolicy.setMaxHistory(12);
		timeBasedRollingPolicy.setParent(rollingFileAppender);
		timeBasedRollingPolicy.setContext(lc);

		rollingFileAppender.setRollingPolicy(timeBasedRollingPolicy);
		timeBasedRollingPolicy.start();
		rollingFileAppender.start();

		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(Thread.currentThread().getName());
		logger.addAppender(rollingFileAppender);
		logger.setLevel(Level.INFO);
		logger.setAdditive(false); /* set to true if root should log too */

		return logger;
	}

}
