package com.umc3springboot.carrotMarket.web.controller;

import com.umc3springboot.carrotMarket.config.BaseException;
import com.umc3springboot.carrotMarket.config.BaseResponse;
import com.umc3springboot.carrotMarket.service.PostService;
import com.umc3springboot.carrotMarket.web.dto.PostSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public BaseResponse<String> saveDepartment( @RequestBody PostSaveReqDto postSaveReqDto){
        try {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$"+postSaveReqDto.getProdCount());
            Long postId = postService.save(postSaveReqDto);
            String result = "post가 저장되었습니다. post id = "+postId ;
            return new BaseResponse<>(result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
