package com.zyzz.main.dao;

import com.zyzz.main.model.entity.Najemca;
import com.zyzz.main.model.entity.Obiekt;
import com.zyzz.main.model.entity.Rezerwacja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RezerwacjaDao extends JpaRepository<Rezerwacja, Integer> {

    @Query("SELECT r FROM Rezerwacja r WHERE r.najemca = :najemca")
    List<Rezerwacja> listaDlaNajemcy(@Param("najemca") Najemca najemca);

    @Query("SELECT r FROM Rezerwacja r WHERE r.obiekt = :obiekt")
    List<Rezerwacja> listaDlaObiektu(@Param("obiekt") Obiekt obiekt);

    @Query(value = "SELECT CASE WHEN EXISTS" +
            "( SELECT r.id FROM Rezerwacja r WHERE (" +
            "(:start >= start_date AND :start <= end_date)" +
            " OR (:end >= start_date AND :end <= end_date)" +
            " OR (:start <= start_date AND :end > start_date))"+
            " AND obiekt_id = :obiekt_id)" +
            "THEN 'true'" +
            "ELSE 'false'" +
            "END", nativeQuery = true)
    boolean czyJestKolizja(@Param("start")LocalDate start, @Param("end") LocalDate end, @Param("obiekt_id") int obiektId);

    @Query(value = "SELECT CASE WHEN EXISTS" +
            "( SELECT r.id FROM Rezerwacja r WHERE (" +
            "(:start >= start_date AND :start <= end_date)" +
            " OR (:end >= start_date AND :end <= end_date)" +
            " OR (:start <= start_date AND :end > start_date))"+
            " AND obiekt_id = :obiektId" +
            " AND id != :rezerwacjaId )" +
            "THEN 'true'" +
            "ELSE 'false'" +
            "END", nativeQuery = true)
    boolean czyJestKolizjaDlaRezerwacji(@Param("start")LocalDate start, @Param("end") LocalDate end, @Param("obiektId") int obiektId, @Param("rezerwacjaId") int rezerwacjaId);


}
