package com.erick.backend.Controller;

import com.erick.backend.Service.WordSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/word-search")
public class WordSearchController {

    private String [] list;

    @Autowired
    private WordSearchService wordSearchService;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public char[][] getWordSearch() {

        wordSearchService.setList(this.list);
        wordSearchService.cleanMatrix(wordSearchService.getMatrix());
        wordSearchService.setPosition();
        wordSearchService.randomABC(wordSearchService.getMatrix());

        return wordSearchService.getMatrix();

    }


    @RequestMapping(path="/getList", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public String [] getList() {

        this.list = wordSearchService.generateList();

        return this.list; }

}

