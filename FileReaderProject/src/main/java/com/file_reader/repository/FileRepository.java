package com.file_reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file_reader.entity.FileEntity;
@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
