package com.Emil.CRUD.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class GymRecordDto {
    private Long id;
    private String exercise;
    private Integer weight;
}
