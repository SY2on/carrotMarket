package com.Sandy.carrotMarket.web.controller;

import com.Sandy.carrotMarket.config.BaseException;
import com.Sandy.carrotMarket.config.BaseResponse;
import com.Sandy.carrotMarket.service.PostService;
import com.Sandy.carrotMarket.web.dto.PostListResDto;
import com.Sandy.carrotMarket.web.dto.PostResDto;
import com.Sandy.carrotMarket.web.dto.PostSaveReqDto;
import com.Sandy.carrotMarket.web.dto.PostUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //post list 조회 by userIdx
    @GetMapping("")
    public BaseResponse<List<PostListResDto>> findByUserId(@RequestParam(required = false) Long userIdx, @RequestParam(required = false) Long categoryIdx){
        try{
            System.out.println("#33"+categoryIdx);
            if(userIdx==null&&categoryIdx==null)
                return new BaseResponse<>(postService.findAll());
            else if(userIdx==null) //categoryIdx 파라미터 들어옴
                return new BaseResponse<>(postService.findByCategory(categoryIdx));
            else if (categoryIdx==null) //userIdx 파라미터 들어옴
                return new BaseResponse<>(postService.findByUserId(userIdx));
            else
                return new BaseResponse<>(postService.findByUserIdAndCategory(userIdx, categoryIdx));

        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //post 수정
    @PutMapping("/{postIdx}")
    public BaseResponse<String> updatePost(@PathVariable("postIdx") Long postIdx, @RequestBody PostUpdateReqDto postUpdateReqDto){
        try{
            String result = "post가 수정되었습니다. post id="+postService.update(postIdx, postUpdateReqDto);
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //post 삭제
    @DeleteMapping("/{postIdx}")
    public BaseResponse<String> deletePost(@PathVariable("postIdx") Long postIdx){
        try{
            String result = "post가 삭제되었습니다. post id="+postService.delete(postIdx);
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
