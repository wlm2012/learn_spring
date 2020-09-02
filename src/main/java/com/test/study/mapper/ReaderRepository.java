package com.test.study.mapper;

import com.test.study.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReaderRepository extends JpaRepository<Reader, String> {
}
