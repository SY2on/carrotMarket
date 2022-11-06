package com.umc3springboot.carrotMarket.web.dto;

import com.umc3springboot.carrotMarket.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostResDto {
    private String userNickName;
    private String prodName;
    private int prodCount;
    private String discount;
    private String title;
    private String content;
    private String category;

    @Builder
    public PostResDto(Post entity, String userNickName, String category) {
        this.userNickName=userNickName;
        this.category = category;
        this.prodName = entity.getProdName();
        this.prodCount=entity.getProdCount();
        this.discount = entity.getDiscount();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
