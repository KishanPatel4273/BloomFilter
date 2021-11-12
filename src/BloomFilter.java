import java.util.BitSet;

public class BloomFilter<E> {


    private int k, b, n;
    private BitSet bloomFilter;

    public BloomFilter(int k, int b, int n){
        this.k = k;
        this.b = b;
        this.n = n;
        bloomFilter = new BitSet(k * b);
    }

    private int hash(E key, int k){
        return ((String.valueOf(key.hashCode() * (k+1))).hashCode()) % b;
    }

    public void add(E key){
        for(int i = 0; i < k; i++){//k hashes
            bloomFilter.set(i*b + hash(key, i), true);
        }
    }
    
    public boolean search(E key){
        for(int i = 0; i < k; i++){//k hashes
            if(!bloomFilter.get(i*b + hash(key, i))){
                return false;
            }
        }
        return true;
    }

    public String toString(){
        String str = "";
        for(int i = 0; i < k; i++){
            str +=  "[";
            for(int j = 0; j < b; j++){
                str += (bloomFilter.get(i*b + j) ? 1 : 0) + ((j < b-1) ? " " : "");
            }
            str += "]\n";
        }
        return str;
    }

    public static void main(String[] args){
       System.out.println(test(100, 100000, true));

    }


    public static double test(int m, int testM, boolean print){
        BloomFilter<Integer> bf = new BloomFilter<Integer>(8*2, 32*2, m);
        int[] val = new int[m];
        //add m unique items
        for(int i = 0; i < m; i++){
            val[i] = (int) (Math.random() * Math.pow(10, 6));
            bf.add(val[i]);
        }

        //prints all items added that werent found by search
        for(int i = 0; i < m; i++){
            if(!bf.search(val[i])){
                System.out.println("ERROR " + val[i] + " was added but not found");
            }
        }

        if(print)
            System.out.println(bf.toString());

        //test new keys to see of if false positive occurs;
        int falsePositive = 0;
        int[] testVal = new int[testM];
        for(int i = 0; i < testM; i++){
            testVal[i] = (int) ((Math.random() * Math.pow(10, 3)) * (Math.random() * Math.pow(10, 3)));
            if(bf.search(testVal[i]) && !searchArray(val, testVal[i])){//Teat val false positive and not in val
                falsePositive++;
                if(print)
                    System.out.println("False Positive : " + testVal[i]);
            }
        }

        //609517


        return (double) falsePositive / testM;
    }

    public static boolean searchArray(int[] A, int e){
        for(int i = 0; i < A.length; i++){
            if(A[i] == e){
                return true;
            }
        }
        return false;
    }

    public static void ig(){
        BloomFilter<Integer> bf = new BloomFilter<Integer>(3, 10, 20);

        bf.add(10);
        bf.add(120);
        bf.add(1204533);
        bf.add(143523523);

        System.out.println(bf.toString());

        System.out.println("search 10 : " + bf.search(10));
        System.out.println("search 1 : " + bf.search(1));
        System.out.println("search 120 : " + bf.search(120));
        System.out.println("search 121 : " + bf.search(121));
        System.out.println("search 143523523 : " + bf.search(143523523));
        System.out.println("search 143523525 : " + bf.search(143523525));


    }

}
