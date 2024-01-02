package umc.hackathon.chagok.converter;

import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.web.dto.PostResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {

    public static PostResponse.MyPostPreviewDTO myPostPreviewDTO(Member member, Post post){
        return PostResponse.MyPostPreviewDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .categoryId(post.getCategory().getId())
                //.tagList(post.getTagList())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static PostResponse.MyPostPreviewListDTO myPostPreviewListDTO(List<Post> postList){

        List<PostResponse.MyPostPreviewDTO> myPostPreviewDTOList = postList.stream()
                .map(post -> myPostPreviewDTO(post.getMember(), post)).collect(Collectors.toList());

        return PostResponse.MyPostPreviewListDTO.builder()
                .myPostPreviewList(myPostPreviewDTOList)
                .build();
    }

    public static PostResponse.PostPreviewDTO postPreviewDTO(Member member, Post post){
        return PostResponse.PostPreviewDTO.builder()
                .ownerNickName(member.getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .categoryId(post.getCategory().getId())
                //.tagList(post.getTagList())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static PostResponse.PostPreviewListDTO postPreviewListDTO(List<Post> postList){

        List<PostResponse.PostPreviewDTO> postPreviewDTOList = postList.stream()
                .map(post -> postPreviewDTO(post.getMember(), post)).collect(Collectors.toList());

        return PostResponse.PostPreviewListDTO.builder()
                .postPreviewList(postPreviewDTOList)
                .build();
    }
}
