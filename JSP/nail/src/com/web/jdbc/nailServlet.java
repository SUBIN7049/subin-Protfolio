package com.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/nailServlet")

public class nailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private nailDataUtil nDbUtil;
    
	@Resource(name="jdbc/nail")
	private DataSource dataSource;

	@Override
    public void init() throws ServletException {
    	super.init();
    	nDbUtil = new nailDataUtil(dataSource);	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command= "list";
		if(request.getParameter("command") != null) {
			
			command = request.getParameter("command");
		};
		
		switch(command) {
		case "list" :
			listmenu(request,response);
			break;
		case "NeedTimeData" :
			listreservation(request,response);
			break;
		case "NeedTimeData2" :
			addreservation(request,response);
			break;
		case "reservationcheck":
			reservationcheck(request,response);
			break;
		case "reservationcheck2":
			reservationcheck2(request,response);
			break;
		case "reservationcancel":
			reservationcancel(request,response);
			break;
		
		}
	}

	

	
	private void reservationcancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] date_num = request.getParameterValues("cancel_reservationnumber");
		
		for(int i=0; i<date_num.length; i++) {
			int reservation_num=nDbUtil.reservation_num2(date_num[i]);
			nDbUtil.delete_reservation_service(reservation_num);
			nDbUtil.delete_reservation(date_num[i]);
			nDbUtil.update_possibleDateTime(date_num[i]);
		}
		response.sendRedirect(request.getContextPath()+"/nailServlet");
	}

	private void reservationcheck2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("re_user_id");
		request.setAttribute("re_user_id",request.getParameter("re_user_id"));
		
		ArrayList<reservation_list> cc = nDbUtil.reservationcheck2(user_id);
		
		request.setAttribute("reservation_cancel_list", cc);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation_cancel1.jsp");
		dispatcher.forward(request,response);
		
	}

	private void reservationcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("re_user_id");
		ArrayList<reservation_list> bb = nDbUtil.reservationcheck(user_id);
		
		request.setAttribute("reservationlist", bb);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation_check2.jsp");
		dispatcher.forward(request,response);
		
		
	}

	private void addreservation(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		String phone = request.getParameter("user_id");
		String redate = request.getParameter("datelast");
		String retime = request.getParameter("timeradio");
		String nail = request.getParameter("nailradio");
		String pedi = request.getParameter("pediradio");
		String[] option =request.getParameterValues("optioncheck");
		
		
		reservation add = new reservation(phone);	
		/// 1. 예약 정보놓기 --> 시간번호(input 받은 시간과 날짜로 얻어로 수 있다. ==> 함수를 만들어야한다.)랑 유저핸드폰(input) 
		// 2. 예약에 포함된 서비스명들 넣기  --> 예약 번호(시간번호만으로도 얻을 수 있다.)와 인풋받은 서비스들 이필요
		/// 3. 선택한 기간 예약 ox 변경하기 -- > 시간 날짜만 필요 한데 이것도 시간번호만 있어도 가능
		
		/// 1. 시간날짜 얻오는 함수를 만들어서 저장한다.
		// 2. 1.함수를 만드는데 위에 함수의 값을 저장한 변수와 input받은 핸드폰 번호를 매개로 받아서 처리한다.
		// 3. 시간 번호로 예약 번호를 얻오는 함수를 만든다
		// 4. 위의 함수를 이용해서 얻은 데이터를 2 번 메서드를 만드는데 매개변수로 넣어준다.
		// 
		int date_num = nDbUtil.addreservation(add, redate, retime);
		nDbUtil.update_ox(redate,retime);		
		int reser_num = nDbUtil.reservation_num(date_num);
		nDbUtil.in_reservation_service(reser_num, nail);
		nDbUtil.in_reservation_service(reser_num, pedi);
		for(int i = 0;i <option.length;i++) {
			
			nDbUtil.in_reservation_service(reser_num, option[i]);
		}
		
		response.sendRedirect(request.getContextPath()+"/nailServlet");
	}

	private void listreservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datee = request.getParameter("reservation_date");
	
		ArrayList<String> aa = nDbUtil.addreservation(datee);
		try {
			ArrayList<nail_menu> bb = nDbUtil.menuchoice(1);
			ArrayList<nail_menu> cc = nDbUtil.menuchoice(2);
			ArrayList<nail_menu> dd = nDbUtil.menuchoice(3);
			
			request.setAttribute("menu_nail", bb);
			request.setAttribute("menu_pedi", cc);
			request.setAttribute("menu_option", dd);
			request.setAttribute("reservation_date",datee);
			request.setAttribute("user_id",request.getParameter("user_id"));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		request.setAttribute("times", aa);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation_time_type.jsp");
		dispatcher.forward(request,response);
	}

	private void listmenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			List<nail_menu> naillist = nDbUtil.getnail_menu();
			
			request.setAttribute("menu_list", naillist);
		}catch(Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/nailhome.jsp");
		dispatcher.forward(request, response);
		 
	}

	

}
