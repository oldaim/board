package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;
import org.zerock.board.repository.ReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replydTO){
        Reply reply =dtoToEntity(replydTO);

        replyRepository.save(reply);

        return reply.getRno();
    } //댓글 등록

    @Override
    public List<ReplyDTO> getList(Long bno){ //댓글 목록
        List<Reply> result = replyRepository
                .getRepliesByBoardOrderByRno(Board.builder().bno(bno)
                        .build());

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO){
        Reply reply =dtoToEntity(replyDTO);

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno){
        replyRepository.deleteById(rno);
    }
}
