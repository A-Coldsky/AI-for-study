import java.util.Arrays;
import java.util.Random;

public class Mainclass {

    /*public static void main(String[] args) {
        int a[][]={{1,2,3},{4,0,6},{7,8,9}};
        int d[][]={{1,2,3},{4,0,6},{7,7,7}};
        Node node=new Node(a);
        a=new int[3][3];
        int [][]c=node.geta();
        System.out.println(Arrays.deepToString(node.geta()));
       /*for (int i1 = 0; i1 < 3; i1++) {
            for (int j1 = 0; j1 < 3; j1++){
                a[i1][j1]=c[i1][j1];
            }

        }*/
        /*swap(a,0,0,2,2);
        System.out.println(Arrays.deepToString(node.geta()));
        System.out.println(node.getCost());
    }

    public static void swap(int array[][],int x1,int y1,int x2,int y2){
        int xx = array[x1][y1];
        int yy = array[x2][y2];
        array[x1][y1] = yy;
        array[x2][y2] = xx;
    }*/
    public static void main(String[] args){
        Node open=new Node();
        Node close=new Node();
        int a[][]={{2,8,3},{1,6,4},{7,0,5}};
        int num=0;//防止查找过多，导致程序难以结束
        Node node=new Node(a);
        node.setgx(0);
        node.setNext(open.getNext());
        open.setNext(node);
        boolean flag=false;
        while(!flag&&(open.getNext()!=null)){
            node=minNode(open);
            num++;
            if (node==null||(num>100)) {
                System.out.println("寻找失败");
                break;
            }
            node.setNext(close.getNext());
            close.setNext(node);//node入close

            if (node.getHx()==0){
                flag=true;//成功找到，则打印出寻找路径
                System.out.println("寻找成功");
                System.out.println(num);
                while (node!=null) {
                    node.printthelist();
                    node=node.getFather();
                }
                //然后跳出循环
            }
            else {//如果没有找到，拓展该节点
                node.makeson(open,close);

            }

        }





    }
    public static Node minNode(Node open){
        Node open1=open;
        Node min=null;
        int cost=99999999;
        if (open1.getNext()==null) return null;//open表为空
        while (open1.getNext()!=null){
            if (open1.getNext().getCost()<cost) {
                cost=open1.getNext().getCost();
                min=open1;
            }
            open1=open1.getNext();
        }
        open1=min.getNext();//实际找到的最小项
        min.setNext(min.getNext().getNext());//最小项出close
        return open1;
    }

}
