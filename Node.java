import java.util.Arrays;

public class Node {
    static int globa[][] = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
    private Node next = null;
    private Node father = null;
    private int[][] a = new int[3][3];
    private int cost = 99999999;
    private int hx = 0;//预计下面代价，根据celhx自动生成
    private int gx = 0;//付出代价，在确定父节点后更新
    Node(){

    }
    Node(int a[][]) {
        this.a = a;
        hx = celhx();
    }

    public void setgx(int gx) {
        this.gx = gx;
        this.cost = this.gx + this.hx;//更改付出代价后及时更新成本
    }

    public int[][] geta() {
        return a;
    }

    public Node getNext() {
        return next;
    }

    public int getCost() {
        return cost;
    }

    public Node getFather() {
        return father;
    }

    public int getHx() {
        return hx;
    }

    public int getGx() {
        return gx;
    }
    public void setNext(Node next){
        this.next=next;
    }

    public void printthelist() {
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void changefather(Node betternode) {
        father = betternode;
    }

    public int makeson(Node open, Node close) {
        int num = 0;
        int i = 999, j = 999;
        for (int i1 = 0; i1 < 3; i1++) {
            for (int j1 = 0; j1 < 3; j1++) {
                if (a[i1][j1] == 0) {
                    i = i1;
                    j = j1;
                    break;
                }
            }//找出空格位置
        }
        if (whetheruse(i - 1, j)) {
            num++;
            haveson(open,close,i-1,j,i,j);
        }
        if (whetheruse(i + 1, j)) {
            num++;
            haveson(open,close,i+1,j,i,j);
        }
        if (whetheruse(i , j-1)) {
            num++;
            haveson(open,close,i,j-1,i,j);
        }
        if (whetheruse(i , j+1)) {
            num++;
            haveson(open,close,i,j+1,i,j);
        }

        return num;
    }//拓展子节点

    private int celhx() {
        int right = 8;
        for (int i = 0; i < 3; i++) {
            for (int t = 0; t < 3; t++)
                if (a[i][t] == 0) continue;
                else if (a[i][t] == globa[i][t]) right--;
        }
        return right;
    }

    private boolean whetheruse(int i, int j) {
        return (0 <= i && i < 3) && ((0 <= j && j < 3));
    }

    private void swap(int array[][], int x1, int y1, int x2, int y2) {
        int xx = array[x1][y1];
        int yy = array[x2][y2];
        array[x1][y1] = yy;
        array[x2][y2] = xx;
    }

    private void deepcopy(int a[][], int deepcopy[][]) {
        for (int i1 = 0; i1 < 3; i1++) {
            for (int j1 = 0; j1 < 3; j1++) {
                deepcopy[i1][j1] = a[i1][j1];
            }
        }
    }

    private void haveson(Node open,Node close,int i1,int j1,int i2,int j2) {
        boolean flag = true;
        int[][] deepcopy1 = new int[3][3];//可能存在bug
        deepcopy(a, deepcopy1);
        swap(deepcopy1, i1, j1, i2, j2);
        Node node = new Node(deepcopy1);
        node.setgx(this.gx + 1);
        node.changefather(this);
        Node close1 = close;
        while (close1.next != null) {
            if (close.next.geta() == node.geta()) {
                flag = false;
                break;
            }
            close1 = close1.next;//检查close是否有该节点
        }
        Node open1 = open;
        while (flag && (open1.next != null)) {
            if (open1.next.geta() == node.geta()) {
                if (node.gx < open1.next.gx) {
                    open1.next.changefather(this);
                    open.next.setgx(node.gx);
                    flag = false;
                    break;
                    //新节点成本更低
                } else {
                    flag = false;
                    break;
                }
            }
            open1 = open1.next;
        }
        if (flag) {
            node.next = open.next;
            open.next = node;
        }
    }
}
