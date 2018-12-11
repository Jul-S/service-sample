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

    //saveAll works both as UPDATE or INSERT operation
    //depending on user id presence
    public <S extends User> List<S> saveAll(Iterable<S> users) {
        users.forEach(user ->
                userData.stream().filter(x -> x.getId().equals(user.getId())).findFirst().ifPresent(userData::remove)
        );
        List<S> list = Lists.newArrayList(users);
        userData.addAll(list);
        return list;
    }

    //save works both as UPDATE or INSERT operation
    //depending on user id presence
    public <S extends User> S save(S user) {
        userData.stream().filter(x -> x.getId().equals(user.getId())).findFirst().ifPresent(userData::remove);
        userData.add(user);
        return user;
    }

    public User delete(String id) {
        User user = userData.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("No user found with Id=" + id));
        userData.remove(user);
        return user;
    }

    public Map<String, List<User>> findAllGroupByGroupId() {
        return userData.stream()
                .collect(Collectors.groupingBy(User::getGroupId, Collectors.toList()));
    }
}
