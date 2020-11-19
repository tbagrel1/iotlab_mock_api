package com.tbagrel1.iotlab_mock_api.repositories;

import com.tbagrel1.iotlab_mock_api.models.Mote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoteRepository extends CrudRepository<Mote, String> {}
