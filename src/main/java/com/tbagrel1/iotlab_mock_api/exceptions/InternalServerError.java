package com.tbagrel1.iotlab_mock_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerError extends ResponseStatusException {
    public InternalServerError(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
