package com.funnydvd.dvdrental.cli.user.domain;

/**
 * 회원 등급 정책
 * 5000원 이상 결제시 SILVER
 * 7000원 이상 결제시 GOLD
 * 10000원 이상 결제시 VIP
 */
public class GradePolicy {

    public static final int VIP_PRICE = 10000;
    public static final int GOLD_PRICE = 7000;
    public static final int SILVER_PRICE = 5000;

    //등급 조정 기능
    public static void changeGrade(User user) {
        //총 결제액에 따른 회원 등급 조정
        int totalPaying = user.getTotalPaying();

        if (totalPaying >= VIP_PRICE) {
            user.setGrade(Grade.VIP);
        } else if (totalPaying >= GOLD_PRICE) {
            user.setGrade(Grade.SILVER);
        } else if (totalPaying >= SILVER_PRICE) {
            user.setGrade(Grade.SILVER);
        } else {
            return;
        }
    }
}
