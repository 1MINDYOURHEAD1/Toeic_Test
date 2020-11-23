package com.wp.toeic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/toeic/*")
public class ToeicManagerControl extends HttpServlet {
	private ArrayList<Integer> choose = new ArrayList<Integer>();
	private static final long serialVersionUID = 1L;
	
    public ToeicManagerControl() {
        super();
    }
    
//    Scanner stdIn = new Scanner(System.in);
//    int input = stdIn.nextInt();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		ToeicDao dao = (ToeicDao)session.getAttribute("dao");
		
		if(dao == null) {
			ServletContext context = getServletContext();
			dao = new ToeicDbcpDao(context.getInitParameter("dbcp_resource_name"));
			session.setAttribute("dao", dao);
		}
		
		String action = request.getParameter("action");
		String pathInfo = request.getPathInfo();
		String viewName = null;
		
		
		if(pathInfo != null && pathInfo.length() > 1) {
			if(pathInfo.equals("/login")) {
				viewName = "/views/Login_form.jsp";
			}
		}
		else if(action != null) {
			if(action.equals("join_form")) {
				viewName = "/views/Join_form.jsp";
			}
			else if(action.equals("join")) {
				MemberDO members = new MemberDO();
				members.setId(request.getParameter("id"));
				members.setPw(request.getParameter("pw"));
				members.setName(request.getParameter("name"));
				members.setAge(Integer.parseInt(request.getParameter("age")));
				members.setGender(request.getParameter("gender"));
				members.setEmail(request.getParameter("email"));
				try {
					dao.insertMembers(members);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}
				viewName = "redirect:/toeic/login";
			}
			
			else if(action.equals("login")) {
				String id = request.getParameter("ID");
				String pw = request.getParameter("PW");
				
				MemberDO members = null;

				try {
					members = dao.getMembers(id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}
				
				if(members == null) {
					viewName = "redirect:/toeic/login";
				}
				else {
					String pw2 = members.getPw().toString();
					if(pw2.equals(pw)) {
						session.setAttribute("id", id);
						
						List<CountDO> firstList = null;
						try {
							firstList = dao.getScore(id);
						} catch (SQLException e) {
							e.printStackTrace();
							throw new ServletException(e.getMessage());
						}
							
						if(firstList.isEmpty()) {
							request.setAttribute("test", 0);
						}
						else if(firstList.size() > 0) {
							request.setAttribute("test", 1);
							request.setAttribute("first_list", firstList);
						}
						
						viewName = "/views/Member.jsp";
					}
					else {
						viewName = "redirect:/toeic/login";
					}
				}
			}
			else if(action.equals("start_test")) {

				viewName = "/views/Test_rule.jsp";
			}
			else if(action.equals("test1")) {
				request.getSession(false);
				
				for(int i=1; i<=3; i++) {
					ToeicQuestionDO questions = null;
					try {
						questions = dao.getToeics(i);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}
					
					request.setAttribute("toeic_list" + i , questions);			
				}
				
				viewName = "/views/Test1.jsp";
			}	
			else if(action.equals("test2")) {
				request.getSession(false);
				
				//점수를 계산하기위한 선택지 배열을 만드는 for문
				for(int i=1; i<=3; i++) {
					int j = Integer.parseInt(request.getParameter("test" + i));
					choose.add(j);
				}
				
				for(int i=4; i<=6; i++) {
					ToeicQuestionDO questions = null;
					try {
						questions = dao.getToeics(i);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}
					
					request.setAttribute("toeic_list" + i , questions);			
				}
				
				viewName = "/views/Test2.jsp";
			}
			else if(action.equals("test3")) {
				request.getSession(false);
				
				for(int i=4; i<=6; i++) {
					int j = Integer.parseInt(request.getParameter("test" + i));
					choose.add(j);
				}
				//문제를 뽑아내는 for문
				for(int i=7; i<=9; i++) {
					ToeicQuestionDO questions = null;
					try {
						questions = dao.getToeics(i);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}
					
					request.setAttribute("toeic_list" + i , questions);			
				}
						
				viewName = "/views/Test3.jsp";
			}
			else if(action.equals("test4")) {
				request.getSession(false);
				
				for(int i=7; i<=9; i++) {
					int j = Integer.parseInt(request.getParameter("test" + i));
					choose.add(j);
				}
				
				for(int i=10; i<=12; i++) {
					ToeicQuestionDO questions = null;
					try {
						questions = dao.getToeics(i);
						
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}
					
					request.setAttribute("toeic_list" + i , questions);			
				}
				
				viewName = "/views/Test4.jsp";
			}
			else if(action.equals("test5")) {
				request.getSession(false);
				
				for(int i=10; i<=12; i++) {
					int j = Integer.parseInt(request.getParameter("test" + i));
					choose.add(j);
				}
				
				for(int i=13; i<=15; i++) {
					ToeicQuestionDO questions = null;
					try {
						questions = dao.getToeics(i);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}
					
					request.setAttribute("toeic_list" + i , questions);		
				}
		
				viewName = "/views/Test5.jsp";
			}
			else if(action.equals("result")) {
				request.getSession(false);
				
				for(int i=13; i<=15; i++) {
					int j = Integer.parseInt(request.getParameter("test" + i));
					choose.add(j);
				}
				
				List<AnswerDO> answerList = null;
				
				try {
					answerList = dao.getAnswerList();
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}
			
				//점수출력 부분
				int sum = 0;
				
				
				/* 핵심부분 Getter Setter 자바 DO객체에서 원하는 부분만 골라서 가져오는 코드
				 * Getter Setter는 배열안의 하나의 타입인 제네릭으로 선언됨
				 * List<AnswerDO> answerList = null;
				 * AnswerDO 클래스타입에서 정답만 가져옴 배열안에 넣은후
				 * 배열안의 값을 가져온후 그값안에서 get으로 데이터를 가져와야함
				 * 정답과 내가 선택한 내용의 값과 일치하면 반복하면서 점수를 계산해주는 원리
				 *  */
				
				for (int i = 0; i < answerList.size(); i++)
				{
					if(answerList.get(i).getAnswer() == choose.get(i))
					{
						sum = sum + 2;		
					}
				}
				request.setAttribute("myscore", sum);				
			
				//점수를 TOEIC_SCORE 테이블에 데이터 추가
				//점수 데이터를 추가하기 위해서는 count의 숫자를 알아야함
				String id = (String)session.getAttribute("id");
				
				List<CountDO> countList= null;
				try {
					countList = dao.getScore(id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}
				
				CountDO newScore = new CountDO();
				//테이블에서 횟수 데이터만 가져오는 반복문
				//일단 있는지 없는지 확인부터
				
				if(countList.isEmpty()) {
					newScore = new CountDO();
					newScore.setId(id);
					newScore.setCount(1);
					newScore.setScore(sum);
					try {
						dao.insertScore(newScore);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}
				}
				else {					
					newScore = new CountDO();
					newScore.setId(id);
					newScore.setCount(countList.size() + 1);
					newScore.setScore(sum);
					try {
						dao.insertScore(newScore);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}
				}

				//추가된 데이터로 표를뽑기위한 다시 자료 출력
				List<CountDO> scoreList = null;
				try {
					scoreList = dao.getScore(id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}
				
				request.setAttribute("choose_list", choose);
				request.setAttribute("answer_list", answerList);
				request.setAttribute("score_list", scoreList);
				
				viewName = "/views/Result.jsp";
				
			}
			else if(action.equals("End")) {
				choose.clear();
				session.invalidate();
				viewName="redirect:/toeic/login";	
			}
		}
		
		if(viewName != null) {
			if(viewName.contains("redirect:")) {
				String location = viewName.split(":")[1];
				response.sendRedirect(request.getContextPath() + location);
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 			
		doGet(request, response);
	}
}
