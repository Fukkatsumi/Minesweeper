package com.minesweeper;

import java.util.Objects;

public class Field {
    public enum State {
        OPEN(' '),
        HIDDEN('\u22A0'),
        MARKED('\u2691');

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
        NUMBER,
        BOMB,
        EMPTY
    }

    private Type type;

    public boolean isBomb() {
        return type == Type.BOMB;
    }

    public boolean isEmpty() {
        return type == Type.EMPTY;
    }

    private char typeValue;

    public char getTypeValue() {
        return typeValue;
    }

    public Field(Type type) {
        this.type = type;
        if (type == Type.EMPTY) {
            this.typeValue = '_';
        } else if (type == Type.BOMB) {
            this.typeValue = '\u25CF';
        }
    }

    public Field(Type type, int number) {
        this.type = type;
        this.typeValue = Integer.toString(number).charAt(0);
    }

    @Override
    public String toString() {
        return "Field{" +
                "state=" + state.toString() +
                ", state value=" + state.getChar() +
                ", type=" + type.toString() +
                ", type value=" + typeValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;
        Field field = (Field) o;
        return typeValue == field.typeValue &&
                state == field.state &&
                type == field.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, type, typeValue);
    }
}
