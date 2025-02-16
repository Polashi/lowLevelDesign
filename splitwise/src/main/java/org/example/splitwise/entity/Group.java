package org.example.splitwise.entity;

import lombok.Data;
import java.util.List;

@Data
public class Group extends BaseModel{
    private String name;
    private String description;
    private List<User> memberList;
}
