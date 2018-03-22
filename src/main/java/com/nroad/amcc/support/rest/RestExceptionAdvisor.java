package com.nroad.amcc.support.rest;

import java.nio.file.AccessDeniedException;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nroad.amcc.PlatformError;

/**
 * REST API exception advisor.
 */
@ControllerAdvice(basePackages = "com.nroad.amcc")
public class RestExceptionAdvisor extends ResponseEntityExceptionHandler {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(RestExceptionAdvisor.class);

    /**
     * Handle runtime exception.
     *
     * @param e exception thrown.
     * @return REST error.
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    public RestError handleRuntimeException(RuntimeException e) {
        logger.error("Server internal error:{}", e.getMessage());
        return RestError.internalError(e.getMessage());
    }

    /**
     * Handle generic Illegal argument exception.
     *
     * @param e exception thrown.
     * @return REST error.
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public RestError handleIllegalArgumentException(IllegalArgumentException e) {
        logger.info("Platform error, {}", e.getMessage());
        return RestError.of(PlatformError.BAD_REQUEST);
    }

    /**
     * Handle ConstraintViolationException.
     *
     * @param e exception thrown.
     * @return REST error.
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public RestError handleConstraintViolationException(ConstraintViolationException e) {
        logger.info("Platform error, {}", e.getMessage());
        return RestError.of(PlatformError.BAD_REQUEST);
    }

    /**
     * Handle no access rights.
     *
     * @param e exception thrown.
     * @return REST error.
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(value = {AccessDeniedException.class})
    public RestError handleRuntimeException(AccessDeniedException e) {
        logger.error("No access rights", e.getMessage());
        return RestError.of(PlatformError.FORBIDDEN);
    }
}
