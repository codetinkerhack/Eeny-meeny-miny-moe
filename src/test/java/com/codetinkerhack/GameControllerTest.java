package com.codetinkerhack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by evgeniys on 3/05/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @MockBean
    private GameTreeListImpl game;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCallGameWhenControllerIsCalled() throws Exception {
        List<Integer> sequence = new LinkedList<>();
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);

        Result result = new Result(4, sequence);

        when(game.runSimulation(4, 1)).thenReturn(result);

        mockMvc.perform(get("/game?n={n}&k={k}", 4, 1));

        verify(game, atLeastOnce()).runSimulation(4, 1);
    }

    @Test
    public void shouldReturnResultWhenControllerIsCalled() throws Exception {
        List<Integer> sequence = new LinkedList<>();
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);

        Result result = new Result(4, sequence);

        when(game.runSimulation(4, 1)).thenReturn(result);

        // below json comparison may need ot be reworked
        mockMvc.perform(get("/game?n={n}&k={k}", 4, 1)).andExpect(status().isOk())
                .andExpect(content().json("{winner : 4, eliminationSequence: [1, 2, 3]}"));
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled() throws Exception {
        when(game.runSimulation(4, -1)).thenThrow(new IllegalArgumentException());
        mockMvc.perform(get("/game?n={n}&k={k}", 4, -1)).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled1() throws Exception {

        when(game.runSimulation(-4, 1)).thenThrow(new IllegalArgumentException());
        mockMvc.perform(get("/game?n={n}&k={k}", -4, 1)).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled2() throws Exception {

        when(game.runSimulation(0, 1)).thenThrow(new IllegalArgumentException());
        mockMvc.perform(get("/game?n={n}&k={k}", 0, 1)).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorResponseWhenBadArgumentsCalled3() throws Exception {

        when(game.runSimulation(1, 0)).thenThrow(new IllegalArgumentException());
        mockMvc.perform(get("/game?n={n}&k={k}", 1, 0)).andExpect(status().is4xxClientError());
    }

}