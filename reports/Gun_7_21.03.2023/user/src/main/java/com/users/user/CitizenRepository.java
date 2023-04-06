package com.users.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CitizenRepository extends CrudRepository<Citizen, Integer> {
}
