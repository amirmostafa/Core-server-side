package com.core.controllers;

import com.core.models.BasicModel;
import com.core.services.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(value = 10)
public class GlobalControllerExceptionHandler extends AbstractController {

    final static Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    public static final String EXCEPTION_MESSAGE = "message.general.error";


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BasicModel> defaultExceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        try {
            log.error("Hanlding Exception for url : " + request.getRequestURI() + " in global exception handler");
        } catch (Exception exp) {
        }

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        log.error("-------------GlobalControllerExceptionHandler.defaultExceptionHandler()", e);

        BasicModel response = new BasicModel();

        String message = e.getMessage();

        if (message!=null&&StringUtils.isAllUpperCase(message.replace("_", ""))) {
            response.setReplyMessage(message);
        } else {
            response.setReplyMessage(Constants.GENERAL_ERROR_MESSAGE);
        }


        response.setPayload(EXCEPTION_MESSAGE);
        return error(response);
    }


}
