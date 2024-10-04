package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 저장 기능
    public Schedule save(Schedule schedule) {

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본키 반환 객체

        String sql = "INSERT INTO schedule (username, password, title, contents, createAt, updateAt) VALUES (?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, schedule.getUsername());
            preparedStatement.setString(2, schedule.getPassword());
            preparedStatement.setString(3, schedule.getTitle());
            preparedStatement.setString(4, schedule.getContents());
            preparedStatement.setString(5, schedule.getCreateAt());
            preparedStatement.setString(6, schedule.getUpdateAt());
            return preparedStatement;
        }, keyHolder);

        // DB INSERT 후 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);
        return schedule;
    }

    // 전체 일정 조회 기능
    public List<ScheduleResponseDto> findAll() {
        // DB조회
        String sql = "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String createAt = rs.getString("createAt");
                String updateAt = rs.getString("updateAt");
                return new ScheduleResponseDto(id, username, password, title, contents, createAt, updateAt);
            }
        });
    }


    // 일정 수정 기능
    public void update(Long id, ScheduleRequestDto requestDto) {

            // 비밀 번호가 아닐 시 수정 실패 알람 추가해야 함(미완)
            String sql = "UPDATE SCHEDULE SET username = ?, password = ?, title = ?, contents = ? , updateAt = ? WHERE id = ?";
            jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents(), LocalDateTime.now().toString(), id);
    }


    // 일정 삭제 기능
    public void delete(Long id) {
            String sql = "DELETE FROM schedule WHERE id = ?";
            jdbcTemplate.update(sql, id);

    }

    public Schedule findById(Long id){
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setUsername(resultSet.getString("username"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContents(resultSet.getString("contents"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
