package com.Emil.CRUD.backend.services;

import com.Emil.CRUD.backend.dtos.GymRecordDto;
import com.Emil.CRUD.backend.entities.GymRecord;
import com.Emil.CRUD.backend.exceptions.AppException;
import com.Emil.CRUD.backend.mappers.GymRecordMapper;
import com.Emil.CRUD.backend.repositories.GymRecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RecordsService {

    private final GymRecordsRepository gymRecordsRepository;
    private final GymRecordMapper gymRecordMapper;

    //READ
    public List<GymRecordDto> allRecords(){
        return gymRecordMapper.toGymRecordDtos(gymRecordsRepository.findAll());
    }

    //CREATE
    public GymRecordDto createGymRecord (GymRecordDto  gymRecordDto){
        GymRecord gymRecord = gymRecordMapper.toGymRecord(gymRecordDto);

        GymRecord createdGymRecord = gymRecordsRepository.save(gymRecord);

        return gymRecordMapper.toGymRecordDto(createdGymRecord);
    }
    //DELETE
    public GymRecordDto deleteGymRecord (Long id){
        GymRecord gymRecord = gymRecordsRepository.findById(id).orElseThrow(() -> new AppException("Gym Record not found", HttpStatus.NOT_FOUND) );
        GymRecordDto gymRecordDto = gymRecordMapper.toGymRecordDto(gymRecord);
        gymRecordsRepository.deleteById(id);

        return gymRecordDto;
    }

    //UPDATE
    public GymRecordDto updateGymRecord(Long id, GymRecordDto gymRecordDto){
        GymRecord gymRecord = gymRecordsRepository.findById(id).
                orElseThrow(() -> new AppException("Gym Record not found", HttpStatus.NOT_FOUND) );

        gymRecordMapper.updateGymRecord(gymRecord, gymRecordDto);

        GymRecord savedGymRecord = gymRecordsRepository.save(gymRecord);

        return gymRecordMapper.toGymRecordDto(savedGymRecord);
    }
}
