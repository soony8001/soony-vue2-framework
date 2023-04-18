package module.app;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import module.app.board.domain.BoardVO;
import module.app.board.service.BoardService;


@Component
@EnableAsync
public class QuartsScheduler {
	
	  @Autowired
	  private BoardService boardService;

	/**
	 * Cron 표현식을 사용한 작업 예약
	 * 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
	 * 이하 소스는 5초마다 실행  *:모드   ?:쓰지 않음   숫자는 매시매분매초
	 * @Scheduled 만 쓰면 사용할 수 있음
	 */
	 /**
	@Scheduled(cron = "0/5 * * * * ?")
	public void scheduleTaskUsingCronExpression() throws Exception{
		long now = System.currentTimeMillis() / 1000;
		System.out.println("schedule tasks using cron jobs - {} = " + now);
		
		String[] sort = URLDecoder.decode("DOC_NO DESC", "UTF-8").split(",");
				
		int page = 1;
		int rows = 10;
	    BoardVO vo = new BoardVO();
	    vo.setSchType("");
	    vo.setSchVal("");
	    vo.setStartNo(((page * rows) - rows) + 1);
	    vo.setEndNo(page * rows);
	    vo.setSort(sort);
	    

	    List<BoardVO> result = boardService.selectBoardList(vo);
	    System.out.println("result Size = " + result.size());
	}
	**/
}
