package com.umc3springboot.carrotMarket.service;

import com.umc3springboot.carrotMarket.config.BaseException;
import com.umc3springboot.carrotMarket.domain.category.Category;
import com.umc3springboot.carrotMarket.domain.category.CategoryRepository;
import com.umc3springboot.carrotMarket.domain.post.Post;
import com.umc3springboot.carrotMarket.domain.post.PostRepository;
import com.umc3springboot.carrotMarket.domain.user.User;
import com.umc3springboot.carrotMarket.domain.user.UserRepository;
import com.umc3springboot.carrotMarket.web.dto.PostSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.umc3springboot.carrotMarket.config.BaseResponseStatus.DATABASE_ERROR;


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
}
