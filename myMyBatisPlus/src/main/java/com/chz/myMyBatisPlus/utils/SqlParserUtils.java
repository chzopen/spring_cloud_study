package com.chz.myMyBatisPlus.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.IsBooleanExpression;
import net.sf.jsqlparser.schema.Column;

@Slf4j
public class SqlParserUtils
{

    public static BinaryExpression andTenantIdExpression(Expression where, Long tenantId)
    {
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("tenant_id"));
        equalsTo.setRightExpression(new LongValue(tenantId));
        if (null != where) {
            if (where instanceof OrExpression) {
                return new AndExpression(equalsTo, new Parenthesis(where));
            } else {
                return new AndExpression(equalsTo, where);
            }
        }
        return equalsTo;
    }

    public static Expression andDeletedExpression(Expression where, Boolean deleted)
    {
        IsBooleanExpression isBooleanExpression = new IsBooleanExpression();
        isBooleanExpression.setLeftExpression(new Column("deleted"));
        isBooleanExpression.setIsTrue(deleted);
        if (null != where) {
            if (where instanceof OrExpression) {
                return new AndExpression(isBooleanExpression, new Parenthesis(where));
            } else {
                return new AndExpression(isBooleanExpression, where);
            }
        }
        return isBooleanExpression;
    }

}
