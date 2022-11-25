package com.bitstudy.app.service;

import com.bitstudy.app.dao.Ex05_BoardDao;
import com.bitstudy.app.domain.Ex01_BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Ex06_BoardService {
    @Autowired
    Ex05_BoardDao boardDao;

    public int getCount() {
        return boardDao.count();
    }
    public int write(Ex01_BoardDto boardDto) {
        return boardDao.insert(boardDto);
    }
    public Ex01_BoardDto read(Integer bno) {
        Ex01_BoardDto boardDto = boardDao.select(bno);
        boardDao.increaseViewCount(bno);
        return boardDto;
    }
    public List<Ex01_BoardDto> getList() {
        return boardDao.selectAll();
    }
    public List<Ex01_BoardDto> getPage(Map map) {
        return boardDao.selectPage(map);
    }
    public int update(Ex01_BoardDto boardDto) {
        return boardDao.update(boardDto);
    }
    public int remove(Map map) {
        return boardDao.delete(map);
    }
}
