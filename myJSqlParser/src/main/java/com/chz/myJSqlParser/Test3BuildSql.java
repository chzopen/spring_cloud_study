package com.chz.myJSqlParser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.SelectUtils;

import java.util.HashMap;
import java.util.Map;

public class Test3BuildSql {

    public static void test() throws JSQLParserException
    {
        Select select = SelectUtils.buildSelectFromTable(new Table("mytable"));
        System.out.println(select);

        Select select2 = SelectUtils.buildSelectFromTableAndExpressions(new Table("mytable"), new Column("a"), new Column("b"));
        System.out.println(select2);

        Select select3 = SelectUtils.buildSelectFromTableAndExpressions(new Table("mytable"), "a+b", "test");
        System.out.println(select3);


    }

}
