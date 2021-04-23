package com.funnydvd.dvdrental.jdbc;

import com.funnydvd.dvdrental.study.jdbc.JdbcBasic;
import com.funnydvd.dvdrental.study.jdbc.JdbcPractice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DbConnectTest {

    //데이터베이스 연걸 접속정보
    private String id = "sqld";
    private String pw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //db서버 위치 : 1521
    private String driverClassName = "oracle.jdbc.driver.OracleDriver"; //오라클 연동클래스 이름


    @Test
    @DisplayName("데이터베이스 연결에 성공해야 한다.")
    void connectionTest() {

        try {
            //오라클 드라이버 클래스 로딩
            Class.forName(driverClassName);

            //연결정보 객체 생성 (Connection)
            Connection conn = DriverManager.getConnection(url, id, pw);
            System.out.println("연결 성공!");

        } catch (Exception e) {
            System.out.println("연결 실패!");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("테이블에 저장을 성공적으로 수행해야 한다.")
    void insertTest() {

        JdbcBasic jb = new JdbcBasic();
        boolean result = jb.save(new JdbcPractice(2, "도우너", "서울시 종로구 어딘가"));

        //단언
        Assertions.assertTrue(result); //result가 true일 것이라고 단언한다.
    }

    @Test
    @DisplayName("테이블에 수정을 성공적으로 수행해야 한다.")
    void updateTest() {

        JdbcBasic jb = new JdbcBasic();
        boolean result = jb.modify(new JdbcPractice(2, "진짜 고길동", "서울시 종로구"));

        //단언
        Assertions.assertTrue(result); //result가 true일 것이라고 단언한다.
    }

    @Test
    @DisplayName("테이블에서 삭제를 성공적으로 수행해야 한다.")
    void deleteTest() {

        JdbcBasic jb = new JdbcBasic();
        boolean result = jb.remove(2);

        //단언
        Assertions.assertTrue(result); //result가 true일 것이라고 단언한다.
    }

    @Test
    @DisplayName("전체 행을 조회해야 한다.")
    void SelectAllTest() {

        JdbcBasic jb = new JdbcBasic();
        List<JdbcPractice> all = jb.findAll();

        // 리스트의 사이즈(행의 수)가 3이라고 단언한다.
        Assertions.assertTrue(all.size() == 3);

        for (JdbcPractice jdbcPractice : all) {
            System.out.println(jdbcPractice);
        }

    }


    @Test
    @DisplayName("특정 행을 조회해야 한다.")
    void SelectOneTest() {

        JdbcBasic jb = new JdbcBasic();
        JdbcPractice findOne = jb.findOne(3);
        System.out.println(findOne);

    }

    @Test
    @DisplayName("테이블의 행 수를 조회해야 한다.")
    void CountTest() {

        JdbcBasic jb = new JdbcBasic();
        int cnt = jb.count();
        System.out.println("cnt = " + cnt);
    }


}

