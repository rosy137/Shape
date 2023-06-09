package com.itwill.shape.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.shape.domain.PostInfo;
import com.itwill.shape.dto.PostCreateDto;
import com.itwill.shape.dto.PostDetailDto;
import com.itwill.shape.dto.PostInfoSelectByAuthorDto;
import com.itwill.shape.dto.PostListDto;
import com.itwill.shape.dto.PostUpdateDto;
import com.itwill.shape.repository.PostCommentRepository;
import com.itwill.shape.repository.PostInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostInfoService {
	
	private final PostInfoRepository postInfoRepository;
	private final PostCommentRepository postCommentRepository;


	/**
	 * 0608 손창민
	 * post_info table에서 pcid와 일치하는 작성글 삭제
	 * @param id
	 * @return
	 */
	public int deleteByPid(long pid) {
//		log.info("author={}", author);
//		log.info("content={}", title);
		
		return postInfoRepository.deleteByPid(pid);
	}
	
	/**
	 * 0601 손창민
	 * post_info table에서 author와 일치하는 작성글 불러오기
	 * @param id
	 * @return
	 */
	public List<PostInfoSelectByAuthorDto> selectByAuthor(String author) {
		log.info("selectById()");
		log.info("author={}", author);
		
		List<PostInfo> entity = postInfoRepository.selectByAuthor(author);
		log.info("entity={}", entity);
		
		// PostInfo 타입의 객체를 PostInfoSelectByIdDTO 타입 객체로
		// 리포지토리 계층의 메서드를 호출 - DB selectById
		return entity.stream().map(PostInfoSelectByAuthorDto::fromEntity).toList();
	}
	
	/**
	 * 0603 지현
	 * 목록 키워드로 불러오기
	 * @return
	 */
	public List<PostListDto> read(String keyword){
		log.info("read(keyword={})",keyword);
		return postInfoRepository.selectWithKeyword(keyword);
	}
	
	public List<PostListDto> read(){
		log.info("read()");
		return postInfoRepository.selectWithCommentCount();
	}
	
	/**
	 * 상세보기 페이지
	 * @param pid
	 * @return
	 */
	public PostDetailDto read(long pid) {
		log.info("read(pid={})",pid);
		
		PostInfo entity = postInfoRepository.selectByPid(pid);
		PostDetailDto dto = PostDetailDto.fromEntity(entity);
		
		long count = postCommentRepository.selectCommentCountWithPid(pid);
		dto.setCommentCount(count);
		return dto;
	}
	
	/**
	 * 새 포스트 작성
	 * @param dto
	 * @return
	 */
	public int create(PostCreateDto dto) {
		log.info("create({})", dto);
		return postInfoRepository.insert(dto.toEntity());
	}
	
	public int viewCount(long pid) {
		log.info("viewCount({})", pid);
		return postInfoRepository.viewCount(pid);
	}
	
	/**
	 * 포스트 업데이트
	 * @param post
	 * @return
	 */
	public int update(PostUpdateDto post) {
		log.info("update({})", post);
		return postInfoRepository.updateTitleAndContent(post.toEntity());
	}
	
	public int delete(long pid) {
		log.info("delete(pid={})", pid);
		return postInfoRepository.deleteByPid(pid);
	}
}
