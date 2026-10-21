package com.example.pokemonzukan_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController//これをつけることでHTTPリクエストをおこなう人として認識される
public class HealthController {
    //GET担当の処理
    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
}
