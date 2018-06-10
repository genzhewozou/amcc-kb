package com.nroad.amcc.support.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KbSource {

	String KB = "kb";

	String ERROR_REPORT = "error-report";

	@Output(KB)
	MessageChannel kb();

	@Output(ERROR_REPORT)
	MessageChannel errorReport();
	
}
