package com.test.study.mapper;

import com.test.study.entity.PersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersInfoRepository extends JpaRepository<PersInfo, String> {
}
