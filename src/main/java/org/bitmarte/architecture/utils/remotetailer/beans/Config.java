package org.bitmarte.architecture.utils.remotetailer.beans;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("config")
public class Config {

	@XStreamImplicit
	private List<Tailer> tailers;

	public List<Tailer> getTailers() {
		return tailers;
	}

	public void setTailers(List<Tailer> tailers) {
		this.tailers = tailers;
	}

}
