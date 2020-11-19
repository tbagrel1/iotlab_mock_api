package com.tbagrel1.iotlab_mock_api.repositories;

import com.tbagrel1.iotlab_mock_api.models.ApiResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiResponseRepository extends CrudRepository<ApiResponse, Long> {
    default ApiResponse getInstance() {
        return findById(ApiResponse.SINGLETON_ID).orElseGet(() -> {
            ApiResponse apiResponse = new ApiResponse(200);
            save(apiResponse);
            return apiResponse;
        });
    }
}
