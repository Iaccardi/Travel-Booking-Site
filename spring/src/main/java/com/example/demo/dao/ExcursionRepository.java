package com.example.demo.dao;

import com.example.demo.entities.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}