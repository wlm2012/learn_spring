package com.test.study.primaryMapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<T, Long> extends JpaRepository<T, Long> {
}
