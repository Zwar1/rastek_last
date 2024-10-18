//package com.example.userCrud.Controller;
//
//import com.example.userCrud.Dto.*;
//import com.example.userCrud.Service.LiquidService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/liquid")
//public class LiquidController {
//
//    @Autowired
//    private LiquidService liquidService;
//
//    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public web_response<LiquidResponse> createLiquid(@ModelAttribute LiquidRequest request){
//        LiquidResponse liquidResponse = liquidService.CrateLiquid(request);
//        return web_response.<LiquidResponse>builder().data(liquidResponse).message("Success").build();
//    }
//
//    @GetMapping("/get")
//    public List<LiquidResponse> GetAll(){
//        return liquidService.GetAllLiquid();
//    }
//}
