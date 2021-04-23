package com.funnydvd.dvdrental.study.jdbc;

//db관련 패키지 : java.sql
import java.sql.*;
import java.util.*;

public class JdbcBasic {

    //데이터베이스 연걸 접속정보
    private String id = "sqld";
    private String pw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //db서버 위치 : 1521
    private String driverClassName = "oracle.jdbc.driver.OracleDriver"; //오라클 연동클래스 이름

    //INSERT문을 실행하는 메서드
    public boolean save(JdbcPractice jP) {

        Connection connection = null;

        try {
            //드라이버 클래스 로딩
            Class.forName(driverClassName);

            //연결정보 객체 생성
            connection = DriverManager.getConnection(url, this.id, pw);

            //SQL 실행
            String query = "INSERT INTO jdbc_practice VALUES (?, ?, ?)";
            //SQL 실행을 위한 PreparedStatement 객체
            PreparedStatement statement = connection.prepareStatement(query);

            //쿼리의 ?값 세팅
            statement.setInt(1, jP.getId());
            statement.setString(2, jP.getName());
            statement.setString(3, jP.getAddr());

            //SQL 실행 명령
            //SELECT : excuteQuery()
            //INSERT, UPDATE, DELETE : excuteUpdate()
            //resultNum : 성공하면 1, 실패하면 0
            int resultNum = statement.executeUpdate();
            return resultNum == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //DB접속 해제: 빈번한 데이터베이스 트랜잭션이 생길때마다
            //메모리 과부하가 걸릴 가능성이 있음.
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //UPDATE
    public boolean modify(JdbcPractice jP) {


        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        // close 해야 할 객체가 하나가 아니라면 뒤에 ;을 붙이고 추가하면 됨.
        try ( Connection connection = DriverManager.getConnection(url, this.id, pw) ) {
            //드라이버 클래스 로딩
            Class.forName(driverClassName);

            //SQL 실행
            String query = "UPDATE jdbc_practice SET name = ?, addr = ? WHERE id = ?";
            //SQL 실행을 위한 PreparedStatement 객체
            PreparedStatement statement = connection.prepareStatement(query);

            //쿼리의 ?값 세팅
            statement.setString(1, jP.getName());
            statement.setString(2, jP.getAddr());
            statement.setInt(3, jP.getId());

            //SQL 실행 명령
            //SELECT : excuteQuery()
            //INSERT, UPDATE, DELETE : excuteUpdate()
            //resultNum : 성공하면 1, 실패하면 0
            int resultNum = statement.executeUpdate();
            return resultNum == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(int id) {


        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        // close 해야 할 객체가 하나가 아니라면 뒤에 ;을 붙이고 추가하면 됨.
        try ( Connection connection = DriverManager.getConnection(url, this.id, pw) ) {
            //드라이버 클래스 로딩
            Class.forName(driverClassName);

            //SQL 실행
            String query = "DELETE FROM jdbc_practice WHERE id = ?";
            //SQL 실행을 위한 PreparedStatement 객체
            PreparedStatement statement = connection.prepareStatement(query);

            //쿼리의 ?값 세팅
            statement.setInt(1, id);

            //SQL 실행 명령
            //SELECT : excuteQuery()
            //INSERT, UPDATE, DELETE : excuteUpdate()
            //resultNum : 성공하면 1, 실패하면 0
            int resultNum = statement.executeUpdate();
            return resultNum == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //다중 행 SELECT
    public List<JdbcPractice> findAll() {

        List<JdbcPractice> resultList = new ArrayList<>();

        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        // close 해야 할 객체가 하나가 아니라면 뒤에 ;을 붙이고 추가하면 됨.
        try ( Connection connection = DriverManager.getConnection(url, this.id, pw) ) {
            //드라이버 클래스 로딩
            Class.forName(driverClassName);

            //SQL 실행
            String query = "SELECT * FROM jdbc_practice ORDER BY id";
            //SQL 실행을 위한 PreparedStatement 객체
            PreparedStatement statement = connection.prepareStatement(query);

            //SQL 실행 명령
            //SELECT : excuteQuery()
            //INSERT, UPDATE, DELETE : excuteUpdate()
            //ResultSet : SELECT 결과 2차원 테이블
            ResultSet rs = statement.executeQuery();

            //커서를 다음행으로 움직임(한 칸 아래로)
            while(rs.next()) {
                JdbcPractice jb = new JdbcPractice(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("addr"));

                resultList.add(jb);
            }

            return resultList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); //빈리스트 리턴
        }
    }



    //단일 행 SELECT
    public JdbcPractice findOne(int id) { //WHERE절에서 사용할 PK를 매개변수로 넣어줌

        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        // close 해야 할 객체가 하나가 아니라면 뒤에 ;을 붙이고 추가하면 됨.
        try ( Connection connection = DriverManager.getConnection(url, this.id, pw) ) {
            //드라이버 클래스 로딩
            Class.forName(driverClassName);

            //SQL 실행
            String query = "SELECT * FROM jdbc_practice WHERE id = ?";
            //SQL 실행을 위한 PreparedStatement 객체
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            //SQL 실행 명령
            //SELECT : excuteQuery()
            //INSERT, UPDATE, DELETE : excuteUpdate()
            //ResultSet : SELECT 결과 2차원 테이블
            ResultSet rs = statement.executeQuery();

            //커서를 다음행으로 움직임(한 칸 아래로)
            if(rs.next()) {
               return new JdbcPractice(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("addr"));
            } else {
                return null;
            }



        } catch (Exception e) {
            e.printStackTrace();
            return null; //null 리턴
        }
    }

    //테이블 행수(count) 조회 : SELECT COUNT(*) FROM jdbc_practice;
    public int count() {
        try ( Connection connection = DriverManager.getConnection(url, this.id, pw) ) {
            //드라이버 클래스 로딩
            Class.forName(driverClassName);

            //SQL 실행
            String query = "SELECT COUNT(*) AS cnt FROM jdbc_practice";
            //SQL 실행을 위한 PreparedStatement 객체
            PreparedStatement statement = connection.prepareStatement(query);

            //SQL 실행 명령
            //SELECT : excuteQuery()
            //INSERT, UPDATE, DELETE : excuteUpdate()
            //ResultSet : SELECT 결과 2차원 테이블
            ResultSet rs = statement.executeQuery();

            //커서를 다음행으로 움직임(한 칸 아래로)
            if(rs.next()) {
               return rs.getInt("cnt");
            } else {
                return -1; //이상이 있음을 보여주기 위해 -1을 리턴
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}