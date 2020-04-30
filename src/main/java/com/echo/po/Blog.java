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
 * 博客类
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String firstPicture;//首图
    private String flag;//原创or转载
    private Integer views;//浏览次数
    private boolean appreciation;//赞赏
    private boolean shareStatement;//版权
    private boolean commentabled;//评论
    private boolean pulished;//发布
    private boolean recommend;//推荐
    private String description;//描述
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ToString.Exclude
    @ManyToOne
    private Type type;
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();
    @ToString.Exclude
    @ManyToOne
    private User user;
    @ToString.Exclude
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
    @ToString.Exclude
    @Transient
    private String tagIds;

    public void init(){
        this.tagIds=getTagIds(this.getTags());
    }
    private String getTagIds(List<Tag> list){
        if(!list.isEmpty()){
            StringBuffer sb = new StringBuffer();
            boolean tf = false;
            for(Tag tag : list){
                if (tf){
                    sb.append(",");
                }else {
                    tf = true;
                }
                sb.append(tag.getId());
            }
            return sb.toString();
        }
        return null;
    }
}
