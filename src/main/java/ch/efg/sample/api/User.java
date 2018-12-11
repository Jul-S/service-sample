package ch.efg.sample.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class User implements IUser {
    private String id;
    private String name;
    private String groupId;
}
