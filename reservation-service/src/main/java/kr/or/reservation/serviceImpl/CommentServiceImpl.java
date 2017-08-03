package kr.or.reservation.serviceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.CommentDao;
import kr.or.reservation.domain.AVGForComment;
import kr.or.reservation.domain.Comment;
import kr.or.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	CommentDao commentDao;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public Long insert(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return null;
		}
		comment.setCreateDate(new Timestamp(System.currentTimeMillis()));
		return commentDao.insert(comment);
	}

	@Override
	public List<Comment> selectByProductId(int productId) {
		// TODO Auto-generated method stub
		if (productId > 0) {
			return commentDao.select(productId);
		}
		return null;
	}

	@Override
	public List<Comment> selectByProductId(int productId, int start, int amount) {
		// TODO Auto-generated method stub
		if (productId > 0 && start >= 0 && amount >= 0) {
			return commentDao.selectByProductId(productId, start, amount);
		}
		return null;
	}

	/*
	 * _bar => ALL 대문자 ( [!!] 상수일 경우에만) 이 외의 경우엔 CAMEL 표기법
	 */
	@Override
	public AVGForComment selectAvgScoreByProductId(int producId) {
		if (producId > 0) {
			Long count = AVGForComment.getCount(producId);
			Float AVG = AVGForComment.getAVG(producId);
			// 08-01
			// 시간을 텀을 둬서 진행하는 방향
			// 로컬 -> caching server를 별도로 두는 곳도 잇음.
			// cached map? library 가 있음.
			// 자주 바뀌는 데이터는 이렇게 사용되면 x -> 비즈니스로직에 맞춰서 진행 :: '전제조건 - 요구사항 이해'
			if (count == null || AVG == null) {
				Map<String, Object> map = commentDao.selectAvgScoreByProductId(producId).get(0);
				// null check
				// getOrDefault() 을 사용하면 삼항 연산자 필요 없음.
				AVG = (map.get("avg_score") == null) ? 0 : ((BigDecimal) map.get("avg_score")).floatValue();
				count = (map.get("avg_score") == null) ? 0 : (Long) map.get("amount_of_count");
				AVGForComment.setCount(producId, count);
				AVGForComment.setAVG(producId, AVG);
				return new AVGForComment(count, AVG);
			} else {
				return new AVGForComment(count, AVG);
			}
		}
		return null;
	}

	@Override
	public List<?> getFileIdByCommentId(int commentId) {
		// TODO Auto-generated method stub
		if (commentId > 0) {
			return commentDao.selectFileId(commentId);
		}
		return null;
	}

	@Override
	public boolean updateFileName(int commentId, int fileId, int fileLength) {
		if (commentId <= 0 || fileId <= 0) {
			return false;
		}
		return commentDao.updateFileName(commentId, fileId, fileLength) == 1;
	}
}
