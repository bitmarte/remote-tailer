package org.bitmarte.architecture.utils.remotetailer.services.config;

import java.io.File;

import org.bitmarte.architecture.utils.remotetailer.beans.Config;
import org.bitmarte.architecture.utils.remotetailer.beans.Input;
import org.bitmarte.architecture.utils.remotetailer.beans.Tailer;
import org.bitmarte.architecture.utils.remotetailer.beans.outputs.RollingFileAppenderOutput;
import org.bitmarte.architecture.utils.remotetailer.services.validator.I_Validator;
import org.bitmarte.architecture.utils.remotetailer.services.validator.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @author bitmarte
 *
 */
public class ConfigService {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigService.class);

	private static Config configuration = null;

	public static void loadConfig(String filePath) throws Exception {
		try {
			if (configuration == null) {
				XStream xStream = new XStream();

				xStream.processAnnotations(Config.class);
				xStream.processAnnotations(Input.class);
				xStream.processAnnotations(RollingFileAppenderOutput.class);
				xStream.processAnnotations(Tailer.class);

				configuration = (Config) xStream.fromXML(new File(filePath));

				I_Validator validator = ValidatorFactory.getInstance(configuration);
				validator.validate();
				validator.setDefaultValue();
			} else {
				LOG.warn("config file is already loaded!");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static Config getConfig() throws Exception {
		return configuration;
	}
}
