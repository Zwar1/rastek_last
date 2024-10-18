package com.example.userCrud.Controller;


import com.example.userCrud.Dto.*;
import com.example.userCrud.Entity.EmployeeEntity;
import com.example.userCrud.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(
            path = "/api/addEmployee",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<EmployeeRes> create(@RequestBody EmployeeReq request) {
        EmployeeRes employeeRes = employeeService.create(request);
        return web_response.<EmployeeRes>builder().data(employeeRes).build();
    }


    @GetMapping(
            path = "/api/addEmployee/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<EmployeeRes> get(@PathVariable("id") Long id){
        EmployeeRes employeeRes = employeeService.get(id);
        return web_response.<EmployeeRes>builder().data(employeeRes).build();
    }

    @PutMapping(
            path = "/api/addEmployee/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public web_response<EmployeeRes> update(@RequestBody UpdateEmployeeReq request,
                                           @PathVariable("NIK") Long NIK) {
        request.setNIK(NIK);
        EmployeeRes employeeRes = employeeService.update(request);
        return web_response.<EmployeeRes>builder().data(employeeRes).build();
    }

    @PostMapping
    public ResponseEntity<EmployeeRes> createEmployee(@RequestBody EmployeeReq req) {
        // Panggil service untuk membuat karyawan baru
        EmployeeRes employeeRes = employeeService.create(req);

        // Mengembalikan ResponseEntity dengan status CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRes);
    }
}

