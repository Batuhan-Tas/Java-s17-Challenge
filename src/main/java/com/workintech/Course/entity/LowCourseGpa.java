package com.workintech.Course.entity;

import org.springframework.stereotype.Component;

@Component
public class LowCourseGpa implements CourseGpa{
    public int getGpa(){
        return 3;
    }
}
