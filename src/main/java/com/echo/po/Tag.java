package com.echo.po;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签类
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
    private List<Blog> blogs = new ArrayList<>();
}
