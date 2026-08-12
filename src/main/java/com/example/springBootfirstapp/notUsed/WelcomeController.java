//package com.example.springBootfirstapp;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//
//public class WelcomeController {
//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Привет! Это твой первый GET эндпоинт.";
//    }
//
//    @PostMapping("/hello")
//    public String sayPostHello() {
//        return "А это уже POST запрос! Ты отправил данные на сервер.";
//    }
//    @GetMapping("/info")
//    public UserInfo getInfo() {
//        return new UserInfo("Dev", "Learning Spring Boot");
//    }
//
//}
