package myinterview;

import lombok.Data;

import java.util.Objects;

@Data
public class Test {

    /**
     * 需求1：根据传入的参数N构造一个深度为N的满二叉权
     * 需求2：构造二叉树时，节点的值从‘A’、‘B’这样的字母顺序开始赋值，一到到‘Z’之后重新再从‘A’开始赋值
     * 需求3：按前面的方法构造多棵树，比如三棵，将每棵树的root节点相连成一个环状，然后以这个树环里面任一个节点为起点，遍历打印树环里面的每一个节点。
     */
    public static void main(String[] args) {
        BiTree biTree1 = new BiTree();
        biTree1.createDeep(4);

        BiTree biTree2 = new BiTree();
        biTree2.createDeep(4);

        BiTree biTree3 = new BiTree();
        biTree3.createDeep(4);

        biTree1.getRoot().setParent(biTree2.getRoot());
        biTree2.getRoot().setParent(biTree3.getRoot());
        biTree3.getRoot().setParent(biTree1.getRoot());

        printTraverse(biTree2.getRoot());
        System.out.println("------------------------------");

        printTraverse(biTree2.getRoot().getRight());
        System.out.println("------------------------------");

        printTraverse(biTree2.getRoot().getRight().getLeft());
        System.out.println("------------------------------");

        printTraverse(biTree2.getRoot().getRight().getLeft().getRight());
        System.out.println("------------------------------");
    }

    private static void printTraverse(BiTreeNode node)
    {
        if( Objects.equals(node.getData(), "A") ){
            printFromRoot(node, false);
        } else {
            printNotFromRoot(node, false);
        }
    }

    private static void printFromRoot(BiTreeNode node, boolean selfPrinted)
    {
        BiTreeNode firstNode = node;
        if( selfPrinted==false ){
            printTree(firstNode);
            System.out.println();
        }
        node = node.getParent();
        while(true){
            if( node!=firstNode ){
                printTree(node);
                System.out.println();
                node = node.getParent();
            } else {
                break;
            }
        }
    }

    private static void printNotFromRoot(BiTreeNode node, boolean selfPrinted)
    {
        // self
        if( selfPrinted==false ){
            printTree(node);
        }

        // parent
        System.out.print(node.getParent().getData()+", ");

        // sibling
        if( node.getParent()!=null ){
            BiTreeNode sibling = node.getParent().getLeft()!=node ? node.getParent().getLeft() : node.getParent().getRight();
            if( sibling!=null ){
                printTree(sibling);
            }
        }

        if( Objects.equals(node.getParent().getData(), "A")==false ){
            // 往上遍历
            printNotFromRoot(node.getParent(), true);
        } else {
            // next tree
            System.out.println();
            printFromRoot(node.getParent(), true);
        }
    }


    private static void printTree(BiTreeNode node)
    {
        if( node.getData()!=null ){
            System.out.print(node.getData()+", ");
        }
        if( node.getLeft()!=null ){
            printTree(node.getLeft());
        }
        if( node.getRight()!=null ){
            printTree(node.getRight());
        }
    }

}
//         A
//   B          C
//  D E       F   G
// H I J K   L M N O