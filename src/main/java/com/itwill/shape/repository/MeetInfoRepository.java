package com.itwill.shape.repository;

import java.util.List;

import com.itwill.shape.domain.MeetInfo;
import com.itwill.shape.dto.MeetInfoPrtcpLikeSelectByPrtcpIdDto;
import com.itwill.shape.dto.MeetListCountDto;
import com.itwill.shape.dto.MeetMainDetailDto;

// meet_info의 repository.
public interface MeetInfoRepository {

	int insert(MeetInfo entity); // create 모임 만들기
	int update(MeetInfo entity); // update 모임 수정하기
	int deleteByMtid(long mtid); // delete 모임 삭제하기
	MeetInfo selectByMtid(long mtid); // modify
	int delete(int i);
	
	//마이페이지
	/**
	 * 0610 손창민
	 * 내가 참여 중인 모임 목록 불러오기
	 * @param prtcpId
	 * @return
	 */
	List<MeetInfoPrtcpLikeSelectByPrtcpIdDto> selectByPrtcpId(String prtcpId);
	
	/**
	 * 0610 손창민
	 * 내가 개설한 모임 목록 불러오기
	 * @param crtrId
	 * @return
	 */
	List<MeetInfoPrtcpLikeSelectByPrtcpIdDto> selectByCrtrId(String crtrId);
	
	/**
	 * 0610 손창민
	 * 내가 찜한 모임 목록 불러오기
	 * @param id
	 * @return
	 */
	List<MeetInfoPrtcpLikeSelectByPrtcpIdDto> selectById(String id);
	
	//마이페이지 끝
	
	// MEET_list에서 사용할 코드들 - 김지민
	
	/**
	 * 최신순(기본)
	 * @return MeetListCountDto
	 */
	List<MeetListCountDto> selectOrderByRecent();
	
	/**
	 * 인기순
	 * @return
	 */
	List<MeetListCountDto> selectOrderByPopularity();

	/**
	 * 제목 키워드 검색 select문
	 * @param title (.value) 사용하기 
	 * @return
	 */
	List<MeetListCountDto> selectByKeyword(String title);
	
	/**
	 * 지역 검색
	 * @param sido (.value) 사용하기 
	 * @return
	 */
	List<MeetListCountDto> selectByLocation(String sido);
	
	/**
	 * 분야 검색
	 * @param category (.value) 사용하기 
	 * @return
	 */
	List<MeetListCountDto> selectByCategory(String category);
	
	/**
	 * 0612 김지민
	 * 모집 완료
	 * @return
	 */
	List<MeetListCountDto> selectOrderByMozipEnd();
	
	/**
	 * 0612 김지민
	 * 모집 중
	 * @return
	 */
	List<MeetListCountDto> selectOrderByMozipIng();
	
	/**
	 * mtid 모임 상세 페이지
	 * @param mtid
	 * @return
	 */
	
	// 작성 상세페이지
	MeetInfo detailByMtid(long mtid);
	
}
