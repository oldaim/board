package org.zerock.board.service;


import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto); //dtoToEntity 메서드를 사용해서 데이터베이스에 dto로 전달된 내용을 entity(객체)
                                 //로 바꿔서 저장하는 기능 ->등록
    PageResultDTO<BoardDTO,Object[]> getList(PageRequestDTO pageRequestDTO); //페이징 처리

    BoardDTO get(Long bno); //조회

    void removeWithReplies(Long bno); //삭제

    void modify(BoardDTO boardDTO);//수정

    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDTO entityToDTO (Board board,Member member ,Long replyCount){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;
    }
}
