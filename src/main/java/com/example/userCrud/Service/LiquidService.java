//package com.example.userCrud.Service;
//
//import com.example.userCrud.Dto.LiquidRequest;
//import com.example.userCrud.Dto.LiquidResponse;
//import com.example.userCrud.Dto.UserResponse;
//import com.example.userCrud.Entity.Liquid;
//import com.example.userCrud.Entity.Roles;
//import com.example.userCrud.Entity.User;
//import com.example.userCrud.Repository.LiquidRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class LiquidService {
//
//    @Autowired
//    private LiquidRepository liquidRepository;
//
//    @Autowired
//    private ImageStoreService imageStoreService;
//
//    @Autowired
//    private ValidationService validationService;
//
//    @Transactional
//    public LiquidResponse CrateLiquid(LiquidRequest request){
//
//        if(liquidRepository.existsByName(request.getName())){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Liquid already exists");
//        }
//
//        String imageUrl = imageStoreService.uploadImage(request.getImage());
//
//        Liquid liquid = new Liquid();
//
//        liquid.setName(request.getName());
//        liquid.setFlavour(request.getFlavour());
//        liquid.setPrice(request.getPrice());
//        liquid.setImage(imageUrl);
//
//        liquidRepository.save(liquid);
//
//        return LiquidResponse.builder()
//                .name(liquid.getName())
//                .price(liquid.getPrice())
//                .flavour(liquid.getFlavour())
//                .image(liquid.getImage())
//                .build();
//    }
//
//    @Transactional(readOnly = true)
//    public List<LiquidResponse> GetAllLiquid(){
//        List<Liquid> liquidList = liquidRepository.findAll();
//
//        return liquidList.stream().map(
//                liquid -> LiquidResponse.builder()
//                        .name(liquid.getName())
//                        .flavour(liquid.getFlavour())
//                        .price(liquid.getPrice())
//                        .image(liquid.getImage())
//                        .build()).collect(Collectors.toList());
//    }
//}
