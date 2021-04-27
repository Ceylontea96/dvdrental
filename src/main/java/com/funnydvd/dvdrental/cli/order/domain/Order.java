package com.funnydvd.dvdrental.cli.order.domain;

import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.user.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Order {

    private static int sequence;

    private int orderNumber; //주문번호
    private User user; //주문자 정보
    private Movie movie; //대여 영화 정보
    private LocalDate orderDate; //대여 날짜
    private LocalDate returnDate; //반납 예정 날짜
    private int overdueDay; //연체일수
    private int overdueCharge; //연체요금
    private OrderStatus orderStatus; //대여 상태 (대여중, 반납완료)

    public Order(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.orderNumber = ++sequence;
        this.orderDate = LocalDate.now();
        this.returnDate = orderDate.plusDays(3);
        this.orderStatus = OrderStatus.ORDERED;
    }

    //DB조회데이터 처리 생성자
    public Order(ResultSet rs) throws SQLException {
        this.orderNumber = rs.getInt("");
    }


    @Override
    public String toString() {

        return
                "## 대여영화번호: " + movie.getSerialNumber() +
                        ", 대여영화명: " + movie.getMovieName() +
                        ", 회원명: " + user.getUserName() + "(" + user.getPhoneNumber() + ")" +
                        ", 대여일자: " + orderDate +
                        ", 반납일자: " + returnDate +
                        ", 연체금액(" + overdueDay + "일): "  + overdueCharge + "원"
                ;
    }

    //반납 시점에 연체료 계산
    public void checkOverdue() {
        this.overdueDay = OverduePolicy.calcOverdueDay(this.returnDate);
        this.overdueCharge = OverduePolicy.calcOverdueCharge(this.returnDate);
    }

    //getter 및 setter
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(int overdueday) {
        this.overdueDay = overdueday;
    }

    public int getOverdueCharge() {
        return overdueCharge;
    }

    public void setOverdueCharge(int overdueCharge) {
        this.overdueCharge = overdueCharge;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
