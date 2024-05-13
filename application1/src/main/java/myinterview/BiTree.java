package myinterview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BiTree<T> {

    private BiTreeNode<T> root;

    public void createDeep(int n)
    {
        Integer value = 1;
        if( n>0 ){
            if( root ==null ){
                root = new BiTreeNode<>();
                root.setData((T)getNodeValue(value));
            }
            if( n > 1 ){
                createChild(root, n-1);
            }
        }
    }

    public void createChild(BiTreeNode<T> parent, int n){
        if( n > 0 ){
            Integer leftValue = nodeValueToInt(parent) * 2;
            Integer rightValue = nodeValueToInt(parent) * 2 + 1;

            BiTreeNode<T> left = new BiTreeNode<>();
            left.setData((T)getNodeValue(leftValue));

            BiTreeNode<T> right = new BiTreeNode<>();
            right.setData((T)getNodeValue(rightValue));

            left.setParent(parent);
            right.setParent(parent);

            parent.setLeft(left);
            parent.setRight(right);
            if( n > 1 ){
                createChild(left, n - 1);
                createChild(right, n - 1);
            }
        }
    }

    private int nodeValueToInt(BiTreeNode<T> node)
    {
        String strValue = (String)node.getData();
        return strValue.charAt(0) - 64;
    }

    private String getNodeValue(int value)
    {
        value = (value-1) % 26;
        String c = String.valueOf( (char)(value+65) );
        return c;
    }



}
