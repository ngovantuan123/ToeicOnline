package vn.myclass.core.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "listenguideline")
@Getter
@Setter
public class ListenGuidelineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listenGuidelineId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;
    @OneToMany(mappedBy = "listenGuidelineEntity",fetch = FetchType.LAZY)
    private List<CommentEntity> listCommentEntity;
}
