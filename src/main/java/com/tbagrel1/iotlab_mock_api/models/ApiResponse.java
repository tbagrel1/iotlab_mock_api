package com.tbagrel1.iotlab_mock_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tbagrel1.iotlab_mock_api.Utils;
import com.tbagrel1.iotlab_mock_api.exceptions.BadRequest;
import com.tbagrel1.iotlab_mock_api.exceptions.InternalServerError;
import com.tbagrel1.iotlab_mock_api.exceptions.NotFound;
import com.tbagrel1.iotlab_mock_api.forms.ApiResponseForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Entity(name = "api_responses")
public class ApiResponse {
    public static final long SINGLETON_ID = 1;
    public static final List<Integer> AUTHORIZED_HTTP_STATUS_CODES = List.of(200, 404, 500);

    private static final String message = "triggered by the user via /api_response route";

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "http_status_code", nullable = false)
    private Integer httpStatusCode;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonProperty("http_status_code")
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public ApiResponse() {
        this.id = SINGLETON_ID;
        this.httpStatusCode = null;
    }

    public ApiResponse(Integer httpStatusCode) {
        this.id = SINGLETON_ID;
        this.httpStatusCode = httpStatusCode;
    }

    @JsonIgnore
    public void updateWithForm(ApiResponseForm form) {
        Utils.ensureNotNull("http_status_code", form.httpStatusCode);
        if (!AUTHORIZED_HTTP_STATUS_CODES.contains(form.httpStatusCode)) {
            throw new BadRequest(String.format("HTTP status code should be one of %s", AUTHORIZED_HTTP_STATUS_CODES));
        }
        this.httpStatusCode = form.httpStatusCode;
    }

    @JsonIgnore
    public Optional<RuntimeException> getException() {
        switch (httpStatusCode) {
            case 404:
                return Optional.of(new NotFound(message));
            case 500:
                return Optional.of(new InternalServerError(message));
            default:
                return Optional.empty();
        }
    }
}
