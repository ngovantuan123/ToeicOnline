package vn.myclass.core.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleid;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "roleEntity",fetch = FetchType.LAZY)
    private List<UserEntity> listUserEntity;


}
