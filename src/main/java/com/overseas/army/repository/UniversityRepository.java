package com.overseas.army.repository;

import com.overseas.army.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByCountry(String country);
    List<University> findByCountryOrderByRankAsc(String country);
    
    @Query("SELECT u FROM University u WHERE " +
           "(:country IS NULL OR :country = '' OR u.country = :country) AND " +
           "(:search IS NULL OR :search = '' OR u.nameCn LIKE %:search% OR u.nameEn LIKE %:search%) " +
           "ORDER BY u.rank ASC")
    List<University> search(@Param("country") String country, @Param("search") String search);
}