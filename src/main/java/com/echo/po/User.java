package com.echo.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户类
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
