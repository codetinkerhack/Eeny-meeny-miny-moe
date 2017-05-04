package com.codetinkerhack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by evgeniys on 3/05/2017.
 */
@RestController
public class GameController {

        @Autowired
        GameTreeListImpl game;

        @RequestMapping(method=GET, path="/game")
        public Result run(@RequestParam(value="n") Integer n, @RequestParam(value="k") Integer k) {

            Result result = game.runSimulation(n, k);

            return result;
        }

        @ResponseStatus(value= HttpStatus.BAD_REQUEST,
                reason="Bad parameters")  // 400
        @ExceptionHandler(IllegalArgumentException.class)
        public void badParameters() {
        }
}
