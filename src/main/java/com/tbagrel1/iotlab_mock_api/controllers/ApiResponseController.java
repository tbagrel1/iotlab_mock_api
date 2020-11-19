package com.tbagrel1.iotlab_mock_api.controllers;

import com.tbagrel1.iotlab_mock_api.forms.ApiResponseForm;
import com.tbagrel1.iotlab_mock_api.models.ApiResponse;
import com.tbagrel1.iotlab_mock_api.repositories.ApiResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiResponseController {
    private ApiResponseRepository apiResponseRepository;

    @Autowired
    public void setApiResponseRepository(ApiResponseRepository apiResponseRepository) {
        this.apiResponseRepository = apiResponseRepository;
    }

    @GetMapping("/api_response")
    public ApiResponse getInstance() {
        return apiResponseRepository.getInstance();
    }

    @PutMapping("/api_response")
    public ApiResponse put(@RequestBody ApiResponseForm form) {
        ApiResponse apiResponse = apiResponseRepository.getInstance();
        apiResponse.updateWithForm(form);
        apiResponseRepository.save(apiResponse);
        return apiResponse;
    }
}
