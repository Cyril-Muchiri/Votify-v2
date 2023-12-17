package com.votifysoft.app.action;

public interface FieldMapper<T> {
    T map(String fieldValue);

}
