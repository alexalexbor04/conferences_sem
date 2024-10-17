package com.example.conferences_sem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConfRepository extends JpaRepository<Conferences, Integer> {
    @Query("SELECT p FROM Conferences p WHERE  CONCAT(p.conf_name, '', p.date, '', p.moder_name, '', p.speaker_name) LIKE %?1%")
    List<Conferences> search(String keyword);

    @Query("SELECT p FROM Conferences p WHERE DATE(p.date) = ?1")
    List<Conferences> findByDate(java.sql.Date date);

    @Query("SELECT DATE(date), COUNT(date) FROM Conferences GROUP BY DATE(date) ORDER BY DATE(date)")
    List<Object[]> countByDayChart();

    @Query("SELECT COUNT(id) FROM Conferences")
    int countId();

    @Query("SELECT COUNT(DISTINCT (DATE(date))) FROM Conferences")
    int countDates();
}
