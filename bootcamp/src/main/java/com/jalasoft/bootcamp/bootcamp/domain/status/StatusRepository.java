package com.jalasoft.bootcamp.bootcamp.domain.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, StatusId>
{
}
