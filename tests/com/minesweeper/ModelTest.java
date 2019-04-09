package com.minesweeper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ModelTest {
    Model model;

    @Before
    public void init(){
        model = new Model();
        model.createBoard();
    }

    @Test
    public void setBombs() {
        int count = 0;
        model.setBombs();
        for (Field f: model.getBoard().values()) {
            if (f != null) {
                if (f.getType() == Constants.BOMB) {
                    count++;
                }
            }
       }
        Assert.assertEquals(Constants.bombCount, count);
    }

    @Test
    @Ignore
    public void bombsNearby() {
    }

    @Test
    public void fillBoard() {
        model.fillBoard();
        for (Field actual: model.getBoard().values()){
            Assert.assertNotNull(actual);
        }
    }

    @Test
    @Ignore
    public void clicked() {
    }

    @Test
    @Ignore
    public void open() {
    }

    @Test
    @Ignore
    public void flag() {
    }

    @Test
    @Ignore
    public void showSafeArea() {
    }

    @Test
    public void showAllBombs() {
        model.showAllBombs();
        for(Field actual: model.getBoard().values()){
            if(actual.getType() == Constants.BOMB) {
                Assert.assertTrue(actual.isVisible());
            }
        }
    }
}