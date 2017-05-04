package com.codetinkerhack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by evgeniys on 3/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnOkAndResult() throws Exception {
        List<Integer> sequence = new LinkedList<>();
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);

        Result resultExpected = new Result(4, sequence);

        ResponseEntity<Result> result = restTemplate.getForEntity("/game?n={n}&k={k}", Result.class, 4, 1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(resultExpected.getWinner(), result.getBody().getWinner());
        assertEquals(resultExpected.getEliminationSequence(), result.getBody().getEliminationSequence());
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled() throws Exception {

        ResponseEntity<Result> result = restTemplate.getForEntity("/game?n={n}&k={k}", Result.class, 4, -1);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled1() throws Exception {
        ResponseEntity<Result> result = restTemplate.getForEntity("/game?n={n}&k={k}", Result.class, -4, 1);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled2() throws Exception {
        ResponseEntity<Result> result = restTemplate.getForEntity("/game?n={n}&k={k}", Result.class, 0, 1);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled3() throws Exception {
        ResponseEntity<Result> result = restTemplate.getForEntity("/game?n={n}&k={k}", Result.class, 4, 0);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

}