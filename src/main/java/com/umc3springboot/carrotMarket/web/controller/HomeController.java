package com.umc3springboot.carrotMarket.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class HomeController {

    @GetMapping("/")
    public String connectedTest(){
        return "스프링부트 6주차 미션 - 연결됨";
    }
}
