package com.example.sample1.dao;

import com.example.sample1.domain.Todo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TodoDao {


    @Select("""
             SELECT t.id, t.todo, t.inserted, COUNT(f.todoId) numOfFiles
             FROM todo t LEFT JOIN  todoFile f ON t.id = f.todoId
             GROUP BY t.id
             ORDER BY t.id DESC
            """)
    public List<Todo> list();


    @Insert("""
              INSERT INTO todo (todo)
                VALUE (#{todo})  
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(Todo todo);

    @Insert("""
            INSERT INTO todoFile(todoId, name) 
            VALUES (#{todo.id},#{filename})
            """)
    int insertFile(Todo todo, String filename);


    @Select("""
                SELECT name
                FROM todoFile
                WHERE todoId = #{todoId}
            """)
    List<String> selectFilesByTodoId(Integer todoId);
}