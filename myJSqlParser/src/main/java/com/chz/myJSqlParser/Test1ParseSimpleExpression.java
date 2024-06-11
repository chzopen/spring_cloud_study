package com.chz.myJSqlParser;

import java.util.Stack;

import lombok.Getter;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;

public class Test1ParseSimpleExpression {

    public static void test() throws JSQLParserException {
        evaluate("4+5*6");
    }

    static void evaluate(String expr) throws JSQLParserException {
        System.out.println("expr = " + expr);
        Expression parseExpression = CCJSqlParserUtil.parseExpression(expr);
        MyExpressionDeParser deParser = new MyExpressionDeParser();
        parseExpression.accept(deParser);
        System.out.println(expr + " = " + deParser.getStack().pop());
    }

    private static class MyExpressionDeParser extends ExpressionDeParser {

        @Getter
        private Stack<Long> stack = new Stack<>();

        @Override
        public void visit(Addition addition) {
            super.visit(addition);

            long sum1 = stack.pop();
            long sum2 = stack.pop();

            stack.push(sum1 + sum2);
        }

        @Override
        public void visit(Multiplication multiplication) {
            super.visit(multiplication);

            long fac1 = stack.pop();
            long fac2 = stack.pop();

            stack.push(fac1 * fac2);
        }

        @Override
        public void visit(LongValue longValue) {
            super.visit(longValue);
            stack.push(longValue.getValue());
        }
    }
}


