package com.funnydvd.dvdrental.cli.user.domaain;

import com.funnydvd.dvdrental.cli.order.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class User {

    private static int sequnce; // 회원 순차번호

    private int userNumber; //identifiter (식별자)

    private String userName; //회원명
    private String phoneNumber; //전화번호
    private int totalPaying; // 누적결제액
    private Grade grade; //회원등급
    //현재 대여중인 목록(영화시리얼넘버, 주문)
    private static Map<Integer, Order> orderMap = new HashMap<>();

    public User(String userName, String phoneNumber) {
        this.userNumber = ++sequnce;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.grade = Grade.BRONZE;
    }

    //대여 주문 추가 기능
    public void addOrder(Order order) {
        orderMap.put(order.getMovie().getSerialNumber(), order);
    }
    //DVD반납시 대여 주문 제거
    public Order removeOrder(int serialNumber) {
        return orderMap.remove(serialNumber);
    }
    //영화 시리얼번호로 특정 대여 주문 정보 얻기
    public Order getOrder(int serialNumber) {
        return orderMap.get(serialNumber);
    }

    //회원의 전체 대여중인 주문 정보 얻기
    public static Map<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTotalPaying() {
        return totalPaying;
    }

    public void setTotalPaying(int charge) {
        this.totalPaying += charge;
        GradePolicy.changeGrade(this);
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNumber=" + userNumber +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", totalPaying=" + totalPaying +
                ", grade=" + grade +
                '}';
    }
}
