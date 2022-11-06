package com.Sandy.carrotMarket.web.dto;

import com.Sandy.carrotMarket.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostUpdateReqDto {

    private String prodName;
    private int prodCount;
    private String discount;
    private String title;
    private String content;
    private Long categoryIdx;

    @Builder
    public PostUpdateReqDto(String prodName, int prodCount, String discount, String title, String content, Long categoryIdx) {

        this.prodName = prodName;
        this.prodCount=prodCount;
        this.discount = discount;
        this.title = title;
        this.content = content;
        this.categoryIdx = categoryIdx;
    }

    public Post toEntity(){
        return Post.builder().
                prodName(prodName).prodCount(prodCount).discount(discount)
                .title(title).content(content)
                .build();
    }
}
