package org.doit.ik.persistence;

import java.io.File;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.doit.ik.domain.MemberVO;
import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	// 추가됨
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	//공지사항 갯수
	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT "
				+ " FROM NOTICES "
				+ " WHERE "+field+" LIKE :query ";
	
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();		
		parameterSource.addValue("query", query);	
		return this.npJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
	}
	
	//공지사항 목록
	@Override
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException
	{					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = " SELECT * FROM ";
		sql += " (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE "+field+" LIKE :query ORDER BY REGDATE DESC) N) ";
		sql += " WHERE NUM BETWEEN :srow AND :erow ";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("query", "%"+query+"%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		return this.npJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}
	
	@Override
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		
		String sql = " DELETE FROM NOTICES"
				+        " WHERE SEQ = :seq ";

		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();		
		parameterSource.addValue("seq", seq);	
		return this.npJdbcTemplate.update(sql, parameterSource);
		
	}
	
	
	@Override
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{
		

		String sql = "UPDATE NOTICES SET TITLE=:title, CONTENT=:content, FILESRC=:filesrc WHERE SEQ=:seq";
	
		/* 이렇게 안해도 된다는 것
	      MapSqlParameterSource parameterSource = new MapSqlParameterSource();
	      parameterSource.addValue("title", notice.getTitle());
	      parameterSource.addValue("content", notice.getContent());
	      parameterSource.addValue("filesrc", notice.getFilesrc());
	      parameterSource.addValue("seq", notice.getSeq());      
	      
	      return this.npJdbcTemplate.update(sql,  parameterSource );
	      */	
		
		 SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(notice);
		 return this.npJdbcTemplate.update(sql, parameterSource);
				 
	}
	
	
	@Override
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = " SELECT * FROM NOTICES WHERE SEQ = :seq ";

			
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();		
		parameterSource.addValue("seq", seq);	
		return this.npJdbcTemplate.queryForObject(sql, parameterSource, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
		
	}

	@Override
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {

		String sql =      " INSERT INTO NOTICES "
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + " (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc) ";

		
		
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(notice);
		 return this.npJdbcTemplate.update(sql, parameterSource);
	}
	
	
}
