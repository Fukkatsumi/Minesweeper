package com.minesweeper;

public class Field {
    public enum State {
        OPEN(' '),
        HIDDEN('*'),
        MARKED('^');

        private char symbol;

        State(char symbol) {
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

    private State state = State.HIDDEN;

    public State getState() {
        return state;
    }

    public void mark() {
        if (this.state == State.HIDDEN) {
            this.state = State.MARKED;
        } else if (this.state == State.MARKED) {
            this.state = State.HIDDEN;
        }
    }

    public boolean isMarked() {
        return state == State.MARKED;
    }

    public void open() {
        if (state != State.MARKED) {
            state = State.OPEN;
        }
    }

    public boolean isOpen() {
        return state == State.OPEN;
    }

    public enum Type {
        NUMBER('0'),
        BOMB('b'),
        EMPTY('_');

        private char symbol;

        Type(char symbol) {
            this.symbol = symbol;
        }

        public Type setNumber(int number) {
            if (this == NUMBER) {
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

    private Type type;

    public Type getType() {
        return type;
    }

    public boolean isBomb() {
        return type == Type.BOMB;
    }

    public boolean isEmpty() {
        return type == Type.EMPTY;
    }

    public Field(Type type) {
        this.type = type;
    }
}
