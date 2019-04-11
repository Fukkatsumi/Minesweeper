package com.minesweeper;

import org.junit.*;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ModelTest {
    private Model model;

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
    public void visible() {
        Point p = new Point(0,0);
        model.fillBoard();
        model.getBoard().get(p).setVisible(true);
        Assert.assertTrue(model.visible(p));
    }

    @Test
    @Ignore
    public void open() {
    }

    @Test
    public void flag() {
        Point p = new Point(0,0);
        model.fillBoard();
        Field actual = model.getBoard().get(p);
        actual.setState(Constants.CHECKED);
        model.flag(p);
        Assert.assertEquals(actual.getState(), Constants.HIDDEN);
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