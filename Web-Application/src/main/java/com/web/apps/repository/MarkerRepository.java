package com.web.apps.repository;

import com.web.apps.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, String>, JpaSpecificationExecutor<Marker> {

    Marker findFirstById(Integer id);
}
