package com.demo.repositories;

import com.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRepositories extends JpaRepository<Employee, Long> {

    // --- Already existing ---
    List<Employee> findByLastName(String lastName);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findAllByFirstNameAndAgeLessThan(String firstName, Integer age);

    List<Employee> findByFirstNameOrLastName(String firstname, String lastname);

    // Optional for login/auth
    Optional<Employee> findByUsername(String username);

    // --- 🧭 Pagination ---
    Page<Employee> findAll(Pageable pageable);

    // --- 🔍 Search / Filtering ---
    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:department IS NULL OR LOWER(e.department) LIKE LOWER(CONCAT('%', :department, '%'))) " +
            "AND (:minAge IS NULL OR e.age >= :minAge) " +
            "AND (:maxAge IS NULL OR e.age <= :maxAge)")
    List<Employee> searchEmployees(String name, String department, Integer minAge, Integer maxAge);

    // --- 🏢 Department-based ---
    List<Employee> findByDepartmentId(Long departmentId);



    // --- 🧾 Status (Active / Inactive) ---
    List<Employee> findByActive(boolean active);

    // --- ⏱️ Date Range ---
    List<Employee> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

    // --- 📊 Aggregation / Reporting ---

    // Average, min, max age statistics
    @Query("SELECT AVG(e.age) FROM Employee e")
    Double getAverageAge();

    @Query("SELECT MIN(e.age) FROM Employee e")
    Integer getMinAge();

    @Query("SELECT MAX(e.age) FROM Employee e")
    Integer getMaxAge();

    // Count employees per department
    @Query("SELECT e.department.name, COUNT(e) FROM Employee e GROUP BY e.department.name")
    List<Object[]> getEmployeeCountByDepartment();
}
