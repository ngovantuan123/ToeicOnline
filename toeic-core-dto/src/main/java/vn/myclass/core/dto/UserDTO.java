package vn.myclass.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
public class UserDTO implements Serializable {
    private Integer userId;
    private String name;
    private String passWord;
    private String fullName;
    private Timestamp createdDate;
    private RoleDTO roleDTO;
}
