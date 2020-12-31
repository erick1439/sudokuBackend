package com.erick.backend.Controller;

import com.erick.backend.Service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
        public char [][] getBoard(){
        return sudokuService.getBoard();
    }

//    public Collection<Student> getAllStudents(){
//        return studentService.getAllStudents();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Student getStudentById(@PathVariable("id") int id) {
//        return studentService.getStudentById(id);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deleteStudentById(@PathVariable("id") int id) {
//        studentService.deleteStudentById(id);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void updateStudent(@RequestBody Student student){
//        studentService.updateStudent(student);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void addStudent(@RequestBody Student student) {
//        studentService.addStudent(student);
//    }
}
