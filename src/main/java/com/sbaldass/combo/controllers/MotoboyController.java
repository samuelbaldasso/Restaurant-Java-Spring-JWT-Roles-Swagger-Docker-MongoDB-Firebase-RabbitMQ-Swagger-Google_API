package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.ApiResponse;
import com.sbaldass.combo.domain.Motoboy;
import com.sbaldass.combo.domain.MotoboyRequest;
import com.sbaldass.combo.domain.NearbyMotoboysResponse;
import com.sbaldass.combo.services.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoboys")
public class MotoboyController {

    @Autowired
    private MotoboyService motoboyService;

    @GetMapping("/nearby")
    public ResponseEntity<?> getNearbyMotoboys(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(required = false, defaultValue = "5000") int radius) {

        try {
            List<Motoboy> motoboys = motoboyService.findNearbyMotoboys(latitude, longitude, radius);
            return ResponseEntity.ok(new NearbyMotoboysResponse(motoboys));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestMotoboyService(@RequestBody MotoboyRequest request, Authentication authentication) {
        try {
            motoboyService.processMotoboyRequest(request);
            return ResponseEntity.ok(new ApiResponse("Motoboy request received. You will be notified once a motoboy is assigned."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

}
