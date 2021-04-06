package com.funnydvd.dvdrental.cli.user.repository;

import com.funnydvd.dvdrental.cli.user.domaain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {

    private static final Map<Integer, User> userDatabase = new HashMap<>();

    static {
        User user1 = new User("김철수", "010-1234-5678");
        User user2 = new User("김철수", "010-2312-5328");
        User user3 = new User("김철수", "010-1347-3456");
        userDatabase.put(user1.getUserNumber(), user1);
        userDatabase.put(user2.getUserNumber(), user2);
        userDatabase.put(user3.getUserNumber(), user3);
    }



    @Override
    public void addUser(User user) {
        userDatabase.put(user.getUserNumber(), user);
    }

    @Override
    public List<User> findAllByName(String userName) {
        List<User> findUserList = new ArrayList<>();

        for (Integer key : userDatabase.keySet()) {
            User user = userDatabase.get(key);
            if (user.getUserName().equals(userName)) {
                findUserList.add(user);
            }
        }
        return findUserList;
    }

    @Override
    public User findByUserNumber(int userNumber) {
        return userDatabase.get(userNumber);
    }

    @Override
    public User deleteUser(int userNumber) {
        return userDatabase.remove(userNumber);
    }
}
