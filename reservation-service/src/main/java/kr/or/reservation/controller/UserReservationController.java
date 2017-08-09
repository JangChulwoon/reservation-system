package kr.or.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.api.NaverLogin;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.service.UserReservationService;

@Controller
public class UserReservationController {

	UserReservationService userReservationService;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public void setUserReservationService(UserReservationService userReservationService) {
		this.userReservationService = userReservationService;
	}

	// argumentResolver ~
	// call 되는 시점에 무슨 작업을 하든 상관 없음
	// @AuthUserId int id << 이런식으로 사용가능
	// 매번 유효성 검증하고, ..~
	@GetMapping(path = "/myPage")
	public String viewMyPage(Model model, HttpSession session) {
		int sessionId = (Integer) session.getAttribute("id");
		model.addAttribute("count", userReservationService.selectTypeCount(sessionId));
		return "myreservation";
	}

}