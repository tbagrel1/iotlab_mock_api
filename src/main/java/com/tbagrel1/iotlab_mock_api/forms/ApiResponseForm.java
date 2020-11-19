package com.tbagrel1.iotlab_mock_api.forms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponseForm {
    @JsonProperty("http_status_code")
    public Integer httpStatusCode;
}
