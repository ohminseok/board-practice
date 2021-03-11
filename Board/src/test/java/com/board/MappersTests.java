package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MappersTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("테스트");
		params.setContent("테스트");
		params.setWriter("테스트");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 = " + result);
	}
	
	@Test
	void testOfDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long)1);
		
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			System.out.println("=========================");
			System.out.println(boardJson);
			System.out.println("=========================");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		
		params.setTitle("테스트 수정");
		params.setContent("테스트 수정");
		params.setWriter("테스트 수정");
		params.setIdx((long)1);
		
		int result = boardMapper.updateBoard(params);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	void testOfDelete() {
		int result = boardMapper.deleteBoard((long)1);
		
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		        
	}
	
	@Test
	void testMultipleInsert() {
		for (int i=2; i<=50; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i + "번째 제목");
			params.setContent(i + "번째 내용");
			params.setWriter(i + "번째 글쓴이");
			boardMapper.insertBoard(params);
		}
	}
	
	@Test
	void testOfList() {
		int boardTotalCunt = boardMapper.selectBoardTotalCount();
		
		if (boardTotalCunt > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			
			if (CollectionUtils.isEmpty(boardList) ==false) {
				for(BoardDTO board :boardList) {
					System.out.println("======================\n");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("======================\n");
				}
			}
		}
		
		
	}
}
