package org.bitmarte.architecture.utils.remotetailer.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("tailer")
public class Tailer {

	@XStreamAlias("input")
	private Input input;

	@XStreamAlias("output")
	private Output output;

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

}
