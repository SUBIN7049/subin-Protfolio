package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class nailDataUtil {
	private DataSource dataSource;
	
	public nailDataUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void close(Connection conn, Statement mySt, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
			}
			if(mySt != null) {
				mySt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//메뉴불러오기(home 가격표 뽑아올때) 함수
	public List<nail_menu> getnail_menu() throws SQLException {
		List<nail_menu> menuu = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT menu,price from nail_menu";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
			
			while(myRs.next()) {
				String menu = myRs.getString("menu");
				String price = myRs.getString("price");
				nail_menu tempmenu = new nail_menu(menu, price, 0);
				menuu.add(tempmenu);
			}
			return menuu;
		}finally {
			close(conn, mySt, myRs);
		}
	}
	
		
	// 예약가능한 시간 불러오기(input 받은 날짜이고 reservation_ox가 0인것 불러올 함수
	public ArrayList<String> addreservation(String ad) {
		ArrayList<String> pts = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql="SELECT possible_time from possibledatetime where possible_date=? and reservation_ox=0";
			
			mySt=conn.prepareStatement(sql);
			mySt.setString(1,ad);
			myRs=mySt.executeQuery();
			
			while(myRs.next()) {
				pts.add(myRs.getString("possible_time"));
			}			
		}catch(Exception e ) {
			
		}
		return pts;
	}
	
	
	//예약할때 카테고리별로 시술내용 불러올 함수
	public ArrayList<nail_menu> menuchoice(int ii) throws SQLException{
		ArrayList<nail_menu> choice= new ArrayList<>();
		
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT menu, price FROM nail_menu where category ="+ii;
			mySt=conn.createStatement();
			myRs=mySt.executeQuery(sql);
			
			while(myRs.next()) {
				String mmenu = myRs.getString("menu");
				String pprice = myRs.getString("price");
				nail_menu mmenuu = new nail_menu(mmenu,pprice);
				choice.add(mmenuu);
			}
			return choice;
		}finally {
			close(conn, mySt, myRs);
		}
	}
	
	//예약내용 넣을 때 사용 할 reservation_datenum 뽑기 함수
	public int selectdate_num(String pdate, String ptime) {
		int aa = 0 ;
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql="select date_num from possibledatetime where possible_date='"+pdate+"' and possible_time='"+ptime+"'";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
				
			myRs.next();
			aa = myRs.getInt("date_num");
			
			return aa;
		}
		catch(SQLException e) {
			e.printStackTrace();
	}finally {
			close(conn, mySt, myRs);
		}
		return aa;
	}
	
	//예약내용 reservation테이블에 넣을 함수
	public int addreservation(reservation add,String pdate, String ptime) {
		Connection conn = null;
		PreparedStatement mySt = null;
		int date_num= -1;
		try {
			date_num = selectdate_num(pdate,ptime);
			conn = dataSource.getConnection();
			String sql = "INSERT INTO reservation(user_id, reservation_datenum) VALUES (?,?)";
			mySt=conn.prepareStatement(sql);
			mySt.setString(1, add.getUser_id());
			mySt.setInt(2, date_num);
			mySt.execute();
			return date_num;
		}catch(SQLException e) {
			e.printStackTrace();
			return date_num;
		}
		finally {
			close(conn, mySt, null);
		}
	}
	
	
	//예약 번호 뽑아올 함수 
	public int reservation_num(int bb)   {
		int i = 0;
		Connection conn=null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql="select reservation_number from reservation where reservation_datenum='"+bb+"'";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
			
			myRs.next();
			i = myRs.getInt("reservation_number");
	
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			return i;
		} 
		finally {
			close(conn,mySt,myRs);
		}
	}
	
	//reservation_service 테이블에 넣을 함수
	public void in_reservation_service(int reservationnum, String menu) {
		
		if(menu == null) return;
		Connection conn = null;
		Statement mySt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO reservation_service (reservation_num, service_name) VALUES ("+reservationnum+", '"+menu+"')";
			System.out.println(sql);
			mySt=conn.createStatement();
			mySt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt,null);
		}
		
	}
	//예약 된 날짜와시간 0에서 1로 바꿔줄 함수
	public void update_ox(String pdate, String ptime) {
		Connection conn = null;
		Statement mySt = null;
		try {
			
			conn = dataSource.getConnection();
			String sql ="UPDATE possibledatetime SET reservation_ox = reservation_ox +1 where possible_date='"+pdate+"' and possible_time='"+ptime+"'"; 
			mySt = conn.createStatement();
			mySt.executeUpdate(sql);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(conn, mySt,null);
		}
	}
	
	
	//예약확인 내역 
	public ArrayList<reservation_list> reservationcheck(String phone) {
		ArrayList<reservation_list> re = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql= "SELECT user_id, possible_date, possible_time, service_name FROM nail.reservation "
					+ "inner join possibledatetime on reservation.reservation_datenum =possibledatetime.date_num "
					+ "inner join reservation_service on reservation.reservation_number = reservation_service.reservation_num where possible_date>now() and user_id='"+phone+"'";
			
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
			System.out.println(sql);
			
			while(myRs.next()) {		
				String user_id = myRs.getString("user_id");
				String possible_date = myRs.getString("possible_date");
				String possible_time = myRs.getString("possible_time");
				String service_name = myRs.getString("service_name");
				
				reservation_list relist = new reservation_list(user_id, possible_date, possible_time, service_name);
				re.add(relist);
			}
			
			System.out.println(re.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt, myRs);
		}
		return re;
	}
	
	public ArrayList<reservation_list> reservationcheck1(String phone) {
		ArrayList<reservation_list> re = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql= "SELECT user_id, possible_date, possible_time, date_num FROM nail.reservation "
					+ "inner join possibledatetime on reservation.reservation_datenum =possibledatetime.date_num "
					+ "inner join reservation_service on reservation.reservation_number = reservation_service.reservation_num where possible_date>now() and user_id='"+phone+"'";
			
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
			System.out.println(sql);
			
			while(myRs.next()) {		
				String user_id = myRs.getString("user_id");
				String possible_date = myRs.getString("possible_date");
				String possible_time = myRs.getString("possible_time");
				String date_num = myRs.getString("date_num");
				
				reservation_list relist = new reservation_list(user_id, possible_date, possible_time, date_num);
				re.add(relist);
				System.out.println(date_num);
			}
			
			System.out.println(re.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt, myRs);
		}
		return re;
	}
	
	//예약취소
	public ArrayList<reservation_list> reservationcheck2(String phone) {
		ArrayList<reservation_list> re2 = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql= "SELECT date_num, possible_date, possible_time FROM nail.reservation "
					+ "inner join possibledatetime on reservation.reservation_datenum =possibledatetime.date_num "
					+ "inner join reservation_service on reservation.reservation_number = reservation_service.reservation_num where possible_date>now() and user_id='"+phone+"'";
			
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
			System.out.println(sql);
			
			while(myRs.next()) {		
				int date_num = myRs.getInt("date_num");
				String possible_date = myRs.getString("possible_date");
				String possible_time = myRs.getString("possible_time");
				
				reservation_list relist = new reservation_list(date_num, possible_date, possible_time);
				re2.add(relist);
				System.out.println(date_num);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		close(conn, mySt, myRs);
		}
		return re2;
	}
	
	//reservation_num 출력
	public int reservation_num2(String date_num)   {
		int i = 0;
		Connection conn=null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql="select reservation_number from reservation where reservation_datenum='"+date_num+"'";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
			
			myRs.next();
			i = myRs.getInt("reservation_number");
	
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			return i;
		} 
		finally {
			close(conn,mySt,myRs);
		}
	}
	
	//reservation_service에서 정보삭제
	public void delete_reservation_service(int reservation_num) {
		Connection conn = null;
		Statement mySt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM reservation_service WHERE reservation_num="+reservation_num;
			mySt = conn.createStatement();
			mySt.executeUpdate(sql);
			System.out.println(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt, null);
		}
	}
	
	//reservation에서 정보삭제
	public void delete_reservation(String date_num) {
		Connection conn = null;
		Statement mySt = null;
		try {
			conn = dataSource.getConnection();
			String sql="DELETE FROM reservation WHERE reservation_datenum="+date_num;
			mySt = conn.createStatement();
			mySt.executeUpdate(sql);
			System.out.println(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt, null);
		}
	}
	
	//possibleDateTime에서 1에서0으로 수정
	public void update_possibleDateTime(String date_num) {
		Connection conn = null;
		Statement mySt = null;
		try {
			conn = dataSource.getConnection();
			String sql="UPDATE possibledatetime SET reservation_ox = 0 where date_num="+date_num;
			mySt=conn.createStatement();
			mySt.executeUpdate(sql);
			System.out.println(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt, null);
		}
	}
	
	public void change_reservation(String reservation_num, int change_num) {
		Connection conn = null;
		Statement mySt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE reservation SET date_num="+change_num+" where reservation_number ="+reservation_num;
			mySt=conn.createStatement();
			mySt.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt, null);
		}	
	}
	
	public void change_reservation_service() {
		Connection conn = null;
		Statement mySt = null;
		try {
			conn = dataSource.getConnection();
			String sql="";
			mySt=conn.createStatement();
			mySt.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, mySt, null);
		}
	}
}
