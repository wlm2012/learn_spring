package com.test.study.mapper;

import com.test.study.entity.BankAcctSetParm;
import com.test.study.entity.InitBasicAttData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitBasicAttDataRepository extends JpaRepository<InitBasicAttData,String> {
}
