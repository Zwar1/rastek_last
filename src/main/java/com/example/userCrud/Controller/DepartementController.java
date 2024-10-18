package com.example.userCrud.Controller;

import com.example.userCrud.Dto.*;
import com.example.userCrud.Service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartementController {

    @Autowired
    private DepartementService departementService;


    //Post API
//    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping(
            path = "/api/addDepartement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<DepartementRes> create(@RequestBody DepartementReq request) {
        DepartementRes departementRes = departementService.create(request);
        return web_response.<DepartementRes>builder().data(departementRes).build();
    }

    //Get API
    @GetMapping(
            path = "/api/addDepartement/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<DepartementRes> get(@PathVariable("id") Long id){
        DepartementRes departementRes = departementService.get(id);
        return web_response.<DepartementRes>builder().data(departementRes).build();
    }

    //Put API
    @PutMapping(
            path = "/api/addDepartement/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<DepartementRes> update(@RequestBody UpdateDepartementReq request,
                                           @PathVariable("id") Long id) {
        request.setId(id);
        DepartementRes departementRes = departementService.update(request);
        return web_response.<DepartementRes>builder().data(departementRes).build();
    }
}
