package com.example.userCrud.Service;

import com.example.userCrud.Dto.*;
import com.example.userCrud.Entity.DepartementEntity;
import com.example.userCrud.Entity.DivisionEntity;
import com.example.userCrud.Entity.SubDivisionEntity;
import com.example.userCrud.Repository.DivisionRepository;
import com.example.userCrud.Repository.SubDivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SubDivisionService {

    @Autowired
    SubDivisionRepository subDivisionRepository;

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    ValidationService validationService;

    @Transactional
    public SubDivisionRes create(SubDivisionReq request) {
        validationService.validate(request);

        // Find the Departement from the database using the provided ID
        DivisionEntity division = divisionRepository.findById(request.getDivision_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Division not found"));

        SubDivisionEntity subDivision = new SubDivisionEntity();

        subDivision.setSubDivision_name(request.getSubDivision_name());
        subDivision.setDivisionEntity(division);

        subDivisionRepository.save(subDivision);

        return SubDivisionRes.builder()
                .id(subDivision.getId())
                .subDivision_name(subDivision.getSubDivision_name())
                .build();
    }

    @Transactional(readOnly = true)
    public SubDivisionRes get(Long id){
        SubDivisionEntity subDivision = subDivisionRepository.findFirstById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "SubDivision Not Found"));

        return SubDivisionRes.builder()
                .id(subDivision.getId())
                .subDivision_name(subDivision.getSubDivision_name())
                .build();
    }

    @Transactional
    public  SubDivisionRes update(UpdateSubDivisionReq request) {

        validationService.validate(request);


        SubDivisionEntity subDivision = subDivisionRepository.findFirstById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubDivision Not Found"));

        subDivision.setSubDivision_name(request.getSubDivision_name());

        subDivisionRepository.save(subDivision);

        return SubDivisionRes.builder()
                .id(subDivision.getId())
                .subDivision_name(subDivision.getSubDivision_name())
                .build();
    }
}
