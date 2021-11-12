import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

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

    public static void main(String[] args) throws IOException {
        // base 16,64,100
        List<Integer> kVals = Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16);
        List<Integer> bVals = Arrays.asList(16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 120, 128);
        List<Integer> mVals = Arrays.asList(10, 15, 20, 25, 30, 35, 40, 45, 50);

        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter("./BloomFilterValues.csv");
        } catch(Exception E){
            
        }
        csvWriter.append((String) ("k,b,m,value\n"));
      
        for (int k : kVals) {
            for (int b : bVals) {
                for (int m : mVals) {
                    double testVal = test(k, b, m, 100000);
                    csvWriter.append((String) (k + "," + b + "," + m + "," + String.valueOf(testVal) + '\n'));
                }
            }
        }

        csvWriter.flush();
        csvWriter.close();
    }

    public static double test(int k, int b, int m, int testM){
        return test(k, b, m, testM, false);
    }

    public static double test(int k, int b, int m, int testM, boolean print){
        BloomFilter<Integer> bf = new BloomFilter<Integer>(k, b, m);
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


        return ((double) falsePositive / testM) * 100;
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
