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
				
				//������ ����ϱ����� ������ �迭�� ����� for��
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
				//������ �̾Ƴ��� for��
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
			
				//������� �κ�
				int sum = 0;
				
				
				/* �ٽɺκ� Getter Setter �ڹ� DO��ü���� ���ϴ� �κи� ��� �������� �ڵ�
				 * Getter Setter�� �迭���� �ϳ��� Ÿ���� ���׸����� �����
				 * List<AnswerDO> answerList = null;
				 * AnswerDO Ŭ����Ÿ�Կ��� ���丸 ������ �迭�ȿ� ������
				 * �迭���� ���� �������� �װ��ȿ��� get���� �����͸� �����;���
				 * ����� ���� ������ ������ ���� ��ġ�ϸ� �ݺ��ϸ鼭 ������ ������ִ� ����
				 *  */
				
				for (int i = 0; i < answerList.size(); i++)
				{
					if(answerList.get(i).getAnswer() == choose.get(i))
					{
						sum = sum + 2;		
					}
				}
				request.setAttribute("myscore", sum);				
			
				//������ TOEIC_SCORE ���̺� ������ �߰�
				//���� �����͸� �߰��ϱ� ���ؼ��� count�� ���ڸ� �˾ƾ���
				String id = (String)session.getAttribute("id");
				
				List<CountDO> countList= null;
				try {
					countList = dao.getScore(id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}
				
				CountDO newScore = new CountDO();
				//���̺��� Ƚ�� �����͸� �������� �ݺ���
				//�ϴ� �ִ��� ������ Ȯ�κ���
				
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

				//�߰��� �����ͷ� ǥ���̱����� �ٽ� �ڷ� ���
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
