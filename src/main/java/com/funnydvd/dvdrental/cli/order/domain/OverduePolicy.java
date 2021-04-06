package com.funnydvd.dvdrental.cli.order.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 연체 정책
 * 1. 반납 예정일로부터 1일 연체당 300원씩 연체료 징수
 */
public class OverduePolicy {

    public static final int BASE_OVERDUE_CHARGE = 300;

    //연체 일수 계산
    public static int calcOverdueDay(LocalDate returnDate) {
        LocalDate now = LocalDate.now();

        if (returnDate.isBefore(now)) {
            return (int) ChronoUnit.DAYS.between(returnDate, now);
        }
        return 0;
    }

    //연체료 계산
    public static int calcOverdueCharge(LocalDate returnDate) {
        return calcOverdueDay(returnDate) * BASE_OVERDUE_CHARGE;
    }
}
