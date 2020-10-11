package com.test.study.mapper;

import com.test.study.entity.PersInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersInfoRepository extends JpaRepository<PersInfo, String> {

	List<PersInfo> findByBzOrderByDjrqDesc(String bz, Pageable pageable);
}