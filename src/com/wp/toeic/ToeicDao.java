package com.wp.toeic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public abstract class ToeicDao {
	protected Connection conn = null;
	
	protected abstract void connectDB() throws SQLException;
	
	protected void disconnectDB() throws SQLException {
		if(conn != null) {
			conn.close();
			conn = null;
		}	
	}
	
	//입력된 ID에 따라 회원 정보 가져오기
	public MemberDO getMembers(String id) throws SQLException {
		MemberDO members = null;
		
		connectDB();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from TOEIC_MEMBER where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
				if(rs.next()) {
					members = new MemberDO();
					members.setId(rs.getString("id"));
					members.setPw(rs.getString("pw"));
					members.setName(rs.getString("name"));
					members.setAge(rs.getInt("age"));
					members.setGender(rs.getString("gender"));
					members.setEmail(rs.getString("email"));
				}
			} catch(SQLException e) {
				throw e;	
			} finally {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
					disconnectDB();
				}
			}
	
			return members;
		}
	
	public int insertMembers(MemberDO members) throws SQLException {
		connectDB();
		
		int result = 0;
		PreparedStatement stmt = null;
		
		try {
			String sql = "insert into TOEIC_MEMBER(id, pw, name, age, gender, email) values(?, ?, ?, ?, ?, ?)";				
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, members.getId());
			stmt.setString(2, members.getPw());
			stmt.setString(3, members.getName());
			stmt.setInt(4, members.getAge());
			stmt.setString(5, members.getGender());
			stmt.setString(6, members.getEmail());
			result = stmt.executeUpdate();	
		} catch(SQLException e) {
			throw e;
		} finally {
			if(stmt != null) {
				stmt.close();
				disconnectDB();
			}
		}
		return result;
	}
	public ToeicQuestionDO getToeics(int q_id) throws SQLException {
		ToeicQuestionDO toeics = null;
		
		connectDB();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from TOEIC_QUESTION where Q_ID=?";
			stmt = conn.prepareStatement(sql);
			//?에 들어가는 첫번째 인덱스로 위치값을 넣어주고 첫번째 ?에 어떤값을 넣을지 설정해줌
			stmt.setInt(1, q_id);
			rs = stmt.executeQuery();
			
				if(rs.next()) {
					toeics = new ToeicQuestionDO();
					toeics.setQ_id(rs.getInt("q_id"));
					toeics.setQ(rs.getString("q"));
					toeics.setA(rs.getString("a"));
					toeics.setB(rs.getString("b"));
					toeics.setC(rs.getString("c"));
					toeics.setD(rs.getString("d"));
				}
			} catch(SQLException e) {
				throw e;	
			} finally {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
					disconnectDB();
				}
				
			}
		return toeics;	
	}
	public List<AnswerDO> getAnswerList() throws SQLException {
		ArrayList<AnswerDO> answerList = null;
		connectDB();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from TOEIC_ANSWER";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				answerList = new ArrayList<AnswerDO>();
				while(rs.next()) {
					AnswerDO tq = new AnswerDO();
					tq.setQ_id(rs.getInt("q_id"));
					tq.setAnswer(rs.getInt("answer"));
					answerList.add(tq);
				}
			}
		} catch(SQLException e) {
			throw e;	
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
				disconnectDB();
			}
		}
	
		return answerList;
	}
	public int insertScore(CountDO counter) throws SQLException {
		connectDB();
		
		int result = 0;
		PreparedStatement stmt = null;
		
		try {
			String sql = "insert into TOEIC_SCORE(ID, COUNT, SCORE) values(?, ?, ?)";				
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, counter.getId());
			stmt.setInt(2, counter.getCount());
			stmt.setInt(3, counter.getScore());
			result = stmt.executeUpdate();	
		} catch(SQLException e) {
			throw e;
		} finally {
			if(stmt != null) {
				stmt.close();
				disconnectDB();
			}
		}
		
		return result;
	}
	public List<CountDO> getScore(String id) throws SQLException {
		ArrayList<CountDO> counterlist = null;
		connectDB();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from TOEIC_SCORE where id=? order by COUNT";
			stmt = conn.prepareStatement(sql);
			//?에 들어가는 첫번째 인덱스로 위치값을 넣어주고 첫번째 ?에 어떤값을 넣을지 설정해줌
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
				if(rs.isBeforeFirst() == true) {
					counterlist = new ArrayList<CountDO>();
					while(rs.next()) {
						CountDO counter = new CountDO();
						counter.setId(rs.getString("id"));
						counter.setScore(rs.getInt("score"));
						counter.setCount(rs.getInt("count"));
						counterlist.add(counter);
					}
					
				}
				else {
					counterlist = new ArrayList<CountDO>();
					while(rs.next()) {
						CountDO counter = new CountDO();
						counter.setId(rs.getString("id"));
						counter.setScore(rs.getInt("score"));
						counter.setCount(rs.getInt("count"));
						counterlist.add(counter);
					}
				}
			} catch(SQLException e) {
				throw e;	
			} finally {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
					disconnectDB();
				}
			}
	
			return counterlist;
	}
}

