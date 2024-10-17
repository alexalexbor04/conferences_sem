package com.example.conferences_sem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BarbRepository extends JpaRepository<Barbershop, Integer> {
    @Query("SELECT p FROM Barbershop p WHERE  CONCAT(p.name, '', p.date, '', p.service, '', p.master) LIKE %?1%")
    List<Barbershop> search(String keyword);

    @Query("SELECT p FROM Barbershop p WHERE DATE(p.date) = ?1")
    List<Barbershop> findByDate(java.sql.Date date);

    @Query("SELECT DATE(date), COUNT(date) FROM Barbershop GROUP BY DATE(date) ORDER BY DATE(date)")
    List<Object[]> countByDayChart();

    @Query("SELECT COUNT(id) FROM Barbershop")
    int countId();

    @Query("SELECT COUNT(DISTINCT DATE(date)) FROM Barbershop")
    int countDates();
}
