import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Arrays;

public class Tugas2 {
    static int minInteger = 8; //Jumlah minimal data
    static int quickSortworstCaseHit = 0;

    static void dataStack(){
    
        Stack<Integer> stack = new Stack<>();
    
        for (int i = 0; i < minInteger; i++) {
            String pesan = "Integer Ke-"+(i+1)+": "+(i*i);
            stack.push(i*i);
            System.err.println(pesan+"\nIsi stack ada "+stack.size()+":\n"+stack+"\n");
        }
        System.err.println("Data di-POP: "+stack.pop());
        System.out.println("Isi stack ada "+stack.size()+":\n"+stack+"\n");
    }

    static void dataQueue(){
        Queue<Integer> queue = new LinkedList<>();
        System.err.println("Enqueue:\n"+queue);
        for (int i = 0; i < minInteger; i++) {
            String pesan = "\nInteger Ke-"+(i+1)+" ("+(i+i)+"): "+queue.add(i+i)+"\n";
            System.out.println(pesan+queue);
            if (i == minInteger-1) {
                System.err.println("# Proses Enqueue selesai\n");  
            }
        }
        System.err.println("Dequeue:\n"+queue);
        int removeCount = minInteger/2; 
        for (int i = 0;i < removeCount;i++){
            String pesan = "\nMenghapus "+queue.remove()+"\n";
            System.out.println(pesan+queue);
            if (i == removeCount-1) {
                System.err.println("# Proses Dequeue selesai\n");  
            }
        }
    }

    static void dataQuickSort(){
        int[] data = new int[minInteger];
        for (int i = 0; i < data.length; i++) {
            Random r=new Random();
            int rng = r.nextInt(minInteger*5);
            // System.out.println(rng);
            data[i] = rng;
        }
        dataQuickSort(data,0,data.length-1);
        System.err.println("Jumlah operasi maksimum untuk "+minInteger+" data = "+(minInteger*(minInteger-1)/2));
        int worstCaseMax = minInteger*(minInteger-1)/2;
        double worstPercent = (double) quickSortworstCaseHit/worstCaseMax*100; 
        System.err.println("Pivot merupakan angka terbesar di partisi: "+quickSortworstCaseHit+"x ("+(worstPercent)+"%)");
    }
    static void dataQuickSort(boolean dataTerurut){
        if (dataTerurut==true) {
            // contoh kasus data terburuk
            int[] worstData = new int[minInteger];
            for (int i = 0; i < worstData.length; i++) {
                worstData[i]=(i+1)*2;
            }
            dataQuickSort(worstData,0,worstData.length-1);
        }
        System.err.println("Jumlah operasi maksimum untuk "+minInteger+" data = "+(minInteger*(minInteger-1)/2));
        int worstCaseMax = minInteger*(minInteger-1)/2;
        double worstPercent = (double) quickSortworstCaseHit/worstCaseMax*100; 
        System.err.println("Pivot merupakan angka terbesar di partisi: "+quickSortworstCaseHit+"x ("+(worstPercent)+"%)");
    }
    static void dataQuickSort(int[] data, int start, int end){
        if (start<end) {
            System.out.println("--------------------------------------\n[QUICKSORT] mengurutkan index "+start+" sampai "+end);
            System.err.println("Data Array:\n"+Arrays.toString(data));

            int pivotIndex = dataQuickSortPartisi(data,start,end);
            dataQuickSort(data, start, pivotIndex-1);
            dataQuickSort(data, pivotIndex+1, end);
        }
    }
    static int dataQuickSortPartisi(int[] data, int start, int end){
        // Menyimpan data lama
        int[] dataBefore = new int[data.length];
        dataBefore = data.clone();
        int[] partisiDataLama = new int[(end-start+1)];
        for (int p=0;p < partisiDataLama.length;p++) {
            partisiDataLama[p] = dataBefore[start+p];
        }

        // Pivot
        int pivot = data[end]; //index terakhir sebagai pivot
        int i = start-1; //index element yang lebih kecil dari pivot
        System.out.println("[Pivot] "+pivot+" di-index "+end+"\n[Partisi] "+Arrays.toString(partisiDataLama));
        
        // menempatkan element
        for (int j = start; j < end; j++) {
            if (data[j] < pivot) {
                i++;
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                if (data[j] != data[i]) {
                    System.out.println("[?|"+j+"] Pertukaran data: index ["+j+"] "+data[i]+" dan index ["+(i)+"] "+data[j]);
                    System.err.println(Arrays.toString(dataBefore)+" sebelum\n"+Arrays.toString(data)+" setelah\n");
                }else if(data[j] == data[i]){
                    System.err.println("[!|"+j+"] data tidak dipindah\n"+data[j]+" di-index j["+j+"] "+data[i]+" di-index i["+i+"] "+Arrays.toString(partisiDataLama)+"\n");
                    quickSortworstCaseHit++;
                }
            } else {
                System.err.println("[!|"+j+"] Index["+j+"] "+data[j]+" lebih besar dari atau sama dengan "+pivot+", Melewati...\n");
            }
        }
        
        // menempatkan pivot
        i++;
        int temp = data[i];
        data[i] = data[end];
        data[end] = temp;
        System.out.println("[Pivot|"+end+"] "+pivot+" dipindah ke-index "+(i));

        // print partisi
        int[] partisiData = new int[(end-start+1)];
        for (int p=0;p < partisiData.length;p++) {
            partisiData[p] = data[start+p];
        }
        System.err.println("Partisi:\n[Sebelum] "+Arrays.toString(partisiDataLama)+"\n[Setelah] "+Arrays.toString(partisiData));
        System.err.println("Array:\n[Sebelum] "+Arrays.toString(dataBefore)+"\n[Setelah] "+Arrays.toString(data)+"\n\n");
        
        return i; //kembalikan posisi pivot
    }
    public static void main(String[] args) {
        // minInteger=128;
        long startTime,endTime;
        startTime = System.nanoTime();
        dataStack();
        dataQueue();
        dataQuickSort();
        endTime = System.nanoTime();
        System.out.println("Durasi: " + (endTime - startTime) + " ns (" + (endTime - startTime) / 1_000_000.0 + " ms)\n");
        
        
        // System.out.println(data);
    }
}