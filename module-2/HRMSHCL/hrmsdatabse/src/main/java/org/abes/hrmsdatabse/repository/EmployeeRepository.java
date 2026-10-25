package org.abes.hrmsdatabse.repository;

import jakarta.persistence.LockModeType;
import org.abes.hrmsdatabse.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Employee> findByEmpId(long empId);
}
