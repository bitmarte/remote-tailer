package org.bitmarte.architecture.utils.remotetailer.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("output")
public class Output {

	@XStreamAlias("type")
	@XStreamAsAttribute
	private String type;

	@XStreamAlias("filePath")
	private String filePath;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
