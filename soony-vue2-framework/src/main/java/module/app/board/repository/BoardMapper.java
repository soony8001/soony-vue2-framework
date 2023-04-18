package module.app.board.repository;

import java.util.List;

import module.app.board.domain.BoardVO;
import module.app.board.domain.FileVO;
import module.app.board.domain.ReplyVO;
import module.app.board.domain.UserInfoVO;

public interface BoardMapper {
  
  /**
   * 게시판 목록 조회
   * 
   * @param vo
   * @return
   * @throws Exception
   */
  public List<BoardVO> selectBoardList(BoardVO vo) throws Exception;
  
  /**
   * 게시판 건수 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public int selectBoardListCount(BoardVO vo) throws Exception;

  /**
   * 게시글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public BoardVO selectBoardDetail(BoardVO vo) throws Exception;
  
  /**
   * 게시글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public UserInfoVO selectUserLoginInfo(UserInfoVO vo) throws Exception;
  
  /**
   * 게시글 조회
   * @param vo
   * @return
   * @throws Exception
   */
  public List<FileVO> selectFileList(BoardVO vo) throws Exception;

  /**
   * 게시글 등록
   * @param vo
   * @return
   * @throws Exception
   */
  public int insertBoard(BoardVO vo) throws Exception;
  
  /**
   * 게시글 등록
   * @param vo
   * @return
   * @throws Exception
   */
  public int insertUserInfo(UserInfoVO vo) throws Exception;
  
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
  public int selectNextDocNo(BoardVO vo) throws Exception;
  
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
  public int updateBoard(BoardVO vo) throws Exception;

  /**
   * 게시글 삭제
   * @param vo
   * @return
   * @throws Exception
   */
  public int deleteBoard(BoardVO vo) throws Exception;

  /**
   * 조회수 증가
   * @param vo
   * @throws Exception
   */
  public void increaseViewCount(BoardVO vo) throws Exception;

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
  
  /**
   * 유저 중복 체크
   * @param vo
   * @return
   * @throws Exception
   */
  public List<UserInfoVO> selectUserInfo(UserInfoVO vo) throws Exception;


}
