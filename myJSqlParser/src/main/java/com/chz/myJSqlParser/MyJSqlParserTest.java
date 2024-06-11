package com.chz.myJSqlParser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.IsBooleanExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

public class MyJSqlParserTest {

    public static void main(String[] args) throws JSQLParserException
    {
        Select stmt = (Select) CCJSqlParserUtil.parse(" SELECT col1 AS a, col2 AS b" +
                " FROM table_name " +
                " WHERE col_1 = 10" +
                " AND col_2 = 20");

        System.out.println("before ::: " + stmt.toString());

        PlainSelect plainSelect = (PlainSelect)stmt.getSelectBody();
        Expression whereExpression = plainSelect.getWhere();

        whereExpression = andTenantIdExpression(whereExpression, 1L);
        whereExpression = andDeletedExpression(whereExpression, true);

        plainSelect.setWhere(whereExpression);

        System.out.println("after ::: " + stmt);
    }

    private static BinaryExpression andTenantIdExpression(Expression where, Long tenantId) {
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

    private static Expression andDeletedExpression(Expression where, Boolean deleted)
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