package module.app.board.repository;

import java.util.List;

import module.app.board.domain.RecruitVO;
import module.app.board.domain.FileVO;
import module.app.board.domain.ReplyVO;

public interface RecruitMapper {
  
  /**
   * 게시판 목록 조회
   * 
   * @param vo
   * @return
   * @throws Exception
   */
  public List<RecruitVO> selectBoardList(RecruitVO vo) throws Exception;
  
  /**
   * 게시판 건수 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public int selectBoardListCount(RecruitVO vo) throws Exception;

  /**
   * 게시글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public RecruitVO selectBoardDetail(RecruitVO vo) throws Exception;
  
  /**
   * 게시글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public List<FileVO> selectFileList(RecruitVO vo) throws Exception;

  /**
   * 게시글 등록
   * @param vo
   * @return
   * @throws Exception
   */
  public int insertBoard(RecruitVO vo) throws Exception;
  
  /**
   * 파일정보 등록
   * @param vo
   * @return
   * @throws Exceptions
   */
  public int insertFileInfo(FileVO vo) throws Exception;
  
  /**
   * 문서 번호 알기
   * @param vo
   * @return
   * @throws Exception
   */
  public int selectNextDocNo(RecruitVO vo) throws Exception;
  
  /**
   * 문서 번호 알기
   * @param vo
   * @returns
   * @throws Exception
   */
  public int selectNextFileNo() throws Exception;

  /**
   * 게시글 수정
   * @param vo
   * @return
   * @throws Exception
   */
  public int updateBoard(RecruitVO vo) throws Exception;

  /**
   * 게시글 삭제
   * @param vo
   * @return
   * @throws Exception
   */
  public int deleteBoard(RecruitVO vo) throws Exception;

  /**
   * 조회수 증가
   * @param vo
   * @throws Exception
   */
  public void increaseViewCount(RecruitVO vo) throws Exception;

  /**
   * 댓글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception;

  /**
   * 댓글 작성
   * @param vo
   * @return
   * @throws Exception
   */
  public int insertReply(ReplyVO vo) throws Exception;

  /**
   * 댓글 수정
   * @param vo
   * @return
   * @throws Exception
   */
  public int updateReply(ReplyVO vo) throws Exception;

  /**
   * 댓글 삭제
   * @param vo
   * @return
   * @throws Exception
   */
  public int deleteReply(ReplyVO vo) throws Exception;


}
