package com.tbagrel1.iotlab_mock_api.controllers;

import com.tbagrel1.iotlab_mock_api.Utils;
import com.tbagrel1.iotlab_mock_api.forms.MoteForm;
import com.tbagrel1.iotlab_mock_api.models.DataWrapper;
import com.tbagrel1.iotlab_mock_api.models.Mote;
import com.tbagrel1.iotlab_mock_api.repositories.ApiResponseRepository;
import com.tbagrel1.iotlab_mock_api.repositories.MoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoteController {
    private MoteRepository moteRepository;
    private ApiResponseRepository apiResponseRepository;

    @Autowired
    public void setMoteRepository(MoteRepository moteRepository) {
        this.moteRepository = moteRepository;
    }

    @Autowired
    public void setApiResponseRepository(ApiResponseRepository apiResponseRepository) {
        this.apiResponseRepository = apiResponseRepository;
    }

    @GetMapping("/iotlab/rest/data/1/light1/last")
    public DataWrapper getAll() {
        apiResponseRepository.getInstance().getException().ifPresent(exception -> { throw exception; });
        return new DataWrapper(Utils.toList(moteRepository.findAll()));
    }

    @PutMapping("/iotlab/rest/data/1/light1/last/{id}")
    public Mote put(@PathVariable("id") String id, @RequestBody MoteForm form) {
        Mote mote = moteRepository.findById(id).orElse(new Mote(id));
        mote.updateWithForm(form);
        moteRepository.save(mote);
        return mote;
    }
}
