package com.sb.sbadmin.repository;

import com.sb.sbadmin.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByGubun(String gubun);

    List<Board> findTop5AllByGubun(String gubun);

    List<Board> findAllByGubunOrderByIdDesc(String gubun);
}
