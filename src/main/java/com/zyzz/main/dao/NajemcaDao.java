package com.zyzz.main.dao;

import com.zyzz.main.model.entity.Najemca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NajemcaDao extends JpaRepository<Najemca, Integer> {

    @Query("SELECT n FROM Najemca n WHERE nazwa = :nazwa")
    Najemca findByName(@Param("nazwa") String nazwa);
}
