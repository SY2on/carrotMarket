package com.Sandy.carrotMarket.web.dto;

import com.Sandy.carrotMarket.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostSaveReqDto {
    private Long userIdx;
    private String prodName;
    private int prodCount;
    private String discount;
    private String title;
    private String content;
    private Long categoryIdx;

    @Builder
    public PostSaveReqDto(Long userIdx,String prodName, int prodCount, String discount, String title, String content, Long categoryIdx) {
        this.userIdx=userIdx;
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
