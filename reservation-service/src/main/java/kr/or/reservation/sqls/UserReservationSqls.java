package kr.or.reservation.sqls;

public class UserReservationSqls {
	// 가격 부분은 어떻게 가져올 것인지 조금 생각해볼것 .
	// 모든 테이블을 inner join으로 작성.
	public static final String SELET_RESERVATION_BY_TYPE = "SELECT info.id,product.name,display.display_start,display.display_end, info.general_ticket_count,info.youth_ticket_count,info.child_ticket_count,info.reservation_type,info.total_price " + 
			"FROM reservation_info as info inner join product  " + 
			"on info.product_id = product.id " + 
			"inner join display_info as display " + 
			"on display.product_id = product.id " + 
			"where info.user_id = :id and info.reservation_type = :type;";

	public static final String SELET_RESERVATION_ALL = "SELECT info.id,product.name,display.display_start,display.display_end, info.general_ticket_count,info.youth_ticket_count,info.child_ticket_count,info.reservation_type,info.total_price " + 
			"FROM reservation_info as info inner join product  " + 
			"on info.product_id = product.id " + 
			"inner join display_info as display " + 
			"on display.product_id = product.id " + 
			"where info.user_id = :id; ";

	
	public static final String CANCEL_RESERVATION_ONE = 
			"UPDATE reservation_info " + 
			"SET " + 
			"reservation_type = 3, " + 
			"modify_date = now() " + 
			"WHERE id = :id and user_id = :userId;";
	
	public static final String SELECT_TYPECOUNT ="SELECT reservation_type , count(*) as count " + 
			"FROM reservation_info " + 
			"WHERE user_id = :id " + 
			"GROUP BY reservation_type; ";
}
