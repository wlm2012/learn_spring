package com.test.study.util.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ProdBatJobQry
 */
public class ProdBatJobQry {

    public static void main(String[] args) throws SQLException, InterruptedException {
        // test();
        test1("RunOnce_7c1fc231447447b7b5ccb46bdaaf460e");
    }

    public static void test() throws SQLException {
        String sql = "select j.stat as stat,j.job_id,j.brch_id,j.task_id,s.desc as task_desc,trim(j.run_cyc) as run_cycle,"
                + "j.inst_date,j.ctrl_flag,j.delay_time, s.run_cyc ,j.beg_time,j.end_time,j.comp_deg, j.handle_time,j.node_no,j.task_pid,jc.task_c_id,"
                + "c.task_c_desc,jc.stat as jc_stat,jc.sn,jc.busin_flow_num  FROM t_job_c jc LEFT JOIN t_job j ON jc.JOB_ID = j.JOB_ID "
                + "LEFT JOIN t_cust s ON jc.TASK_ID = s.TASK_ID AND jc.BRCH_ID=s.BRCH_ID LEFT JOIN t_cust_c c ON jc.TASK_ID = c.TASK_ID"
                + " AND jc.BRCH_ID = c.BRCH_ID AND jc.TASK_C_ID = c.TASK_C_ID where j.brch_id = '370200000000' and (j.stat = '2') "
                + "and jc.stat in('0','1','2','3','4') order by j.beg_time desc";

        // System.out.println(sql);

        try (Connection con = DriverManager.getConnection("jdbc:db2://172.17.17.36:50000/serspdb", "qdgjj", "qdzfzj");
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql);) {
            while (rs.next()) {
                String task_desc = rs.getString("task_desc");
                String task_c_id = rs.getString("task_c_id");
                String busin_flow_num = rs.getString("busin_flow_num");
                System.out.printf("%-15s%-5s%s", task_desc, task_c_id, busin_flow_num);
            }
        }

    }

    public static void test1(String busin) throws SQLException, InterruptedException {
        String sql = "select j.stat as stat,j.job_id,j.brch_id,j.task_id,s.desc as task_desc,trim(j.run_cyc) as run_cycle,"
                + "j.inst_date,j.ctrl_flag,j.delay_time, s.run_cyc ,j.beg_time,j.end_time,j.comp_deg, j.handle_time,j.node_no,j.task_pid,jc.task_c_id,"
                + "c.task_c_desc,jc.stat as jc_stat,jc.sn,jc.busin_flow_num  FROM t_job_c jc LEFT JOIN t_job j ON jc.JOB_ID = j.JOB_ID "
                + "LEFT JOIN t_cust s ON jc.TASK_ID = s.TASK_ID AND jc.BRCH_ID=s.BRCH_ID LEFT JOIN t_cust_c c ON jc.TASK_ID = c.TASK_ID"
                + " AND jc.BRCH_ID = c.BRCH_ID AND jc.TASK_C_ID = c.TASK_C_ID where j.brch_id = '370200000000' and (j.stat = '2') "
                + "and jc.stat in('0','1','2','3','4') order by j.beg_time desc";

        // System.out.println(sql);



        try (Connection con = DriverManager.getConnection("jdbc:db2://172.17.17.36:50000/serspdb", "qdgjj", "qdzfzj");
                Statement statement = con.createStatement();) {
            while (true) {
                boolean flag = false;
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    String busin_flow_num = rs.getString("busin_flow_num").trim();
                    if (busin.equals(busin_flow_num)) {
                        flag = true;
                    }
                }
   
                System.out.println(flag);
                if (!flag) {
                    break;
                }
                Thread.sleep(60 * 1000);
            }
        }

    }

}