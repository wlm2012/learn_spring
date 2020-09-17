package com.test.study.mapper;

import com.test.study.entity.BankAcctSetParm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAcctSetParmRepository  extends JpaRepository<BankAcctSetParm,String> {
}
