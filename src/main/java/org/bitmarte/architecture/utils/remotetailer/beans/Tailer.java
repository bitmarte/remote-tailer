package org.bitmarte.architecture.utils.remotetailer.beans;

import java.util.List;

import org.bitmarte.architecture.utils.remotetailer.beans.outputs.A_Output;

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
	private List<A_Output> output;

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public List<A_Output> getOutput() {
		return output;
	}

	public void setOutput(List<A_Output> output) {
		this.output = output;
	}

}
