package com.Eidiko.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Eidiko.Entity.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

	Optional<Image> findByimageName(String imageName);



}
