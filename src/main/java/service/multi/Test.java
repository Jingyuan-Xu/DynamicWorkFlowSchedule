package service.multi;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random(14);
        for(int i=0;i<15;++i){
            System.out.print(random.nextInt() + " ");
        }
        System.out.println();
        random = new Random(14);
        for(int i=0;i<15;++i){
            System.out.print(random.nextInt()+" ");
        }
    }
}
