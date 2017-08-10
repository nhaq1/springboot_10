package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by oracle on 8/9/17.
 */

@Controller // allows class to handle http requests
public class HomeController {

    @Autowired // instantiates class PersonRepository; makes variable personRepository available to all methods
    CourseRepository courseRepository;

    @RequestMapping("/") // handle incoming URL from browser
    public String listCourses(Model model) {

        //get all courses from database & assign to reference variable "courses"
        model.addAttribute("courses", courseRepository.findAll());

        // load list.html with "courses" variable
        return "list";
    } //end method

    @GetMapping("/add") // handle incoming "/add" URL from browser
    public String addCourse(Model model) {

        // create Person object and assign to "person" variable
        model.addAttribute("course", new Course());

        // load courseform.html with "course" variable
        return "courseform";
    } // end method

    @PostMapping("/process") // handle submit button from html
    public String processForm(@Valid Course course, BindingResult result) {

        // handle form validation
        if (result.hasErrors()){
            // if there are errors in courseform, then go back to courseform
            return "courseform";
        }

        // if there are no errors in form, then save data in DB...
        courseRepository.save(course);

        // ... and go back to main page
        return "redirect:/";
    } // end method

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {

        // get record for one course using "id" value (i.e. primary key) & assign to "course" variable
        model.addAttribute("course", courseRepository.findOne(id));

        // load show.html with "course" variable
        return "show";
    } // end method


    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){

        model.addAttribute("course", courseRepository.findOne(id));
        return "courseform";
    } // end method

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        courseRepository.delete(id);
        return "redirect:/";
    } // end method

} // end class
