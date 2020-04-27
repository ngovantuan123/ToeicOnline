package vn.myclass.core.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "userEntity")
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String passWord;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @ManyToOne
    @JoinColumn(name = "roleid")
    private RoleEntity roleEntity;
    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY)
    private List<CommentEntity> listCommentEntity;


}
