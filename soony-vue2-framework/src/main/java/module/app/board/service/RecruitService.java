package module.app.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import module.app.board.domain.RecruitVO;
import module.app.board.domain.FileVO;
import module.app.board.domain.ReplyVO;
import module.app.board.repository.RecruitMapper;

@Service
public class RecruitService {
	
	@Autowired
  RecruitMapper recruitMapper;
	
	public List<RecruitVO> selectBoardList(RecruitVO vo) throws Exception {
		return recruitMapper.selectBoardList(vo);
	}

	public int selectBoardListCount(RecruitVO vo) throws Exception {
		return recruitMapper.selectBoardListCount(vo);
	}

	public RecruitVO selectBoardDetail(RecruitVO vo) throws Exception{
		return recruitMapper.selectBoardDetail(vo);
	}
	
	public List<FileVO> selectFileList(RecruitVO vo) throws Exception{
		return recruitMapper.selectFileList(vo);
	}
	
	public int insertBoard(RecruitVO vo) throws Exception {
		return recruitMapper.insertBoard(vo);
	}
	
	public int inserFileInfo(FileVO vo) throws Exception {
		return recruitMapper.insertFileInfo(vo);
	}
	
	public int selectNextDocNo(RecruitVO vo) throws Exception {
		return recruitMapper.selectNextDocNo(vo);
	}
	
	public int selectNextFileNo() throws Exception {
		return recruitMapper.selectNextFileNo();
	}

	public int updateBoard(RecruitVO vo) throws Exception{
		return recruitMapper.updateBoard(vo);
	}

	public int deleteBoard(RecruitVO vo) throws Exception {
		return recruitMapper.deleteBoard(vo);
	}

	public void increaseViewCount(RecruitVO vo) throws Exception {
		recruitMapper.increaseViewCount(vo);
	}

	public List<ReplyVO> selectReplyList(ReplyVO vo) throws Exception {
		return recruitMapper.selectReplyList(vo);
	}

	public int insertReply(ReplyVO vo) throws Exception {
		return recruitMapper.insertReply(vo);
	}

	public int updateReply(ReplyVO vo) throws Exception {
		return recruitMapper.updateReply(vo);
	}

	public int deleteReply(ReplyVO vo) throws Exception {
		return recruitMapper.deleteReply(vo);
	}
  
}
