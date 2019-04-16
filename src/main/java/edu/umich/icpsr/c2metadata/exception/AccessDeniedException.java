package edu.umich.icpsr.c2metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Access denied to this resource.")
public class AccessDeniedException extends RuntimeException {
    private static final long serialVersionUID = 9146782392900355932L;

}
