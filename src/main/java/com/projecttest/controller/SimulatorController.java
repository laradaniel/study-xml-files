package com.projecttest.controller;

import com.projecttest.service.SimulatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/simulator")
public class SimulatorController {

    SimulatorService simulatorService;

    public SimulatorController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }

    @GetMapping
    public ResponseEntity<byte[]> getAllTransactions(){
        byte[] zipBytes = simulatorService.findAllTransactions();
        if(zipBytes.length == 0) return ResponseEntity.noContent().build();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=xml_package.zip")
                .body(zipBytes);
    }

    @PostMapping
    public ResponseEntity<Void> postTransactions(@RequestBody MultipartFile arquivoXML){
        try {
            String xmlContent = new String(arquivoXML.getBytes());
            simulatorService.save(xmlContent);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
