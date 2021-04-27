package com.funnydvd.dvdrental.cli.order.repository;

import com.funnydvd.dvdrental.cli.order.domain.Order;

public class JdbcOrderRepository implements OrderRepository{

    //데이터베이스 연결 접속정보
    private String id = "java_web2";
    private String pw = "202104";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //db서버 위치 : 1521
    private String driverClassName = "oracle.jdbc.driver.OracleDriver"; //오라클 연동클래스 이름


    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public void returnOrder(Order order) {

    }
}
