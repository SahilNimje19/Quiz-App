package com.Quiz_App.repository;

import com.Quiz_App.model.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository

public class QuizRepository {
    private final JdbcTemplate jdbcTemplate;

    public QuizRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<QuizQuestion> rowMapper = (rs, rowNum) -> {
        QuizQuestion q = new QuizQuestion();
        q.setId(rs.getInt("id"));
        q.setQuestion(rs.getString("question"));
        q.setOptionA(rs.getString("option_a"));
        q.setOptionB(rs.getString("option_b"));
        q.setOptionC(rs.getString("option_c"));
        q.setOptionD(rs.getString("option_d"));
//        q.setCorrectOption(rs.getString("correct_option").charAt(0));
        String.valueOf(q.getCorrectOption());
        return q;

    };

    public List<QuizQuestion> findAll() {
        return jdbcTemplate.query("SELECT * from quiz_question", rowMapper);
    }
    public QuizQuestion findById(int id) {
        return jdbcTemplate.queryForObject("select * from quiz_question where id= ?", rowMapper,id);
    }
    public void save(QuizQuestion q) {
        jdbcTemplate.update(
                "Insert into quiz_question (question, option_a, option_b, option_c, option_d, correct_option) values (?,?,?,?,?,?) ",
                q.getQuestion(),
                q.getOptionA(),
                q.getOptionB(),
                q.getOptionC(),
                q.getOptionD(),
                String.valueOf(q.getCorrectOption())
        );
    }
    public void saveresult(String username, int score, int total) {
        jdbcTemplate.update(
                "INSERT INTO quiz_result (username, score, total) values (?,?,?)",
                    username, score,total
        );
    }
}
