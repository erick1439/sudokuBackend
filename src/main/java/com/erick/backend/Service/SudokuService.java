package com.erick.backend.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
import org.springframework.web.bind.annotation.RestController;

import java.lang.*;

@RestController
public class SudokuService {

    public char [][] getBoard()
    {
        String FORMAT = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
        int size = 9;
        int level = (int)(Math.random() % 3 + 1);

        String url = String.format(FORMAT, size, level);
        String response = new SudokuService().sendGet(url);
        System.out.println(response);

        return parser(response);
    }

    public String sendGet(String urlString)
    {
        HttpURLConnection con = null;

        try
        {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null)
                response.append(line);

            return response.toString();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (con != null)
                con.disconnect();
        }

        return null;
    }

    public static char[][] parser(String json)
    {
        char [][] array = new char[9][9];

        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length; j++)
                array[i][j] = '.';

        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("squares");

        for (int i = 0; i < arr.length(); i++)
        {
            int x = arr.getJSONObject(i).getInt("x");
            int y = arr.getJSONObject(i).getInt("y");
            int value = arr.getJSONObject(i).getInt("value");

            array[x][y] = (char)(value + 48);
        }

        return array;
    }

    public static boolean isValid(char[][] board, int row, int col, char c)
    {
        for(int i = 0; i < 9; i++)
        {
            if(board[i][col] != '.' && board[i][col] == c)
                return false; //check row

            if(board[row][i] != '.' && board[row][i] == c)
                return false; //check column

            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
                return false; //check 3*3 block
        }

        return true;
    }

    public static boolean checkBoard(char [][] board)
    {
        boolean [][] boxes = new boolean [9][9];

        for (int i = 0; i < 9; i++)
        {
            boolean [] row = new boolean [9];
            boolean [] col = new boolean [9];

            for (int j = 0; j < 9; j++)
            {
                if (board[i][j] == '.');

                else if (row[(board[i][j] - '0') - 1] == false)
                    row[(board[i][j] - '0') - 1] = true;

                else
                    return false;

                if (board[j][i] == '.');

                else if (col[(board[j][i] - '0') - 1] == false)
                    col[(board[j][i] - '0') - 1] = true;

                else
                    return false;

                int box = (i / 3) * 3 + (j / 3);

                if (board[i][j] == '.');

                else if (boxes[box][(board[i][j] - '0') - 1] == false)
                    boxes[box][(board[i][j] - '0' - 1)] = true;

                else
                    return false;
            }
        }

        return true;
    }

    public static boolean solver(char [][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if (board[i][j] == '.')
                {
                    for (char c = '1'; c <= '9'; c++)
                    {
                        if (isValid(board, i, j, c))
                        {
                            board[i][j] = c;

                            if (solver(board))
                                return true;

                            else {
                                board[i][j] = '.';
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

}



