package com.minesweeper;

import org.junit.*;

import java.awt.*;


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
    public void bombsNearby() {
        Point p = new Point(1,1);
        model.fillBoard();
        model.bombsNearby(p);
        Assert.assertNotNull(model.getBoard().get(p));
        Assert.assertNotSame(Constants.BOMB, model.getBoard().get(p).getType());

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
    public void open() {
        Point p = new Point(0,0);
        model.fillBoard();
        if(!model.getBoard().get(p).isVisible()){
            model.open(p);
            Assert.assertTrue(model.visible(p));
        }
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
    public void showAllBombs() {
        model.showAllBombs();
        for(Field actual: model.getBoard().values()){
            if(actual.getType() == Constants.BOMB) {
                Assert.assertTrue(actual.isVisible());
            }
        }
    }
}