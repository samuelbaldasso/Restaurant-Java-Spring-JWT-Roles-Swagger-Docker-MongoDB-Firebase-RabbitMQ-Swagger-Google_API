package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.*;
import com.sbaldass.combo.services.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoboy")
public class MotoboyController {

    @Autowired
    private MotoboyService motoboyService;

    @PreAuthorize("hasRole('CUSTOMER')")
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

    @PreAuthorize("hasRole('MOTOBOY')")
    @PostMapping("/accept")
    public ResponseEntity<?> acceptRequest(@RequestBody RequestAction requestAction, Authentication authentication) {
        try {
            motoboyService.acceptRequest(requestAction.getRequestId(), authentication.getName());
            return ResponseEntity.ok(new ApiResponse("Request accepted successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('MOTOBOY')")
    @PostMapping("/reject")
    public ResponseEntity<?> rejectRequest(@RequestBody RequestAction requestAction, Authentication authentication) {
        try {
            motoboyService.rejectRequest(requestAction.getRequestId(), authentication.getName());
            return ResponseEntity.ok(new ApiResponse("Request rejected successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('CUSTOMER')")
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
