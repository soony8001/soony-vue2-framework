package module.app.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import module.app.board.domain.BoardVO;
import module.app.board.domain.FileVO;
import module.app.board.domain.ReplyVO;
import module.app.board.domain.UserInfoVO;
import module.app.board.repository.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
  BoardMapper boardMapper;
	
	public List<BoardVO> selectBoardList(BoardVO vo) throws Exception {
		return boardMapper.selectBoardList(vo);
	}

	public int selectBoardListCount(BoardVO vo) throws Exception {
		return boardMapper.selectBoardListCount(vo);
	}

	public BoardVO selectBoardDetail(BoardVO vo) throws Exception{
		return boardMapper.selectBoardDetail(vo);
	}
	
	public UserInfoVO selectUserLoginInfo(UserInfoVO vo) throws Exception{
		return boardMapper.selectUserLoginInfo(vo);
	}
	
	public List<FileVO> selectFileList(BoardVO vo) throws Exception{
		return boardMapper.selectFileList(vo);
	}
	
	public int insertBoard(BoardVO vo) throws Exception {
		return boardMapper.insertBoard(vo);
	}
	
	public int insertUserInfo(UserInfoVO vo) throws Exception {
		return boardMapper.insertUserInfo(vo);
	}
	
	public int inserFileInfo(FileVO vo) throws Exception {
		return boardMapper.insertFileInfo(vo);
	}
	
	public int selectNextDocNo(BoardVO vo) throws Exception {
		return boardMapper.selectNextDocNo(vo);
	}
	
	public int selectNextFileNo() throws Exception {
		return boardMapper.selectNextFileNo();
	}

	public int updateBoard(BoardVO vo) throws Exception{
		return boardMapper.updateBoard(vo);
	}

	public int deleteBoard(BoardVO vo) throws Exception {
		return boardMapper.deleteBoard(vo);
	}

	public void increaseViewCount(BoardVO vo) throws Exception {
		boardMapper.increaseViewCount(vo);
	}

	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception {
		return boardMapper.selectReplyList(vo);
	}

	public int insertReply(ReplyVO vo) throws Exception {
		return boardMapper.insertReply(vo);
	}

	public int updateReply(ReplyVO vo) throws Exception {
		return boardMapper.updateReply(vo);
	}

	public int deleteReply(ReplyVO vo) throws Exception {
		return boardMapper.deleteReply(vo);
	}
	
	public List<UserInfoVO> selectUserInfo(UserInfoVO vo) throws Exception {
		return boardMapper.selectUserInfo(vo);
	}
  
}
