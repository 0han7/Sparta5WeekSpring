package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {
    private final JdbcTemplate jdbcTemplate;

    // final이기 때문에 생성자 필요
    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // 일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(savedSchedule);
        return scheduleResponseDto;
    }

    // 일정 조회
    public List<ScheduleResponseDto> getSchedule() {
        // DB 조회
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findAll();
    }


    // 일정 수정
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        // 해당 일정가 DB에 있는지 확인
        Schedule schedule = scheduleRepository.findById(id);
        if (schedule != null) {
            // 일정 내용 수정
            scheduleRepository.update(id, requestDto);
            return id;
        }
        else{
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");

        }

    }

    //일정 삭제
    public Long deleteSchedule(Long id) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        // 해당 일정이 DB에 존재 확인
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null){
            scheduleRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }
}
