package com.example.demo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by oracle on 8/9/17.
 */

// This repository sits between Contoller (i.e HomeController) and Model (i.e. Course) classes
// This repository talks to the database
// Provides the following methods: findOne(), findAll(), save(), delete()
// <Course, Long> Course:class, Long: Type for the primary key
public interface CourseRepository extends CrudRepository <Course, Long> {
}
