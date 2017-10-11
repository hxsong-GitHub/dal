package com.ctrip.platform.dal.dao.sqlbuilder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Just a container for clauses and be type of clause for convenient.
 * 
 * @author jhhe
 *
 */
public class ClauseList extends Clause {
    private List<Clause> list = new LinkedList<>();
    
    /**
     * The build context will share the same context of the builder by default
     */
    protected BuilderContext context;
    
    public void setContext(BuilderContext context) {
        this.context = context;
        for(Clause c: list)
            c.setContext(context);        
    }
    
    public List<Clause> getList() {
        return list;
    }

    public boolean isExpression() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public boolean isBracket() {
        return false;
    }

    public boolean isLeft() {
        return false;
    }

    public boolean isOperator() {
        return false;
    }
    
    public boolean isNot() {
        return false;
    }
    
    public boolean isComma() {
        return false;
    }
    
    public ClauseList add(Clause... clauses) {
        for(Clause c: clauses) {
            c.setContext(context);
            list.add(c);
        }
        return this;
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String build() throws SQLException {
        StringBuilder sb = new StringBuilder();
        for(Clause c: list)
            sb.append(c.build());
        return sb.toString();
    }
}