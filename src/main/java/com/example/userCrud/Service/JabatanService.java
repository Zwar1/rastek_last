package com.example.userCrud.Service;

import com.example.userCrud.Dto.*;
import com.example.userCrud.Entity.*;
import com.example.userCrud.Repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JabatanService {

    @Autowired
    JabatanRepository jabatanRepository;

    @Autowired
    ValidationService validationService;

    @Transactional
    public JabatanRes create(JabatanReq request) {
        validationService.validate(request);

        JabatanEntity jabatanEntity = new JabatanEntity();

        jabatanEntity.setKode_jabatan(request.getKode_jabatan());
        jabatanEntity.setNama_struktural(request.getNama_struktural());
        jabatanEntity.setNama_fungsional(request.getNama_fungsional());

        jabatanRepository.save(jabatanEntity);

        return toJabatanResponse(jabatanEntity);

    }

    @Transactional(readOnly = true)
    public JabatanRes get(Long id){
        JabatanEntity jabatan = jabatanRepository.findFirstById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Jabatan Not Found"));

        return toJabatanResponse(jabatan);
    }

    @Transactional
    public JabatanRes update(UpdateJabatanReq request) {

        validationService.validate(request);

        JabatanEntity jabatan = jabatanRepository.findFirstById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jabatan Not Found"));

        JabatanEntity jabatanEntity = new JabatanEntity();

        jabatanEntity.setKode_jabatan(request.getKode_jabatan());
        jabatanEntity.setNama_struktural(request.getNama_struktural());
        jabatanEntity.setNama_fungsional(request.getNama_fungsional());

        jabatanRepository.save(jabatanEntity);

        return toJabatanResponse(jabatanEntity);
    }

    private JabatanRes toJabatanResponse(JabatanEntity jabatan) {

        JabatanEntity jabatan1 = jabatan;

        return JabatanRes.builder()
                .id(jabatan1.getId())
                .kode_jabatan(jabatan1.getKode_jabatan())
                .nama_struktural(jabatan1.getNama_struktural())
                .nama_fungsional(jabatan1.getNama_fungsional())
                .build();
    }
}
