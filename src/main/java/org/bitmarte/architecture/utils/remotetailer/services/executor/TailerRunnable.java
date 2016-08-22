package org.bitmarte.architecture.utils.remotetailer.services.executor;

import java.io.InputStream;
import java.util.Properties;

import org.bitmarte.architecture.utils.remotetailer.beans.Tailer;
import org.bitmarte.architecture.utils.remotetailer.services.output.LoggerBuilderFactory;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import ch.qos.logback.classic.Logger;

/**
 * @author bitmarte
 *
 */
public class TailerRunnable implements Runnable {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(TailerRunnable.class);

	private Tailer tailer = null;

	public TailerRunnable(Tailer tailer) {
		this.tailer = tailer;
	}

	public void run() {
		try {
			Logger log = this.createLogger();

			JSch jSch = new JSch();

			Session session = jSch.getSession(this.tailer.getInput().getUsername(), this.tailer.getInput().getHost(),
					this.tailer.getInput().getPort());
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			session.setPassword(this.tailer.getInput().getPassword());

			session.connect();

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand("tail -f " + this.tailer.getInput().getFilePath());

			channel.setInputStream(null);

			((ChannelExec) channel).setErrStream(System.out);

			InputStream in = channel.getInputStream();

			channel.connect();
			byte[] tmp = new byte[2048];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					log.info(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					LOG.warn("[" + Thread.currentThread().getName() + "] exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					LOG.error("[" + Thread.currentThread().getName() + "] Error on thread sleep", e);
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			LOG.error("[" + Thread.currentThread().getName() + "] Generic error", e);
		}
	}

	private Logger createLogger() throws Exception {
		return LoggerBuilderFactory.getInstance(this.tailer.getOutput().get(0)).createLogger();
	}

}
