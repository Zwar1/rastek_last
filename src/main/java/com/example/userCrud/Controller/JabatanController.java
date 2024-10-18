package com.example.userCrud.Controller;

import com.example.userCrud.Dto.*;
import com.example.userCrud.Service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class JabatanController {

    @Autowired
    JabatanService jabatanService;

    @PostMapping(
            path = "/api/addJabatan",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<JabatanRes> create(@RequestBody JabatanReq request) {
        JabatanRes jabatanRes = jabatanService.create(request);
        return web_response.<JabatanRes>builder().data(jabatanRes).build();
    }

    @GetMapping(
            path = "/api/addJabatan/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<JabatanRes> get(@PathVariable("id") Long id){
        JabatanRes jabatan = jabatanService.get(id);
        return web_response.<JabatanRes>builder().data(jabatan).build();
    }

    @PutMapping(
            path = "/api/addJabatan/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<JabatanRes> update(@RequestBody UpdateJabatanReq request,
                                           @PathVariable("id") Long id) {
        request.setId(id);
        JabatanRes jabatanRes = jabatanService.update(request);
        return web_response.<JabatanRes>builder().data(jabatanRes).build();
    }
}
