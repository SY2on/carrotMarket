package com.umc3springboot.carrotMarket.web.dto;

import com.umc3springboot.carrotMarket.domain.post.Post;
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

    public PostListResDto(Post entity){
        title = entity.getTitle();
        prodName=entity.getProdName();
        prodCount= entity.getProdCount();
        updatedAt=entity.getUpdatedAt();
    }

}
