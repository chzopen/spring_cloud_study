package myinterview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BiTreeNode<T> {

    private T data;

    private BiTreeNode<T> parent;
    private BiTreeNode<T> left;
    private BiTreeNode<T> right;

    public void setData(T data) {
        this.data = data;
    }

}
