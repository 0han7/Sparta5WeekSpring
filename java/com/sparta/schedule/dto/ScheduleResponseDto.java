package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String username;
    private String password;
    private String title;
    private String contents;
    private String createAt;
    private String updateAt;


    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUsername();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.password = schedule.getPassword();
        this.createAt = schedule.getCreateAt();
        this.updateAt = schedule.getUpdateAt();
    }

    // 한번 확인 필요
    public ScheduleResponseDto(Long id, String username, String password, String title, String contents) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }

    public ScheduleResponseDto(Long id, String username, String password, String title, String contents, String createAt, String updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.createAt = String.valueOf(createAt);
        this.updateAt = String.valueOf(updateAt);
    }
}
