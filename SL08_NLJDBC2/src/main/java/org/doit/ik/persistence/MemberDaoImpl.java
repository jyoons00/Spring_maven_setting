package org.doit.ik.persistence;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	// 추가됨
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	@Override
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = " SELECT * "
					+ " FROM member "
					+ " WHERE id = :id ";

		/* return this.jdbcTemplate.queryForObject(
				sql, 
				new Object[] {id}, 
				new BeanPropertyRowMapper<MemberVO>(MemberVO.class)); 
		*/
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();		
		parameterSource.addValue("id", id);	
		return this.npJdbcTemplate.queryForObject(sql, parameterSource, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		
	}
	
	@Override
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException
	{
		String sql = "INSERT INTO MEMBER"
				+ " (id, pwd, name, gender, birth, is_lunar, cphone , email, habit , regdate) "
				+ " VALUES( :id, :pwd, :name, :gender, :brith, :is_lunar, :cphone, :email, :habit, SYSDATE) ";
		
		/*
		return this.jdbcTemplate.update(sql, 
				member.getId(), 
				member.getPwd(), 
				member.getName(), 
				member.getGender()
				,member.getBirth(), 
				member.getIs_lunar(), 
				member.getCphone(), 
				member.getEmail(), 
				member.getEmail(), 
				member.getHabit(), 
				member.getRegdate());
		*/
		
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
		 return this.npJdbcTemplate.update(sql, parameterSource);
		
	}
}
