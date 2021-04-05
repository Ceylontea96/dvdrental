package com.funnydvd.dvdrental.cli.movie.domain;

import java.time.LocalDate;

/**
 * 요금정책
 * 1. 올해 출시된 영화는 기본적으로 5000원을 받음
 * 2. 출시 후 1년이 지날 때마다 500원씩 할인한다.
 * 3. 최소 요금은 2000원이며 그 밑으로는 할인되지 않는다.
 */
public class ChargePolicy {

    //요금 관련 상수
    public static final int BASE_CHARGE = 5000;
    public static final int DISCOUNT= 500;
    public static final int LIMIT_CHARGE= 2000;


    //영화 요금을 계산해서 리턴하는 기능
    public static int calculateDvdCharge(int pubYear) {
        //올해 연도 정보
        int thisYear = LocalDate.now().getYear();
        //출시 후 경과년도 계산
        int movieAge = thisYear - pubYear;

        //요금 계산
        int charge = BASE_CHARGE - (movieAge * DISCOUNT);
        if( charge < LIMIT_CHARGE) {
            return LIMIT_CHARGE;
        }

        return charge;
    }

}
