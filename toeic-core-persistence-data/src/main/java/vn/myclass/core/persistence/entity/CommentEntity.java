package vn.myclass.core.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@Setter
@Getter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @Column(name = "content")
    private String content;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "listenguidelineid")
    private ListenGuidelineEntity listenGuidelineEntity;
}
