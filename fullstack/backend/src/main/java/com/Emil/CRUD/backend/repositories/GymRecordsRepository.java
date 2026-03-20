package com.Emil.CRUD.backend.repositories;

import com.Emil.CRUD.backend.entities.GymRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRecordsRepository extends JpaRepository <GymRecord, Long> {
}
