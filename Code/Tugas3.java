import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tugas3 {
    // variabel
    static final int JUMLAH_NODE = 10;
    static List<List<Integer>> graf = new ArrayList<>();
    static int[] nilai = new int[JUMLAH_NODE];
    static boolean[] dikunjungi = new boolean[JUMLAH_NODE];

    // Depth-First Search
    static boolean dfs(int node, int n, List<Integer> jalur, int[] langkah) {
        dikunjungi[node] = true;
        jalur.add(node);
        langkah[0]++;
        tampilkanJalur(jalur);

        // jika nilai ditemukan
        if (nilai[node] == n) {
            System.out.println("Nilai " + n + " ditemukan pada a" + node);
            System.out.println("Jumlah langkah DFS: " + langkah[0]);
            return true;
        }

        // loop semua tetangga node
        for (int tetangga : graf.get(node)) {
            // jika node tetangga belum dikunjungi
            if (!dikunjungi[tetangga]) {
                // jika node tetangga === n, return true
                if (dfs(tetangga, n, jalur, langkah)) {
                    return true;
                }
            }
        }

        // jika semua tetangga telah dikunjungi dan n tidak ditemukan, pindah jalur
        jalur.remove(jalur.size() - 1);
        return false;
    }

    // Breadth-First Search
    static boolean bfs(int nodeAwal, int n) {
        Deque<Integer> antrian = new ArrayDeque<>();
        int[] induk = new int[JUMLAH_NODE];
        int langkah = 0;

        Arrays.fill(induk, -1);
        antrian.add(nodeAwal);
        dikunjungi[nodeAwal] = true;

        // selama antrian tidak kosong
        while (!antrian.isEmpty()) {
            // hilangkan antrian terdepan, tampilkan jalur
            int node = antrian.poll();
            langkah++;
            tampilkanJalurBFS(node, induk);

            // jika node == n, berhenti
            if (nilai[node] == n) {
                System.out.println("Nilai " + n + " ditemukan pada a" + node);
                System.out.println("Jumlah langkah BFS: " + langkah);
                return true;
            }

            // untuk setiap node tetangga
            for (int tetangga : graf.get(node)) {
                // jika node tetangga belum dikunjungi
                if (!dikunjungi[tetangga]) {
                    // tandai telah dikunjungi, tambahkan ke antrian
                    dikunjungi[tetangga] = true;
                    induk[tetangga] = node;
                    antrian.add(tetangga);
                }
            }
        }

        // jika antrian kosong, dan n belum ditemukan, return false
        return false;
    }

    // Menampilkan jalur dari daftar node (digunakan DFS dan BFS)
    static void tampilkanJalur(List<Integer> jalur) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jalur.size(); i++) {
            if (i > 0) sb.append(" > ");
            int node = jalur.get(i);
            sb.append("a").append(node).append("(").append(nilai[node]).append(")");
        }
        System.out.println(sb);
    }

    // Menampilkan jalur dari a0 menuju node yang dikunjungi BFS
    static void tampilkanJalurBFS(int node, int[] induk) {
        List<Integer> jalur = new ArrayList<>();
        for (int curr = node; curr != -1; curr = induk[curr]) {
            jalur.add(0, curr);
        }
        tampilkanJalur(jalur);
    }

    // Menampilkan struktur graf
    static void tampilkanStruktur(int node, int sebelumnya, String jalur) {
        jalur += (jalur.isEmpty() ? "" : " > ") + "a" + node;
        boolean ujungJalur = true;

        for (int tetangga : graf.get(node)) {
            if (tetangga != sebelumnya) {
                ujungJalur = false;
                tampilkanStruktur(tetangga, node, jalur);
            }
        }

        if (ujungJalur) {
            System.out.println(jalur);
        }
    }

    // Mengubah semua status kunjungan menjadi false
    static void resetKunjungan() {
        Arrays.fill(dikunjungi, false);
    }

    public static void main(String[] args) {
        // membuat nilai acak
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        List<Integer> angkaAcak = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            angkaAcak.add(i);
        }

        Collections.shuffle(angkaAcak);

        // membuat node
        for (int i = 0; i < JUMLAH_NODE; i++) {
            graf.add(new ArrayList<>());
            nilai[i] = angkaAcak.get(i);
        }

        // membuat edge
        for (int i = 1; i < JUMLAH_NODE; i++) {
            int nodeLain = random.nextInt(i);
            graf.get(i).add(nodeLain);
            graf.get(nodeLain).add(i);
        }

        // menampilkan node dan nilainya
        System.out.println("Struktur graf:");
        tampilkanStruktur(0, -1, "");
        System.out.println("\nNilai node:");
        for (int i = 0; i < JUMLAH_NODE; i++) {
            System.out.println("a" + i + " = " + nilai[i]);
        }

        // memasukkan nilai yang akan dicari
        System.out.print("\nMasukkan angka yang dicari: ");
        int n = input.nextInt();
        input.close();

        // menjalankan dfs
        resetKunjungan();
        System.out.println("\n=== PROSES DFS ===");
        if (!dfs(0, n, new ArrayList<>(), new int[]{0})) {
            System.out.println("Nilai " + n + " tidak ditemukan.");
        }

        // menjalankan bfs
        resetKunjungan();
        System.out.println("\n=== PROSES BFS ===");
        if (!bfs(0, n)) {
            System.out.println("Nilai " + n + " tidak ditemukan.");
        }
    }
}