package com.funnydvd.dvdrental.cli.order.repository;

import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.order.domain.Order;
import com.funnydvd.dvdrental.cli.order.domain.OrderStatus;
import com.funnydvd.dvdrental.cli.user.domain.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryOrderRepository implements OrderRepository {

    private static final Map<Integer, Order> orderDatabase = new HashMap<>();




    @Override
    public void saveOrder(Order order) {
        Movie retalMovie = order.getMovie();
        User rentalUser = order.getUser();

        //영화 연계정보 처리
        retalMovie.setRental(true);
        retalMovie.setRentalUser(rentalUser);

        //회원 연계정보 처리
        rentalUser.setTotalPaying(retalMovie.getCharge());
        rentalUser.addOrder(order);

        orderDatabase.get(order.getOrderNumber()).setOrderStatus(OrderStatus.ORDERED);
        orderDatabase.put(order.getOrderNumber(), order);
    }

    @Override
    public void returnOrder(Order order) {
        Movie returnMovie = order.getMovie();
        User returnUser = order.getUser();

        //반납영화 연계정보 처리
        returnMovie.setRental(false);
        returnMovie.setRentalUser(null);

        //반납회원 연계정보 처리
        returnUser.removeOrder(returnMovie.getSerialNumber());

        orderDatabase.get(order.getOrderNumber()).setOrderStatus(OrderStatus.RETURNED);
    }
}
