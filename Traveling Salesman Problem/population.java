import java.util.Arrays;
import java.util.Random;

public class population {
    static Random random=new Random();
    static int[][] road = {{0, 2, 3, 6, 5},
            {2, 0, 4, 2, 1},
            {3, 4, 0, 4, 7},
            {6, 2, 4, 0, 8},
            {5, 1, 7, 8, 0}};
    static int n = Mainclass.n - 1;
    int[] gene = new int[n];
    float fit = 0;
    population(){

    }
    population(int[] gene) {
        this.gene = gene;
    }
    private void celfit() {
        float a = 0;
        for (int i = 0; i < n-1; i++) {
            a+=road[gene[i]][gene[i+1]];
        }
        a+=road[0][gene[0]];
        a+=road[gene[n-1]][0];
        fit=1/a;
    }
    public int[] getGene(){
        return gene;

    }
    public float getfit(){
        return fit;
    }
    public void setGene(int[] gene){
        this.gene=gene;
        celfit();//重新设置基因后更新
    }
    public void printgene(){
        System.out.println(Arrays.toString(gene));
        System.out.println("距离是："+1/fit);
    }
    public void genechange(){//变异
        int i=random.nextInt(n);
        int j=random.nextInt(n);
        while (i==j){
            j=random.nextInt(n);//防止没有成功变异
        }
        int a=gene[i];
        gene[i]=gene[j];
        gene[j]=a;
        celfit();//变异后确定fit
    }
    public void mixgene(population pol){//交叉
        int start=random.nextInt(n);
        int length=random.nextInt(n-start)+1;
        int[] gene1=new int[n];
        int[] gene2=new int[n];
        gene1=gene.clone();
        gene2=pol.gene.clone();
        refreshgene(gene1,pol.gene,start,length);
        refreshgene(gene2,gene,start,length);
        setGene(gene1);
        pol.setGene(gene2);
    }
    private void refreshgene(int []gene1,int[] gene2,int start,int length){
        int[] lin=new int[n];
        boolean flag;
        int t;
        for (int i=0;i<n;i++){
            t=0;
            if (i<start||(i>(start+length-1))){
                //当gene不在交叉序列中时
                flag=true;
                for (int j=start;j<(start+length);j++){
                    if (gene1[i]==gene2[j]){
                        flag=false;
                        break;
                    }
                }
                if (flag){
                    lin[t]=gene1[i];
                    t++;
                    if (t==start){
                        for (;t<(start+length);t++){
                            lin[t]=gene2[t];
                        }
                    }
                }
            }
        }
        gene1=lin.clone();

    }

}
