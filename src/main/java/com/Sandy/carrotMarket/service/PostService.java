package com.Sandy.carrotMarket.service;

import com.Sandy.carrotMarket.config.BaseException;
import com.Sandy.carrotMarket.domain.category.Category;
import com.Sandy.carrotMarket.domain.category.CategoryRepository;
import com.Sandy.carrotMarket.domain.post.Post;
import com.Sandy.carrotMarket.domain.post.PostRepository;
import com.Sandy.carrotMarket.domain.user.User;
import com.Sandy.carrotMarket.domain.user.UserRepository;
import com.Sandy.carrotMarket.web.dto.PostListResDto;
import com.Sandy.carrotMarket.web.dto.PostResDto;
import com.Sandy.carrotMarket.web.dto.PostSaveReqDto;
import com.Sandy.carrotMarket.web.dto.PostUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.Sandy.carrotMarket.config.BaseResponseStatus.*;


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
    public List<PostListResDto> findAll() throws BaseException{

        List<Post> postList = postRepository.findAll();
        if(postList.size()==0)
            throw new BaseException(NOT_FOUND_ANY_POST);

        ArrayList<PostListResDto> postListDtoList = new ArrayList<>() ;
        for(Post post : postList){
            postListDtoList.add(new PostListResDto(post));
        }

        return postListDtoList;
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
    public List<PostListResDto> findByCategory(Long categoryIdx) throws BaseException{

        Category category = categoryRepository.findById(categoryIdx)
                .orElseThrow(()->new BaseException(NOT_FOUND_CATEGORY));
        List<Post> postList = category.getPosts();

        ArrayList<PostListResDto> postListDtoList = new ArrayList<>() ;
        for(Post post : postList){
            postListDtoList.add(new PostListResDto(post));
        }

        return postListDtoList;
    }

    @Transactional
    public List<PostListResDto> findByUserIdAndCategory(Long userIdx,Long categoryIdx) throws BaseException{


        List<Post> postList = postRepository.findAllByUserIdAndCategoryId(userIdx, categoryIdx);

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
