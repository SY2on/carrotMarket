package com.Sandy.carrotMarket.domain.post;

import com.Sandy.carrotMarket.domain.BaseTimeEntity;
import com.Sandy.carrotMarket.domain.category.Category;
import com.Sandy.carrotMarket.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@DynamicInsert
@Getter
@Entity
@NoArgsConstructor
@Table(name="Post")
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="postIdx")
    private Long id;

    @Column(nullable = false)
    private String prodName;

    @Column(nullable = false)
    private int prodCount;

    @ColumnDefault("Y")
    private String discount;

    @Column(nullable = false)
    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoryIdx")
    private Category category;

    @ColumnDefault("ACTIVE")
    private String status;

    @Builder
    public Post(String prodName, int prodCount, String discount, String title, String content, Category category){
        this.prodName = prodName;
        this.prodCount = prodCount;
        this.discount = discount;
        this.title = title;
        this.content = content;
        this.category = category;
    }
    public void setCategory(Category category){
        this.category = category;
    }

    public void setUser(User user){
        this.user=user;
    }

    public void update(Post post){
        this.prodName=post.getProdName();
        this.prodCount=post.getProdCount();
        this.discount=post.getDiscount();
        this.title= post.getTitle();
        this.content=post.getContent();
        this.category=post.getCategory();
    }
}
