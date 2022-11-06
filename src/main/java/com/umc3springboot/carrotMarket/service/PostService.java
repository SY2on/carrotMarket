package com.umc3springboot.carrotMarket.service;

import com.umc3springboot.carrotMarket.config.BaseException;
import com.umc3springboot.carrotMarket.domain.category.Category;
import com.umc3springboot.carrotMarket.domain.category.CategoryRepository;
import com.umc3springboot.carrotMarket.domain.post.Post;
import com.umc3springboot.carrotMarket.domain.post.PostRepository;
import com.umc3springboot.carrotMarket.domain.user.User;
import com.umc3springboot.carrotMarket.domain.user.UserRepository;
import com.umc3springboot.carrotMarket.web.dto.PostListResDto;
import com.umc3springboot.carrotMarket.web.dto.PostResDto;
import com.umc3springboot.carrotMarket.web.dto.PostSaveReqDto;
import com.umc3springboot.carrotMarket.web.dto.PostUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.umc3springboot.carrotMarket.config.BaseResponseStatus.*;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostSaveReqDto postSaveReqDto) throws BaseException {
        try {
            Post post = postSaveReqDto.toEntity();
            User user = userRepository.getReferenceById(postSaveReqDto.getUserIdx());
            Category category = categoryRepository.getReferenceById(postSaveReqDto.getCategoryIdx());
            post.setCategory(category);
            post.setUser(user);
            return postRepository.save(post).getId();
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public PostResDto findById(Long postIdx) throws BaseException{

        Post post = postRepository.findById(postIdx)
                .orElseThrow(()-> new BaseException(NOT_FOUND_POST));

        String userNickName = post.getUser().getNickName();
        String category = post.getCategory().getName();

        return PostResDto.builder().
                entity(post).userNickName(userNickName).category(category)
                .build();
    }

    @Transactional
    public List<PostListResDto> findByUserId(Long userIdx) throws BaseException{

        User user = userRepository.findById(userIdx)
                .orElseThrow(()-> new BaseException(NOT_FOUND_USER));
        List<Post> postList = user.getPosts();

        ArrayList<PostListResDto> postListDtoList = new ArrayList<>() ;
        for(Post post : postList){
            postListDtoList.add(new PostListResDto(post));
        }

        return postListDtoList;
    }
    @Transactional
    public Long update(Long postIdx, PostUpdateReqDto postUpdateReqDto) throws BaseException{
        Post post = postRepository.findById(postIdx)
                .orElseThrow(()-> new BaseException(NOT_FOUND_POST));

        Post updatePost = postUpdateReqDto.toEntity();
        Category updateCategory = categoryRepository.getReferenceById(postUpdateReqDto.getCategoryIdx());
        updatePost.setCategory(updateCategory);

        post.update(updatePost);

        return post.getId();
    }

    @Transactional
    public  Long delete(Long postIdx) throws BaseException{
        Post post = postRepository.findById(postIdx)
                .orElseThrow(()-> new BaseException(NOT_FOUND_POST));

        postRepository.delete(post);
        return post.getId();
    }
}
