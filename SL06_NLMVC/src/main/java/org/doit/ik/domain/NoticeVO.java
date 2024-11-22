package org.doit.ik.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
	private String seq;
	private String title;
	private String writer;
	private Date regdate;
	private String filesrc; // 소문자로..
	private int hit;
	private String content;
}