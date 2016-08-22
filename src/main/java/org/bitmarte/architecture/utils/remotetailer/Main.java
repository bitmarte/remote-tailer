package org.bitmarte.architecture.utils.remotetailer;

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

		for (Tailer tailer : config.getTailers()) {
			Thread t = new Thread(new TailerRunnable(tailer));
			t.start();
		}
	}

}
