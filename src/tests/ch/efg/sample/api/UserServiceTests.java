package ch.efg.sample.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UserServiceTests {

    private List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("121", "John", "G011"));
        users.add(new User("122", "Bob", "G012"));
        users.add(new User("123", "Ann", "G011"));
        users.add(new User("124", "And", "G002"));
        users.add(new User("125", "One", "G001"));
        users.add(new User("126", "More", "G012"));
        return users;
    }

    @Test
    public void testFindAll() {
        List<User> users = getUsers();
        UserServiceImpl userService = new UserServiceImpl(users);
        List<User> all = userService.findAll();
        assertEquals(all, users);
        all.forEach(System.out::println);
    }

    @Test
    public void testFindById() {
        List<User> users = getUsers();
        UserServiceImpl userService = new UserServiceImpl(users);
        List<User> all = userService.findById("124");
        assertThat(all.size(), is(1));
        all.forEach(System.out::println);
    }

    @Test
    public void testSaveAllAndDelete() {
        List<User> users = getUsers();
        UserServiceImpl userService = new UserServiceImpl(users);

        List<User> addUsers = Arrays.asList(
                new User("124", "Add", "G012"),
                new User("129", "Meto", "G001")
        );

        List<User> all = userService.saveAll(addUsers);
        assertEquals(all,addUsers);
        assertThat(userService.findAll().size(), is(7));
        all.forEach(System.out::println);

        User actual = userService.delete("124");
        assertNotNull(actual);
        userService.findAll().forEach(System.out::println);
        assertThat(userService.findAll().size(), is(6));
    }

    @Test
    public void testSave() {
        List<User> users = getUsers();
        UserServiceImpl userService = new UserServiceImpl(users);
        User user1 = new User("124", "Add", "G012");
        userService.save(user1);
        assertEquals(userService.findById("124").get(0), user1);

        User user2 = new User("127", "Add", "G012");
        userService.save(user2);
        assertEquals(userService.findById("127").get(0), user2);
        assertThat(userService.findAll().size(), is(7));
        userService.findAll().forEach(System.out::println);

    }

    @Test
    public void testGroupById() {
        List<User> users = getUsers();
        UserServiceImpl userService = new UserServiceImpl(users);
        Map<String, List<User>> actual = userService.findAllGroupByGroupId();
        actual.keySet().forEach(k-> System.out.println(k + "\n" + actual.get(k)));
        assertThat(actual.keySet().size(), is(4));
    }
}
