package com.echo.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论类
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createTime;

    @ToString.Exclude
    @ManyToOne
    private User user;
    @ToString.Exclude
    @ManyToOne
    private Blog blog;
    @ToString.Exclude
    @ManyToOne
    private Comment parentComment;
    @ToString.Exclude
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replayComments = new ArrayList<>();
}
