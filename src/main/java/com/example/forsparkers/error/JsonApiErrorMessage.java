package com.example.forsparkers.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonApiErrorMessage extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        Map<String, Object> response = new HashMap<>();

        Integer status = (Integer) errorAttributes.get("status");
        response.put("code", status);

        String message = (String) errorAttributes.get("message");
        if (!StringUtils.hasLength(message)) {
            message = HttpStatus.resolve(status).toString();
        }
        response.put("message", message);

        return response;
    }
}
