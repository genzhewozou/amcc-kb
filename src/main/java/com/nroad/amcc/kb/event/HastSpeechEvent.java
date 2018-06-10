package com.nroad.amcc.kb.event;

import com.nroad.event.ApplicationEvent;

public abstract class HastSpeechEvent implements ApplicationEvent {

	private final String id;

	protected HastSpeechEvent(String id) {
		this.id = id;
	}

	@Override
	public String getEventSourceId() {
		return id;
	}

	public String getId() {
		return id;
	}

}
