package module.app.board.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import module.app.board.domain.RecruitVO;
import module.app.board.domain.FileVO;
import module.app.board.domain.RecruitVO;
import module.app.board.domain.ReplyVO;
import module.app.board.service.RecruitService;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/recruit")
public class RecruitController {

  @Autowired
  private RecruitService recruitService;
  
  MultipartResolver multipartResolver;
  
  /**
   * 게시글 목록 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value="/mainList")
  public HashMap<String, Object> selectBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String schType = request.getParameter("schType");
    String schVal = request.getParameter("schVal");
    int rows = Integer.parseInt(request.getParameter("rows"));
    int page = Integer.parseInt(request.getParameter("page"));
    String[] sort = URLDecoder.decode(request.getParameter("sort"), "UTF-8").split(",");

    RecruitVO vo = new RecruitVO();
    vo.setSchType(schType);
    vo.setSchVal(schVal);
    vo.setStartNo(((page * rows) - rows) + 1);
    vo.setEndNo(page * rows);
    vo.setSort(sort);

    List<RecruitVO> result = recruitService.selectBoardList(vo);

    int total = 0;

    if(result.size() > 0) {
      total = recruitService.selectBoardListCount(vo);
    }

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("data", result);
    map.put("total", total);

    return map;
  }
  
  /**
   * 게시글 목록 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value="/recruitList")
  public HashMap<String, Object> selectBoardMainList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String schType = request.getParameter("schType");
    String schVal = request.getParameter("schVal");
    int rows = Integer.parseInt(request.getParameter("rows"));
    int page = Integer.parseInt(request.getParameter("page"));
    String[] sort = URLDecoder.decode(request.getParameter("sort"), "UTF-8").split(",");

    RecruitVO vo = new RecruitVO();
    vo.setSchType(schType);
    vo.setSchVal(schVal);
    vo.setStartNo(((page * rows) - rows) + 1);
    vo.setEndNo(page * rows);
    vo.setSort(sort);

    List<RecruitVO> result = recruitService.selectBoardList(vo);

    int total = 0;

    if(result.size() > 0) {
      total = recruitService.selectBoardListCount(vo);
    }

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("data", result);
    map.put("total", total);

    return map;
  }

  /**
   * 게시글 상세 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value = "/detail")
  public RecruitVO selectBoardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String docNo = request.getParameter("docNo");

    RecruitVO vo = new RecruitVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 조회수 증가
    recruitService.increaseViewCount(vo);

    RecruitVO result = recruitService.selectBoardDetail(vo);
 
    List<FileVO> fileList = recruitService.selectFileList(vo);
    result.setFiles(fileList);

    return result;
  }
  
  /**
   * 게시글 작성
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/downloadFile")
  public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
	  String retStr = "";
	  System.out.println("--------------vue파일다운로드-------------");
	  
	  
	  String getPath= request.getParameter("filepath");
	  String getName= request.getParameter("filename");
	  String getOrginFileName = request.getParameter("originFileName");
	  
	  System.out.println("getOrginFileName:"+getOrginFileName);
	  System.out.println("getName:"+getName);
	  System.out.println("getPath:"+getPath);

	  String sFilePath="C:\\sts-4.15.0.RELEASE\\workspace\\gradle-board\\fileStorage\\" + getOrginFileName;
	  String fileName = getOrginFileName;
	  
	  File oFile = new File(sFilePath);
	  
	  // 5M 다운로드 
	  byte[] b = new byte[5*1025*1024];
	  
	  FileInputStream in = new FileInputStream(oFile);
	  
	  ServletContext context = request.getSession().getServletContext();
	  String sMimeType = context.getMimeType(sFilePath);
	  
	  if (sMimeType == null) {
		  // 한글 
		  if (sFilePath.contains(".hwp")) {
			  sMimeType = "application/x-hwp";
		  } else if (sFilePath.contains(".hwx")) {
			  sMimeType = "application/x-hwx";
		  } else if (sFilePath.contains(".hml")) {
			  sMimeType = "application/vnd.hancom.hml";
		  } else if (sFilePath.contains(".hwpx")) {
			  sMimeType = "application/vnd.hancom.hwpx";
		  } else if (sFilePath.contains(".pdf")) {
			  sMimeType = "application/pde";
		  } else if (sFilePath.contains(".xls")) {
			  sMimeType = "application/vnd.ms-excel";
		  } else if (sFilePath.contains(".xlsx")) {
			  sMimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		  } else {
			  sMimeType = "application.octec-stream";
		  }
		 
	  }
	  
	  response.setContentType(sMimeType);
	  
	  String A = new String(fileName.getBytes("euc-kr"), "8859_1");
	  String B = "utf-8";
	  String sEncoding = URLEncoder.encode(A,B);
	  
	  String AA = "Content-Disposition";
	  String BB = "attachment; filename=" + sEncoding;
	  response.setHeader(AA, BB);
	  
	  int numRead = 0;
	  
	  ServletOutputStream out2 = response.getOutputStream();
	  while((numRead=in.read(b,0,b.length)) != -1) {
		  out2.write(b,0,numRead);
	  }
	  out2.flush();
	  out2.close();
	  in.close();

	  retStr = getOrginFileName;
	  
	  return retStr;
  }

  /**
   * 게시글 작성
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/insert")
  public int insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
	  
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    RecruitVO vo = new RecruitVO();
    vo.setTitle(title);
    vo.setContent(content);
    // TODO 로그인 사용자로 작성자 받기
    vo.setWriter("Test Writer");

    int result = 0; // 신규 게시글 번호
    
    int rows = recruitService.insertBoard(vo);

    if(rows > 0) {
      result = vo.getDocNo();
    }
    
    int linkDocNo = recruitService.selectNextDocNo(vo);
    int maxfileNo = recruitService.selectNextFileNo();
        
    Iterator<String> fileNames = multipartRequest.getFileNames();
    int sortNo = 0;
    while(fileNames.hasNext()) {
    	String fileName = fileNames.next();
    	System.out.println("##########################################################");
    	System.out.println("fileName = " + fileName);
    	System.out.println("##########################################################");
    	MultipartFile mFile = multipartRequest.getFile(fileName);
    	File file = new File("C:\\sts-4.15.0.RELEASE\\workspace\\gradle-board\\fileStorage\\" + fileName);
    	if (mFile.getSize() != 0 ) {
    		if(!file.exists()) {
         		if (file.getParentFile().mkdir()) {
        			try {
        				file.createNewFile();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		}    			
    		}
    		try {
    			// File 실질적인 저장
    			mFile.transferTo(file);
    			maxfileNo ++;
    			sortNo ++;
    			// File 정보 등록
    		    FileVO fileVO = new FileVO();
    		    fileVO.setFileNo(getFileNo(maxfileNo));
    		    fileVO.setFilePath("C:\\sts-4.15.0.RELEASE\\workspace\\gradle-board\\fileStorage\\"+fileName);
    		    fileVO.setLinkDocNo(linkDocNo);
    		    fileVO.setRealFileNm(fileName);
    		    fileVO.setServerFileNm(fileName);
    		    fileVO.setSortNo(sortNo);
    		    fileVO.setUseYn("Y");
    		    fileVO.setWriter("soon");
    		    
    		    recruitService.inserFileInfo(fileVO);
    		    
    		}catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

    return result;
  }
  
  private String getFileNo(int fileNo) {
	  
	  String retValue = "FILE_";
	  
	  String strFileNo = String.valueOf(fileNo);
	  String prefix = "";
	  for(int i=0; i<10-strFileNo.length();i++) {
		  prefix = prefix + "0";
	  }
	  
	  return retValue + prefix + strFileNo;
  }

  /**
   * 게시글 수정
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/update")
  public int updateBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    RecruitVO vo = new RecruitVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setTitle(title);
    vo.setContent(content);

    int rows = recruitService.updateBoard(vo);

    return rows;
  }

  /**
   * 게시글 삭제
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/delete")
  public int deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");

    RecruitVO vo = new RecruitVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 댓글 유무 확인 후 같이 삭제
    ReplyVO replyVO = new ReplyVO();
    replyVO.setDocNo(Integer.parseInt(docNo));

    List<ReplyVO> result = recruitService.selectReplyList(replyVO);

    if(result.size() > 0) {
      recruitService.deleteReply(replyVO);
    }

    int rows = recruitService.deleteBoard(vo);

    return rows;
  }

  /**
   * 댓글 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value = "/reply/list")
  public List<ReplyVO> selectReplyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");

    ReplyVO vo = new ReplyVO();
    vo.setDocNo(Integer.parseInt(docNo));

    List<ReplyVO> result = recruitService.selectReplyList(vo);

    return result;
  }

  /**
   * 댓글 작성
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/insert")
  public int insertReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");
    String comment = request.getParameter("comment");

    ReplyVO vo = new ReplyVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setWriter("Test Writer");
    vo.setContent(comment);

    int rows = recruitService.insertReply(vo);

    return rows;
  }

  /**
   * 댓글 수정
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/update")
  public int updateReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String replyNo = request.getParameter("replyNo");
    String docNo = request.getParameter("docNo");
    String comment = request.getParameter("comment");

    ReplyVO vo = new ReplyVO();
    vo.setReplyNo(Integer.parseInt(replyNo));
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setContent(comment);

    int rows = recruitService.updateReply(vo);

    return rows;
  }

  /**
   * 댓글 삭제
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/reply/delete")
  public int deleteReply(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String replyNo = request.getParameter("replyNo");
    String docNo = request.getParameter("docNo");

    ReplyVO vo = new ReplyVO();
    vo.setReplyNo(Integer.parseInt(replyNo));
    vo.setDocNo(Integer.parseInt(docNo));

    int rows = recruitService.deleteReply(vo);

    return rows;
  }
  
}
