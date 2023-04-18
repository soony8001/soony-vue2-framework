package module.app.board.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
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

import module.app.board.domain.BoardVO;
import module.app.board.domain.FileVO;
import module.app.board.domain.ReplyVO;
import module.app.board.domain.UserInfoVO;
import module.app.board.service.BoardService;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;
  
  MultipartResolver multipartResolver;
  
  /**
   * 게시글 목록 조회
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping(value="/list")
  public HashMap<String, Object> selectBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String schType = request.getParameter("schType");
    String schVal = request.getParameter("schVal");
    int rows = Integer.parseInt(request.getParameter("rows"));
    int page = Integer.parseInt(request.getParameter("page"));
    String[] sort = URLDecoder.decode(request.getParameter("sort"), "UTF-8").split(",");

    BoardVO vo = new BoardVO();
    vo.setSchType(schType);
    vo.setSchVal(schVal);
    vo.setStartNo(((page * rows) - rows) + 1);
    vo.setEndNo(page * rows);
    vo.setSort(sort);

    List<BoardVO> result = boardService.selectBoardList(vo);

    int total = 0;

    if(result.size() > 0) {
      total = boardService.selectBoardListCount(vo);
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
  @GetMapping(value="/mainList")
  public HashMap<String, Object> selectBoardMainList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String schType = request.getParameter("schType");
    String schVal = request.getParameter("schVal");
    int rows = Integer.parseInt(request.getParameter("rows"));
    int page = Integer.parseInt(request.getParameter("page"));
    String[] sort = URLDecoder.decode(request.getParameter("sort"), "UTF-8").split(",");

    BoardVO vo = new BoardVO();
    vo.setSchType(schType);
    vo.setSchVal(schVal);
    vo.setStartNo(((page * rows) - rows) + 1);
    vo.setEndNo(page * rows);
    vo.setSort(sort);

    List<BoardVO> result = boardService.selectBoardList(vo);

    int total = 0;

    if(result.size() > 0) {
      total = boardService.selectBoardListCount(vo);
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
  public BoardVO selectBoardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String docNo = request.getParameter("docNo");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 조회수 증가
    boardService.increaseViewCount(vo);

    BoardVO result = boardService.selectBoardDetail(vo);
 
    List<FileVO> fileList = boardService.selectFileList(vo);
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

    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setContent(content);
    // TODO 로그인 사용자로 작성자 받기
    vo.setWriter("Test Writer");

    int result = 0; // 신규 게시글 번호
    
    int rows = boardService.insertBoard(vo);

    if(rows > 0) {
      result = vo.getDocNo();
    }
    
    int linkDocNo = boardService.selectNextDocNo(vo);
    int maxfileNo = boardService.selectNextFileNo();
        
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
    		    
    		    boardService.inserFileInfo(fileVO);
    		    
    		}catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

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
  @PostMapping(value = "/insertUserInfo")
  public HashMap<String, Object> insertUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
	  
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
    String userId = request.getParameter("userId");
    String passwd = request.getParameter("passwd");
    String email = request.getParameter("email");
    String name = request.getParameter("name");
    String flexRadioDefault = request.getParameter("flexRadioDefault");
    String phone1 = request.getParameter("phone1");
    String phone2 = request.getParameter("phone2");
    String postNum = request.getParameter("postNum");
    String addr1 = request.getParameter("addr1");
    String addr2 = request.getParameter("addr2");
    String birth = request.getParameter("birth");
    String multiItems = request.getParameter("multiItems");
    
    String salt = generateSalt();

    UserInfoVO vo = new UserInfoVO();
    vo.setUserId(userId);
    vo.setPassword(hashPassword(passwd, salt));
    vo.setEmail(email);
    vo.setUserName(name);
    vo.setGender(flexRadioDefault);
    vo.setPhone(phone1 + phone2);
    vo.setPostNum(postNum);
    vo.setAddr1(addr1);
    vo.setAddr2(addr2);
    vo.setBirth(birth);
    vo.setSalt(salt);
    
    //vo.setContent(content);
    // TODO 로그인 사용자로 작성자 받기
    //vo.setWriter("Test Writer");

    int result = 0; // 신규 게시글 번호
    
    int rows = boardService.insertUserInfo(vo);
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    if (rows > 0) {
    	map.put("userId", userId);
    }
    map.put("row", rows);
    
    return map;
  }
  
  /**
   *  로그인 동작
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/confirmLoginInfo")
  public HashMap<String, Object> confirmLoginInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
	  
    HashMap<String, Object> map = new HashMap<String, Object>();
    String userId = request.getParameter("userId");
    String passwd = request.getParameter("passwd");
    
    UserInfoVO userVO = new UserInfoVO();
    userVO.setUserId(userId);
    userVO.setPassword(passwd);
    
    // db에서 값 가지고 오기
    UserInfoVO resultVO = boardService.selectUserLoginInfo(userVO);
    if (resultVO == null || "".equals(resultVO.getPassword())) {
    	System.out.println("Invalid password!");
        map.put("result", "fail");
        return map;
    }
    
    String salt = resultVO.getSalt();
    String hashedPassword = resultVO.getPassword();
    String calculatedHashedPassword = hashPassword(passwd, salt);
    
    if (hashedPassword.equals(calculatedHashedPassword)) {
        System.out.println("Login successful!");
        map.put("result", "success");
      } else {
        System.out.println("Invalid password!");
        map.put("result", "fail");
      }
    
    return map;
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

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setTitle(title);
    vo.setContent(content);

    int rows = boardService.updateBoard(vo);

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

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));

    // 댓글 유무 확인 후 같이 삭제
    ReplyVO replyVO = new ReplyVO();
    replyVO.setDocNo(Integer.parseInt(docNo));

    List<ReplyVO> result = boardService.selectReplyList(replyVO);

    if(result.size() > 0) {
      boardService.deleteReply(replyVO);
    }

    int rows = boardService.deleteBoard(vo);

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

    List<ReplyVO> result = boardService.selectReplyList(vo);

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

    int rows = boardService.insertReply(vo);

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

    int rows = boardService.updateReply(vo);

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

    int rows = boardService.deleteReply(vo);

    return rows;
  }
  
  
  /**
   * 아이디 체크
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @ResponseBody
  @PostMapping(value = "/selectUserInfo")
  public HashMap<String, Object> selectUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String userId = request.getParameter("userId");

    UserInfoVO vo = new UserInfoVO();
    vo.setUserId(userId);

    int total = 0;
    List<UserInfoVO> result  = boardService.selectUserInfo(vo);
    
    if (result != null && result.size() > 0) {
    	total = result.size();
    }
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("userId", userId);
    map.put("total", total);
    

    return map;
  }
  
  private static String hashPassword(String password, String salt) {
	    try {
	      MessageDigest md = MessageDigest.getInstance("SHA-256");
	      md.update(salt.getBytes());
	      byte[] hashedPasswordBytes = md.digest(password.getBytes());
	      return Arrays.toString(hashedPasswordBytes);
	    } catch (NoSuchAlgorithmException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  private static String generateSalt() {
	    SecureRandom random = new SecureRandom();
	    byte[] salt = new byte[16];
	    random.nextBytes(salt);
	    return Arrays.toString(salt);
	  }
  
}
