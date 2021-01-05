package com.erick.backend.Controller;

import org.json.*;
import com.erick.backend.Entity.Table;
import com.erick.backend.Service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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



    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public char [][] solveBoard(@RequestBody String json) {

        char [][] board = new char [9][9];

        JSONObject obj = new JSONObject(json);
        JSONArray arr = (JSONArray) obj.get("array");

        char [] str = arr.toString().toCharArray();
        char [] temp = new char [81];

        for (int i = 0, j = 0; i < str.length; i++)
        {
            if (str[i] == '.')
                temp[j++] = '.';

            else if (Character.isDigit(str[i]))
                temp[j++] = str[i];
        }

        int index = 0;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = temp[index++];

        sudokuService.solver(board);

        return board;
    }
}
