package com.ycu.king.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述:
 *
 * @date :2022/10/11
 * @description :
 * @author: moji
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/cost")
    public String cost(int time){

        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
