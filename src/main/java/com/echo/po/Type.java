package com.echo.po;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类类
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
}
