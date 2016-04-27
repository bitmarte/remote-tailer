package org.bitmarte.architecture.utils.remotetailer;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.remotetailer.beans.Config;
import org.bitmarte.architecture.utils.remotetailer.beans.Tailer;
import org.bitmarte.architecture.utils.remotetailer.services.config.ConfigService;
import org.bitmarte.architecture.utils.remotetailer.services.executor.TailerRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		ConfigService.loadConfig(args[0]);
		Config config = ConfigService.getConfig();

		try {
			FileUtils.deleteQuietly(
					new File("C:/Users/A110282/Desktop/tmp/elastic/elastic-logstash-data/remote-tailer/"));
		} catch (Exception e) {
			LOG.warn("Output directory not found!", e);
		}

		for (Tailer tailer : config.getTailers()) {
			Thread t = new Thread(new TailerRunnable(tailer));
			t.start();
		}
	}

}
