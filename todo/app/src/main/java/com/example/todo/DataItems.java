package com.example.todo;

import java.util.List;

public class DataItems {
    private List<TodoCard> todoCards;

    List<TodoCard> getTodoCards() {
        return todoCards;
    }

    void setTodoCards(List<TodoCard> todoCards) {
        this.todoCards = todoCards;
    }
}
