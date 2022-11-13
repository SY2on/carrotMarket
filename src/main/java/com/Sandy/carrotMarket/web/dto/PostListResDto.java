package com.Sandy.carrotMarket.web.dto;

import com.Sandy.carrotMarket.domain.category.Category;
import com.Sandy.carrotMarket.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostListResDto {
    private String title;
    private String prodName;
    private int prodCount;
    private LocalDateTime updatedAt;
    private String userNickname;
    private String category;

    public PostListResDto(Post entity){
        this.title = entity.getTitle();
        this.prodName=entity.getProdName();
        this.prodCount= entity.getProdCount();
        this.updatedAt=entity.getUpdatedAt();
        this.userNickname = entity.getUser().getNickName();
        this.category = entity.getCategory().getName();
    }

}
