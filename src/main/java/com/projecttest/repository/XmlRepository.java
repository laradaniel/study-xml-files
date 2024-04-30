package com.projecttest.repository;

import com.projecttest.model.XmlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XmlRepository extends JpaRepository<XmlData, Long> {
}
