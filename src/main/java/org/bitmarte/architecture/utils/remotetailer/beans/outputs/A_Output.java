package org.bitmarte.architecture.utils.remotetailer.beans.outputs;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
public class A_Output {

	@XStreamAlias("deleteOnStart")
	@XStreamAsAttribute
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = { "true", "false" })
	private boolean deleteOnStart;

	@XStreamAlias("layoutPattern")
	private String layoutPattern;

	public boolean isDeleteOnStart() {
		return deleteOnStart;
	}

	public void setDeleteOnStart(boolean deleteOnStart) {
		this.deleteOnStart = deleteOnStart;
	}

	public String getLayoutPattern() {
		return layoutPattern;
	}

	public void setLayoutPattern(String layoutPattern) {
		this.layoutPattern = layoutPattern;
	}

}
