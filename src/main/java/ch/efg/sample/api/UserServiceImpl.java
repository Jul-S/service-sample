package ch.efg.sample.api;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserServiceImpl implements IUserService<User, String> {

    private final List<User> userData;

    public List<User> findAll() {
        return Lists.newArrayList(userData);
    }

    public List<User> findById(String id) {
        return userData.stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
    }

    public <S extends User> List<S> saveAll(Iterable<S> users) {
        users.forEach(this::save);
        return Lists.newArrayList(users);
    }

    public <S extends User> S save(S user) {
        boolean added = userData.add(user);
        return added ? user : null;
    }

    public User delete(String id) {
        User user = userData.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        boolean removed = (user != null) && userData.remove(user);
        return removed ? user : null;
    }

    public Map<String, List<User>> findAllGroupByGroupId() {
        return userData.stream()
                .collect(Collectors.groupingBy(User::getGroupId, Collectors.toList()));
    }
}
