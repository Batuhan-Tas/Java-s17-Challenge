package com.workintech.Course.controller;

import com.workintech.Course.entity.Course;
import com.workintech.Course.entity.CourseGpa;
import com.workintech.Course.exceptions.CourseException;
import com.workintech.Course.exceptions.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses;
    private CourseGpa lowGpa;
    private CourseGpa mediumGpa;
    private CourseGpa highGpa;

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowGpa, @Qualifier("mediumCourseGpa") CourseGpa mediumGpa, @Qualifier("highCourseGpa") CourseGpa highGpa) {
        this.lowGpa = lowGpa;
        this.mediumGpa = mediumGpa;
        this.highGpa = highGpa;
    }

    @PostConstruct
    public void init(){
        courses = new ArrayList<>();
    }

    @GetMapping("/")
    public List<Course> get(){
        return courses;
    }

    @GetMapping("/{name}")
    public List<Course> getCourse(@PathVariable String name){
        List<Course> foundCourses = courses.stream().filter(course -> course.getName().equals(name)).collect(Collectors.toList());
        if(foundCourses.isEmpty()){
            throw new CourseException("Name is not valid", HttpStatus.BAD_REQUEST);
        }
        return foundCourses;
    }

    @PostMapping("/")
    public Course post(@RequestBody Course course){
        CourseValidation.checkValidation(course);
        courses.add(course);
        return course;
    }

    @PutMapping("/{id}")
    public Course update(@RequestBody Course course, @PathVariable int id){
        Optional<Course> foundCourses = courses.stream().filter(courseUp -> courseUp.getId() == id).findFirst();
        if(foundCourses.isPresent()){
           int index = courses.indexOf(foundCourses.get());
           courses.set(index,course);
        }
        return courses.get(id);
    }

    @DeleteMapping
    public Course delete(@PathVariable int id){
        Optional<Course> foundCourses = courses.stream().filter(courseUp -> courseUp.getId() == id).findFirst();
        if(foundCourses.isPresent()){
            int index = courses.indexOf(foundCourses.get());
            courses.remove(index);
            System.out.println("Successfully deleted...");
        }
        return null;
    }



}
