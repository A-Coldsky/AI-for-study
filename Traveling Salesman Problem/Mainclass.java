import java.util.Random;

public class Mainclass {
    static int n=5;//城市数目
    static int N=6;//种群数目
    static Random random=new Random();
    static int[][] road={{0,2,3,6,5},
                         {2,0,4,2,1},
                         {3,4,0,4,7},
                         {6,2,4,0,8},
                         {5,1,7,8,0}};
    static float Pc=0.4f;//交叉率
    static float Pm=0.4f;//变异率
    public static void main(String[] args){
     population[] pul=new population[N];
     getspace(pul);
     int[] a=new int[n-1];
     int[][] b={{1,2,3,4}
     ,{2,1,3,4}
     ,{3,2,1,4}
     ,{4,2,3,1}
     ,{1,3,2,4}
     ,{1,4,3,2}};
     for (int i=0;i<N;i++){
        a=b[i].clone();
        pul[i].setGene(a);//初始化种群
     }
     int g=0;
     int jiaocha=0;
     int bianyi=0;
     while (g<100){
         population[] pulcopy=new population[N];
         getspace(pulcopy);
         float fits=0;
         for (int i=0;i<N;i++){
                fits+=pul[i].getfit();
         }
         for (int i=0;i<N;i++){
             float birth=random.nextFloat();
             if (0<=birth&&birth<((pul[0].getfit())/fits)){
                 pulcopy[i].setGene(pul[0].getGene().clone());
             }
             else if (((pul[0].getfit())/fits)<=birth&&birth<((pul[0].getfit()+pul[1].getfit())/fits)){
                 pulcopy[i].setGene(pul[1].getGene().clone());
             }
             else if(((pul[0].getfit()+pul[1].getfit())/fits)<=birth&&birth<((pul[0].getfit()+pul[1].getfit()+pul[2].getfit())/fits)){
                 pulcopy[i].setGene(pul[2].getGene().clone());
             }
             else if (((pul[0].getfit()+pul[1].getfit()+pul[2].getfit())/fits)<=birth&&birth<((pul[0].getfit()+pul[1].getfit()+pul[2].getfit()+pul[3].getfit())/fits)){
                 pulcopy[i].setGene(pul[3].getGene().clone());
             }
             else if (((pul[0].getfit()+pul[1].getfit()+pul[2].getfit()+pul[3].getfit())/fits)<=birth&&birth<((pul[0].getfit()+pul[1].getfit()+pul[2].getfit()+pul[3].getfit())+pul[4].getfit()/fits)){
                 pulcopy[i].setGene(pul[4].getGene().clone());
             }
             else {
                 pulcopy[i].setGene(pul[5].getGene().clone());
             }
         }//选择结束
         for (int i=0;i<N*Pc;i++){
             int m=random.nextInt(N);
             int n=random.nextInt(N);
             while (m==n) {
                 n = random.nextInt(N);
             }
             pulcopy[m].mixgene(pulcopy[n]);
             jiaocha++;
         }//交叉结束
         for (int i=0;i<N*Pm;i++){
             int m=random.nextInt(N);
             pulcopy[m].genechange();
             bianyi++;
         }//变异结束
         pul=pulcopy;

         g++;
     }
        System.out.println("jiaocha:"+jiaocha);
     System.out.println("bianyi:"+bianyi);
     printbest(pul);
    }
    static void printbest(population[] pul){
        population best=new population();
        for (int i=0;i<N;i++){
            if (pul[i].getfit()>best.getfit()){
                best=pul[i];
            }
        }
        best.printgene();
    }
    static void getspace(population[] pul){
        for (int i=0;i<N;i++){
            pul[i]=new population();
        }
    }
}
