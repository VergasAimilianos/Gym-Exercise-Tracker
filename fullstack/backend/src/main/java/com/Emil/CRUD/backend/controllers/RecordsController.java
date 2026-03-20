package com.Emil.CRUD.backend.controllers;

import com.Emil.CRUD.backend.dtos.GymRecordDto;
import com.Emil.CRUD.backend.entities.GymRecord;
import com.Emil.CRUD.backend.services.RecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class RecordsController {
    private final RecordsService recordsService;

    @GetMapping("/gym/records") //Read
    public ResponseEntity<List<GymRecordDto>> allRecords(){
        return ResponseEntity.ok(recordsService.allRecords());
    }

    @PostMapping("/gym/records") // Create
    public ResponseEntity<GymRecordDto> createGymRecord(@RequestBody GymRecordDto gymRecordDto){
        GymRecordDto createdGymRecord = recordsService.createGymRecord(gymRecordDto);
        return ResponseEntity.created(URI.create("/gym/records/"+createdGymRecord.getId()))
                .body(createdGymRecord);
    }

    @DeleteMapping("gym/records/{id}")
    public ResponseEntity<GymRecordDto> deleteGymRecord(@PathVariable Long id){
        return ResponseEntity.ok(recordsService.deleteGymRecord(id));

    }

    @PutMapping("gym/records/{id}")
    public ResponseEntity<GymRecordDto> updateGymRecord(@PathVariable Long id, @RequestBody GymRecordDto gymRecordDto){
        return ResponseEntity.ok(recordsService.updateGymRecord(id, gymRecordDto));

    }

}
