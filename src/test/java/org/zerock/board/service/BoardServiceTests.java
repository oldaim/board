package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){ //등록
        BoardDTO dto = BoardDTO.builder()
                .title("Test..")
                .content("Test..")
                .writerEmail("user01aaa.com")
                .build();

        Long bno =boardService.register(dto);
    }

    @Test
    public void testList(){ //페이징 처리
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO,Object[]> result =boardService.getList(pageRequestDTO);

        for(BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testGet(){ //조회
        Long bno =100L;

        BoardDTO boardDTO =boardService.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void testRemove(){ //삭제
        Long bno =3L;

        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify(){//수정

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경합니다")
                .content("내용 변경합니다")
                .build();

        boardService.modify(boardDTO);
    }
}
