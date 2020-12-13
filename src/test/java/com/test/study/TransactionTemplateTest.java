package com.test.study;


import com.test.study.entity.TPersInfo;
import com.test.study.primaryMapper.PersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
public class TransactionTemplateTest {


	private PersMapper persMapper;

	private TransactionTemplate transactionTemplate;


	@Autowired
	public void setPersMapper(PersMapper persMapper) {
		this.persMapper = persMapper;
	}

	@Autowired
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}


	@Test
	public void transactionTemplateTest() {

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				TPersInfo tPersInfo = persMapper.selectById("110506192760");
				System.out.println(tPersInfo);
				tPersInfo.setXingming("110");
				persMapper.updateById(tPersInfo);
				tPersInfo = persMapper.selectById("110506192760");
				System.out.println(tPersInfo);
				status.setRollbackOnly();
			}
		});
		TPersInfo tPersInfo = persMapper.selectById("110506192760");
		System.out.println(tPersInfo);
	}

}
