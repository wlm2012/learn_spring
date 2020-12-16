package com.test.study.primaryMapper;

import com.test.study.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeRepository extends JpaRepository<Long, Coffee> {
}
