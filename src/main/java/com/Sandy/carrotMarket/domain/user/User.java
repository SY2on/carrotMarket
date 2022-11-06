package com.Sandy.carrotMarket.domain.user;

import com.Sandy.carrotMarket.domain.BaseTimeEntity;
import com.Sandy.carrotMarket.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Getter
@Entity
@NoArgsConstructor
@Table(name="User")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userIdx")
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @ColumnDefault("default.jpg")
    private String imgUrl;

    @ColumnDefault("36")
    private int mannerTemp;

    @ColumnDefault("50")
    private int retradeHope;

    @ColumnDefault("50")
    private int responseRate;

    @ColumnDefault("ACTIVE")
    private String status;

    @OneToMany(mappedBy = "user")
    private List<Post> posts =new ArrayList<>();

    @Builder
    public User(String nickName, String imgUrl){
        this.nickName = nickName;
        this.imgUrl = imgUrl;
    }


}
