package com.empty.cup.sql.sqlinterface;

/**
 * Created by empty cup on 2017/9/28.
 */

public class BaseDao<T> implements IBaseDao<T> {
    @Override
    public long insert(Object entity) {
        return 0;
    }
}
