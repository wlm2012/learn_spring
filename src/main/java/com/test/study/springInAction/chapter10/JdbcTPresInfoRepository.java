package com.test.study.springInAction.chapter10;


import com.test.study.entity.TPersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTPresInfoRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcTPresInfoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}


	public Iterable<TPersInfo> findAll() {
		return jdbc.query("select * from t_pers_info", this::mapRowTPersInfo);
	}

	public TPersInfo findOne(String grzh) {
		return jdbc.queryForObject("select * from t_pers_info where grzh = ?", this::mapRowTPersInfo, grzh);
	}

	public void insertTPersInfo(TPersInfo tPersInfo) {
		jdbc.update("INSERT INTO t_pers_info (`GRZH`, `XINGMING` ) VALUES(?,?)", tPersInfo.getGrzh(), tPersInfo.getXingming());
	}


	private TPersInfo mapRowTPersInfo(ResultSet resultSet, int rowNum) throws SQLException {
		return new TPersInfo(
				resultSet.getString("grzh"),
				resultSet.getString("xingming"),
				resultSet.getString("xmqp"),
				resultSet.getString("xingbie"),
				resultSet.getString("gddhhm"),
				resultSet.getString("sjhm"),
				resultSet.getString("zjlx"),
				resultSet.getString("zjhm"),
				resultSet.getString("csny"),
				resultSet.getString("hyzk"),
				resultSet.getString("zhiye"),
				resultSet.getString("zhichen"),
				resultSet.getString("zhiwu"),
				resultSet.getString("xueli"),
				resultSet.getString("yzbm"),
				resultSet.getString("jtzz"),
				resultSet.getBigDecimal("jtysr"),
				resultSet.getDate("csrq"),
				resultSet.getString("email"),
				resultSet.getString("mz"),
				resultSet.getString("beiz"),
				resultSet.getDate("djrq"),
				resultSet.getString("djgy"),
				resultSet.getString("khjg"),
				resultSet.getString("last_update_time")

		);
	}

}
