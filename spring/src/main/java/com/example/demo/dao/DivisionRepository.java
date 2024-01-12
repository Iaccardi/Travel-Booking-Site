package com.example.demo.dao;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin

public interface DivisionRepository extends JpaRepository<Division, Long> {
}