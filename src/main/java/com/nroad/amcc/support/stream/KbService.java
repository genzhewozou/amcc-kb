package com.nroad.amcc.support.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.nroad.event.ApplicationEvent;
import com.nroad.event.EventBuilder;

@Service
public class KbService {

	@Autowired
	private KbSource kbSource;

	public void sendMessage(ApplicationEvent event) {
		kbSource.kb().send(MessageBuilder.withPayload(EventBuilder.withPayload(event).build()).build());
	}

}
