package org.bitmarte.architecture.utils.remotetailer.beans.outputs;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("rollingFileAppender")
public class RollingFileAppenderOutput extends A_Output {

	@XStreamAlias("fileRootPath")
	private String fileRootPath;

	@XStreamAlias("fileNamePattern")
	private String fileNamePattern;

	public String getFileRootPath() {
		return fileRootPath;
	}

	public void setFileRootPath(String fileRootPath) {
		this.fileRootPath = fileRootPath;
	}

	public String getFileNamePattern() {
		return fileNamePattern;
	}

	public void setFileNamePattern(String fileNamePattern) {
		this.fileNamePattern = fileNamePattern;
	}

}
