package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.BookDto;
import test.util.DbcpBean;


public class BookDao {
	
	
	public BookDto getByNum(int num) {
		//MemberDto 객체의 참조값을 담을 지역변수를 미리 만든다
		BookDto dto=null;
		
		//필요한 객체를 담을 지역변수를 미리 만든다
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql문
			String sql = """
					SELECT name, author, publisher
					FROM book
					WHERE num=?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstmt.setInt(1, num);
			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			//반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			while (rs.next()) {
				//객체 생성 후
				dto=new BookDto();
				//회원 한명의 정보를 담는다
				dto.setNum(num); //번호는 매개변수에 있는 내용을 담으면 된다
				dto.setName(rs.getString("name"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dto;
	}
	
	//회원 전체 목록을 select 해서 List 에 담아서 리턴하는 메소드
	public List<BookDto> selectAll(){
		//회원정보를 누적시킬 ArrayList 객체 미리 준비하기
		List<BookDto> list=new ArrayList<>();
		
		//필요한 객체를 담을 지역변수를 미리 만든다
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql문
			String sql = """
					SELECT num, name, author, publisher
					FROM book
					ORDER BY num DESC
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			
			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			//반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			while (rs.next()) {
				//커서가 위치한 곳의 회원정보를 저장할 MemberDto 객체 생성				
				BookDto dto=new BookDto();				
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				//회원 한명의 정보가 담긴 새로운 MemberDto 객체의 참조값을 List에 누적시키기
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
	
	//회원 한명의 정보를 DB에서 수정하고 성공여부를 리턴하는 메소드
	public boolean update(BookDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//변화된 row의 갯수를 담을 변수 선언하고 0으로 초기화
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE book
					SET name=?, author=?, publisher=?
					WHERE num=?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getPublisher());
			pstmt.setInt(4, dto.getNum());
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}

		}
		// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}
	
	//회원 한명의 정보를 DB에서 삭제하고 성공여부를 리턴하는 메소드
	public boolean deleteByNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//변화된 row의 갯수를 담을 변수 선언하고 0으로 초기화
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					DELETE FROM book
					WHERE num=?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setInt(1, num);
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}

		}
		// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}
	
	// 회원 한 명의 정보를 DB에 저장하고 성공 여부를 리턴하는 메소드
	public boolean insert(BookDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//변화된 row의 갯수를 담을 변수 선언하고 0으로 초기화
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					INSERT INTO book
					(num, name, author, publisher)
					VALUES(book_seq.NEXTVAL, ?, ?, ?)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getPublisher());
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}

		}
		// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}
}
