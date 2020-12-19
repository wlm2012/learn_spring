package com.test.study.primaryMapper;

import com.test.study.entity.Coffee;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends BaseRepository<Coffee, Long> {


}
