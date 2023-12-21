package com.example.lr14.repositories;

import com.example.lr14.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
