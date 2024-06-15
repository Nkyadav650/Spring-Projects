package com.Eidiko.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Eidiko.Entity.File;
@Repository
public interface FileRepo extends JpaRepository<File, Long> {

 public	Optional<File> findByfileName(String fileName);

}
