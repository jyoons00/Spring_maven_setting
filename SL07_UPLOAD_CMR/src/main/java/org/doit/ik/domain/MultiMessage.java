package org.doit.ik.domain;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class MultiMessage { 
	
	private String output;
	
    // <input type="file" name="attach" multiple="multiple">
	// 배열로 받아도 좋지만, private CommonsMultipartFile [] attach;
	 private List<CommonsMultipartFile> attach; // 리스트로 받아도 좋다.

}
