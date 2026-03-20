package com.Emil.CRUD.backend.mappers;

import com.Emil.CRUD.backend.dtos.GymRecordDto;
import com.Emil.CRUD.backend.entities.GymRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")

public interface GymRecordMapper {

    GymRecord toGymRecord(GymRecordDto dto);

    GymRecordDto toGymRecordDto(GymRecord gymRecord);

    List<GymRecordDto> toGymRecordDtos(List<GymRecord> gymRecords);

    @Mapping(target= "id", ignore = true) // don't want to overwrite ID
    void updateGymRecord(@MappingTarget GymRecord gymRecord, GymRecordDto gymRecordDto);

}
