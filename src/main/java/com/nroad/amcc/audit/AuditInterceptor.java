package com.nroad.amcc.audit;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nroad.amcc.support.stream.KbService;

@Component
public class AuditInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);

	private static final Decoder decoder = Base64.getDecoder();
	
	private static final ObjectMapper mapper=new ObjectMapper();
	
	@Autowired
	private AuditRepository auditRepository;

	@Autowired
	private KbService kafkaService;

	@Value("${service.source:crm}")
	private String serviceSource;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e)
			throws Exception {
		logger.debug("AuditInterceptor afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		logger.debug("AuditInterceptor postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		logger.debug("AuditInterceptor preHandle");

		Audit instance = new Audit();
		instance.setMethod(request.getMethod());
		// instance.setPrincipal(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		instance.setPrincipal(getValueFormJwtToken(request, "user_id"));
		instance.setRemoteAddr(getIpAddr(request));
		instance.setUri("[" + request.getMethod() + "]" + request.getRequestURI());
		instance.setServiceSource(serviceSource);
		Audit audit = auditRepository.save(instance);

		// 发送kafka消息
		AuditEvent event = new AuditEvent();
		event.setId(audit.getId());
		event.setCreatedDate(audit.getCreatedDate().getTime());
		event.setMethod(audit.getMethod());
		event.setPrincipal(audit.getPrincipal());
		event.setRemoteAddr(audit.getRemoteAddr());
		event.setServiceSource(audit.getServiceSource());
		event.setUri(audit.getUri());
		kafkaService.sendMessage(event);

		return true;
	}

	private String getValueFormJwtToken(HttpServletRequest request, String key)
			throws JsonProcessingException, IOException {
		if (key == null || key.trim().length() == 0) {
			return null;
		}
		String jwtToken = request.getHeader("Authorization");
		if (jwtToken == null || !jwtToken.startsWith("Bearer")) {
			return null;
		}
		String payload = jwtToken.substring(jwtToken.indexOf(".") + 1, jwtToken.lastIndexOf("."));
		String decoderStr = new String(decoder.decode(payload), "UTF-8");
		return mapper.readTree(decoderStr).get(key).asText();
	}

	// 获得客户端真实IP地址
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
