package com.erick.backend.Controller;

import com.erick.backend.Service.WordSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/word-search")
public class WordSearchController {

    @Autowired
    private WordSearchService wordSearchService;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public char[][] getWordSearch() { return wordSearchService.getMatrix(); }

}

