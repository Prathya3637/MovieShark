package com.project.movieshark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.movieshark.entity.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
