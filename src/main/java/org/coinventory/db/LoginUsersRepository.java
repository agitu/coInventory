package org.coinventory.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginUsersRepository extends CrudRepository<LoginUsers, Long> {
    Optional<LoginUsers> findByEmail(String email);
}
