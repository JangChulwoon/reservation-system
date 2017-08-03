package kr.or.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.reservation.dto.ReservationTypeCountDTO;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.sqls.UserReservationSqls;

@Repository
public class UserReservationDao {

	Logger log = Logger.getLogger(this.getClass());
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<UserReservationDTO> mapper = BeanPropertyRowMapper.newInstance(UserReservationDTO.class);
	private RowMapper<ReservationTypeCountDTO> countMapper = BeanPropertyRowMapper
			.newInstance(ReservationTypeCountDTO.class);

	public UserReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<UserReservationDTO> selectReservationAll(int userId) {
		Map<String, Integer> map = Collections.singletonMap("id", userId);
		return jdbc.query(UserReservationSqls.SELET_RESERVATION_ALL, map, mapper);
	}

	public List<UserReservationDTO> selectReservationByType(int userId, int type) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", userId);
		map.put("type", type);
		return jdbc.query(UserReservationSqls.SELET_RESERVATION_BY_TYPE, map, mapper);
	}

	public boolean cancelReservation(int userId, int reservationId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", reservationId);
		map.put("userId", userId);
		return jdbc.update(UserReservationSqls.CANCEL_RESERVATION_ONE, map) == 1;
	}

	public List<ReservationTypeCountDTO> selectTypeCount(int userId) {
		Map<String, ?> map = Collections.singletonMap("id", userId);
		return jdbc.query(UserReservationSqls.SELECT_TYPECOUNT, map, countMapper);
	}

}
