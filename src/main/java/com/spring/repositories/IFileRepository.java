package com.spring.repositories;

import com.spring.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFileRepository extends JpaRepository<File, UUID> {
    File getFileByName(String filename);

}
