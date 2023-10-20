package com.example.sample1.service;

import com.example.sample1.dao.TodoDao;
import com.example.sample1.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TodoService {
    private final TodoDao dao;

    public List<Todo> list() {
        return dao.list();
    }

    public boolean insert(Todo todo) {
        return dao.insert(todo) ==1;
    }
}
