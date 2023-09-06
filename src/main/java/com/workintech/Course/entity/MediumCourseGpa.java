package com.workintech.Course.entity;

import org.springframework.stereotype.Component;

@Component
public class MediumCourseGpa implements CourseGpa{
    public int getGpa(){
        return 5;
    }
}
