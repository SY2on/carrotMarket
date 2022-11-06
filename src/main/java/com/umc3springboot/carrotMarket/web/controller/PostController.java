package com.umc3springboot.carrotMarket.web.controller;

import com.umc3springboot.carrotMarket.config.BaseException;
import com.umc3springboot.carrotMarket.config.BaseResponse;
import com.umc3springboot.carrotMarket.service.PostService;
import com.umc3springboot.carrotMarket.web.dto.PostResDto;
import com.umc3springboot.carrotMarket.web.dto.PostSaveReqDto;
import com.umc3springboot.carrotMarket.web.dto.PostUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/post")
public class PostController {
    private final PostService postService;

    //post 저장
    @PostMapping("")
    public BaseResponse<String> savePost( @RequestBody PostSaveReqDto postSaveReqDto){
        try {
            Long postId = postService.save(postSaveReqDto);
            String result = "post가 저장되었습니다. post id = "+postId ;
            return new BaseResponse<>(result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //post 조회 by postIdx
    @GetMapping("/{postIdx}")
    public BaseResponse<PostResDto> findById(@PathVariable("postIdx") Long postIdx){
        try{
            return new BaseResponse<>(postService.findById(postIdx));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //post 수정
    @PutMapping("{postIdx}")
    public BaseResponse<String> updatePost(@PathVariable("postIdx") Long postIdx, @RequestBody PostUpdateReqDto postUpdateReqDto){
        try{
            String result = "post가 수정되었습니다. post id="+postService.update(postIdx, postUpdateReqDto);
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
