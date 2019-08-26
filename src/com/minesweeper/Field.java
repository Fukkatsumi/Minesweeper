package com.minesweeper;

public class Field {
    public enum Type {
        NUMBER('0'),
        BOMB('b'),
        EMPTY('_');

        private char symbol;

        Type(char symbol){
            this.symbol = symbol;
        }

        public Type setNumber(int number){
            if(this == NUMBER) {
                symbol = Integer.toString(number).charAt(0);
            }
            return this;
        }

        public char getChar() {
            return symbol;
        }

        @Override
        public String toString() {
            return "Type{" +
                    "symbol='" + symbol + '\'' +
                    '}';
        }
    }

    public enum State{
        OPEN (' '),
        HIDDEN ('*'),
        CHECKED ('^');

        private char symbol;

        State(char symbol){
            this.symbol = symbol;
        }

        public char getChar() {
            return symbol;
        }

        @Override
        public String toString() {
            return "State{" +
                    "symbol='" + symbol + '\'' +
                    '}';
        }
    }

    private Type type;
    private State state = State.HIDDEN;

    public Type getType() {
        return type;
    }

    public State getState() {
        return state;
    }

    public void check(){
        if (this.state == State.HIDDEN){
            this.state = State.CHECKED;
        } else if (this.state == State.CHECKED){
            this.state = State.HIDDEN;
        }
    }

    public boolean isChecked(){
        return state == State.CHECKED;
    }

    public void open(){
        if(state != State.CHECKED) {
            state = State.OPEN;
        }
    }

    public boolean isOpen() {
        return state == State.OPEN;
    }

    public boolean isBomb(){
        return type == Type.BOMB;
    }

    public boolean isEmpty(){
        return type == Type.EMPTY;
    }

    public Field(Type type) {
        this.type = type;
    }
}
