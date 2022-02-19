package com.newlec.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.notice.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* 
		  /notice/list ��û�� ���� db���� �����͸� ������ �ͼ� list�� ������� ��.
		  �� ���� 5���� ������
		  view�� �ѱ�� �����ʹ� req�� ��Ƽ� �Ѱ���
		  ������ ������ req�� ��Ƽ� �Ѱ���
		 */
		
		//pager�Ķ����
		String pageNum_ =( req.getParameter("p") != null && req.getParameter("p") != "")? req.getParameter("p"): "1";
		int pageNum = Integer.parseInt(pageNum_);
		
		
		//jdbc ����
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String sql = "SELECT * FROM NOTICE";				
		String user = "NEWLEC";
		String password ="dkdk123";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement(); 			
			ResultSet rs = st.executeQuery(sql);
			
			List<Notice> list = new ArrayList<Notice> ();
			
			while(rs.next()) {
				int  id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_Id = rs.getString("writer_id");
				String content = rs.getString("content"); 
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				Notice notice = new Notice(id, title, writer_Id, content, regdate, hit, files);
				list.add(notice);
			}
			
			req.setAttribute("list", list);
			req.setAttribute("pageNum", pageNum);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/notice/list.jsp");
			dispatcher.forward(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


    	}
	
}
