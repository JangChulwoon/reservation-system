<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
    
    <style type="text/css">
    .none{
    	display:none;
    }
    
    </style>
</head>

<body>
    <div id="container">
        <jsp:include page="common/header.jsp"/>
        <hr>
        <div class="ct">
            <div class="section_my">
                <!-- 예약 현황 -->
                <div class="my_summary">
                    <ul class="summary_board">
                    
                        <li class="item">
                            <!--[D] 선택 후 .on 추가 link_summary_board -->
                            <a href="#" class="link_summary_board on"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure">${count.All}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure">${count.EXPECTATION}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure">${count.USED}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure"> ${count.CANCELLATION}</span> </a>
                        </li>
                    </ul>
                </div>
                <!--// 예약 현황 -->

                <!-- 내 예약 리스트 -->
                
                <div class="wrap_mylist">
          			<!-- 예약 리스트 없음 -->
	                <div class="err none"> <i class="spr_book ico_info_nolist"></i>
	                    <h1 class="tit">예약 리스트가 없습니다</h1>
	                </div>
		                <!--// 예약 리스트 없음 -->
				    <ul class="list_cards">
					    <li class ="card expectation" data-type = "1">
							
					    </li>
                        <!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
                        
                        <li class="card confirmed" data-type = "2">
							
                        </li>
                        
                        <li class="card used" data-type = "3">
                           
                        </li>
                        
                        <li class="card cancellation used" data-type = "4">
                           
                        </li>
                	</ul>
                </div>
                <!--// 내 예약 리스트 -->

            </div>
        </div>
        <hr>
    </div>
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div id="footer" class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>

    <!-- 취소 팝업 -->
    <!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
    <div class="popup_booking_wrapper none" >
        <div class="dimm_dark" style="display:block"></div>
        <div class="popup_booking refund">
            <h1 class="pop_tit">
                <span>서비스명/상품명</span>
                <small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
            </h1>
            <div class="nomember_alert">
                <p>취소하시겠습니까?</p>
            </div>
            <div class="pop_bottom_btnarea">
                <div class="btn_gray">
                    <a href="#" class="btn_bottom"><span>아니오</span></a>
                </div>
                <div class="btn_green">
                    <a href="#" class="btn_bottom"><span>예</span></a>
                </div>
            </div>
            <!-- 닫기 -->
            <a href="#" class="popup_btn_close" title="close">
                <i class="spr_book2 ico_cls"></i>
            </a>
            <!--// 닫기 -->
        </div>
    </div>
    <!--// 취소 팝업 -->
    


<script id="reservation-content" type="text/x-handlebars-template">

{{# menubar}}
<div class=link_booking_details>
	<div class="card_header">
		<div class="left"></div>
			<div class="middle">
				<i class="spr_book2 {{icon}}"></i>
				<span class="tit">{{menubar}}</span>
			</div>
		<div class="right"></div>
	</div>
</div>
{{/menubar}}

{{#reservation}}
<article class="card_item">
	<a href="#" class="link_booking_details">
		<div class="card_body">
			<div class="left"></div>
			<div class="middle">
				<div class="card_detail" data-id = {{id}}  >
					<em class="booking_number">No. {{id}}</em>
					<h4 class="tit">{{name}}</h4>
					<ul class="detail">
						<li class="item">
							<span class="item_tit">일정</span>
							<em class="item_dsc">
								{{timeStamp displayStart}} ~{{timeStamp displayEnd}}
							</em>
						</li>
						<li class="item">
							<span class="item_tit">내역</span>
							<em class="item_dsc">
							  {{exit '일반' generalTicketCount}}  {{exit '청소년' youthTicketCount}}   {{exit '어린이' childTicketCount}}  -  합계 ({{plus generalTicketCount youthTicketCount  childTicketCount}})
 							</em>
						</li>
						<li class="item">
							<span class="item_tit">업체</span>
							<em class="item_dsc">
								{{name}}
							</em>
						</li>
					</ul>
					<div class="price_summary">
						<span class="price_tit">결제 금액</span>
						<em class="price_amount">
						<span>{{totalPrice}}</span>
						<span class="unit">원</span>
						</em>
					</div>
					<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
					{{#if btns}}
					<div class="booking_cancel">
						<button class="btn"><span>{{btnText btns}}</span></button>
					</div>
					{{/if}}
				</div>
			</div>
			<div class="right"></div>
		</div>
	<div class="card_footer">
		<div class="left"></div>
		<div class="middle"></div>
		<div class="right"></div>
	</div>
	</a>
	<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
</article>	
{{/reservation}}
</script>	


    
<script src="/resources/js/node_modules/jquery/dist/jquery.min.js"></script>

<!--  Handlebar -->
<script src="/resources/js/node_modules/handlebars/dist/handlebars.min.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<script src = "/resources/js/node_modules/@egjs/component/dist/component.min.js"></script>

<script src="/resources/js/reservationState.js"></script>

<script>

// hㅁndlebars 설정 
moment.locale('ko', {
    weekdays: ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
    weekdaysShort: ["일","월","화","수","목","금","토"],
});



// 핸들바 helper 설정
Handlebars.registerHelper('exit', function (string,value) {
	if(value){
		 return string +' ('+ value+')';
	}
	return '';
});

Handlebars.registerHelper('plus', function (first,second,third) {
	  return first+ second + third;
});

Handlebars.registerHelper("timeStamp", function(timestamp) {
	  if (moment) {
	    // can use other formats like 'lll' too
	    return  moment(timestamp).format("YYYY.DD.MM (ddd)");
	  }
	  else {
	    return datetime;
	  }
});

Handlebars.registerHelper("btnText", function(btn) {
	 return btn;
});

var reservationTypeEnum = {
	    ALL_RESERVATION : 0,
	    EXPECTATION : 1,
	    END : 2,
	    CENCELLATION : 3
}


/* 	var module = ReservationState;
	module.init();
	module.isEmpty(); */
	
	$(function(){
		var $item = $(".link_summary_board");
		var $card = $(".card");
		$item.each(function(i,v){
			new MenuBar($(v));
		});
		
		$card .each(function(i,v){
			new MainContents($(v).attr("class").split(' ')[1]);
		});
		
	});
	
</script>

</body>

</html>