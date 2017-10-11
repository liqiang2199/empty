package com.empty.cup.sql.sqlinterface;

/**
 * Created by empty cup on 2017/9/28.
 */

public interface IBaseDao<T> {
    long insert(Object entity);
}
