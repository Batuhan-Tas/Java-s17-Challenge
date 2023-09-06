package com.workintech.Course.exceptions;

import com.workintech.Course.entity.Course;
import org.springframework.http.HttpStatus;

public class CourseValidation {
    public static void checkValidation(Course course){
        if(course.getCredit() < 0 || course.getCredit() >4){
            throw new CourseException("Course is not valid", HttpStatus.BAD_REQUEST);
        }
    }
}
