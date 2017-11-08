package com.liveasy.demo.service.preferenceService;

import com.liveasy.demo.model.UserSubmodels.Preference;
import com.liveasy.demo.repository.PreferenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PreferenceServiceImpl implements PreferenceService{

    private PreferenceRepository preferenceRepository;

    @Autowired
    public PreferenceServiceImpl(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public List<?> listAll() {
        List< Preference > preferences = new ArrayList<>();
        preferenceRepository.findAll().forEach(preferences::add);
        return preferences;
    }

    @Override
    public Preference getById(Long id) {

        Optional<Preference> preference = preferenceRepository.findById(id);
        //todo better error handling here
        if(!preference.isPresent()){
            log.error("NO ROLE FOUND BY THE ID: " + id.toString());
        }
        return preference.get();
    }

    @Override
    public Preference saveOrUpdate(Preference domainObject) {
        return preferenceRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        preferenceRepository.deleteById(id);
    }
}
