package com.tbagrel1.iotlab_mock_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFound extends ResponseStatusException {
    public NotFound(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
