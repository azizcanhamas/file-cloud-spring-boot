package com.h2group.h2example;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    List<Citizen> findByName(String name);
}
