package org.bitmarte.architecture.utils.remotetailer.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("input")
public class Input {

	@XStreamAlias("type")
	@XStreamAsAttribute
	private String type;

	@XStreamAlias("host")
	private String host;

	@XStreamAlias("port")
	private int port;

	@XStreamAlias("username")
	private String username;

	@XStreamAlias("password")
	private String password;

	@XStreamAlias("filePath")
	private String filePath;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
