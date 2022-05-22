package com.zyzz.main.dao;

import com.zyzz.main.model.entity.Obiekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ObiektDao extends JpaRepository<Obiekt, Integer> {

    @Query("SELECT o FROM Obiekt o WHERE nazwa = :nazwa")
    Obiekt findByName(@Param("nazwa") String nazwa);

}
