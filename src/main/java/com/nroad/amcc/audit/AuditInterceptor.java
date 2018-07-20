package com.nroad.amcc.audit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nroad.amcc.support.configuration.AuthenticationUtil;
import com.nroad.amcc.support.stream.KbService;

@Component
public class AuditInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);
	
	@Autowired
	private AuditRepository auditRepository;

	@Autowired
	private KbService kafkaService;

	@Value("${service.source:kb}")
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
		instance.setPrincipal(AuthenticationUtil.getUserId());
		instance.setRemoteAddr(getIpAddr(request));
		instance.setUri("[" + request.getMethod() + "]" + request.getRequestURI());
		instance.setServiceSource(serviceSource);
		instance.setUsername(AuthenticationUtil.getUserName());
		instance.setTenantId(AuthenticationUtil.getTenantId());
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
		event.setUsername(audit.getUsername());
		event.setTenantId(audit.getTenantId());
		kafkaService.sendMessage(event);

		return true;
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
