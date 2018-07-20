package com.nroad.amcc;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * REST API exception advisor.
 */
@ControllerAdvice
public class RestExceptionAdvisor extends ResponseEntityExceptionHandler {
    /** Logger. */
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
        logger.error("Server internal error:{}", e.getMessage(), e);
        return RestError.of(SystemError.SERVER_INTERNAL_ERROR);
    }

    /**
     * Handle system exception.
     *
     * @param e exception thrown.
     * @return REST error.
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(value = {SystemException.class})
    public RestError handleSystemException(SystemException e) {
        logger.error("Server internal error:{}", e.getMessage(), e);
        return RestError.of(e.getError());
    }

    /**
     * Handle generic Illegal argument exception.
     *
     * @param e exception thrown.
     * @return REST error.
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public RestError handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("Platform error, {}", e.getMessage(), e);
        return RestError.of(SystemError.SERVER_INTERNAL_ERROR);
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
        logger.info("User error, {}", e.getMessage(), e);
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
    public RestError handleAccessDeniedException(AccessDeniedException e) {
        logger.info("Access denied", e.getMessage());
        return RestError.of(PlatformError.OPERATION_ACCESS_DENIED);
    }

    /**
     * Handle no access rights.
     *
     * @param e exception thrown.
     * @return REST error.
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {PlatformException.class})
    public RestError handlePlatformException(PlatformException e) {
        logger.info("User error: {}", e.getMessage());
        return RestError.of(e.getError());
    }
    
}
