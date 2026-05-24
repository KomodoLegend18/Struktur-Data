import java.util.LinkedList;

public class Tugas1{
    /* Buatlah sebuah deklarasi variabel dengan tipe data float yang bernama ‘StrukturBaris’? (10 Poin)*/
    float StrukturBaris;
    /*Buatlah sebuah deklarasi variabel dengan tipe data string yang bernama ‘SusunanKataBaru’ yang berisi kata ‘Pemrograman Struktur Data Java’? (10 Poin)*/
    String SusunanKataBaru = "Pemrograman Struktur Data Java";
    /*Buatlah pendeklarasian array satu dimensi dengan nama ‘DelapanAngka, tipe data integer, yang berisi angka (10, 12, 18, 23, 33, 58, 67, 82)? Pendeklarasian tersebut dengan menggunakan bahasa pemrograman Java. (10 Poin)*/
    int[] DelapanAngka = {10,12,18,23,33,58,67,82};
    /*Buatlah pendeklarasian array dua dimensi dengan nama ‘DuaAngka’, tipe data String, yang terdiri dari tiga baris dan tiga kolom, isi baris dan kolom berisi angka berikut (2, 4, 6, 8, 10, 12, 14, 16, 18) Pendeklarasian tersebut dengan menggunakan bahasa pemrograman Java. (15 Poin)*/
    String[][] DuaAngka = {
        {"2","4","6"},
        {"8","10","12"},
        {"14","16","18"}
    };
    /*Buatlah deklarasi linked list dengan nama 'UrutanListAngka' yang memiliki list (15, 28, 33, 47, 
    59),  Pendeklarasian tersebut dengan menggunakan bahasa pemrograman Java. (20 Poin)*/
    LinkedList<Integer> UrutanListAngka = new LinkedList<>();
    public static void main(String[] args) {
        Tugas1 obj = new Tugas1();
        obj.UrutanListAngka.add(15);
        obj.UrutanListAngka.add(28);
        obj.UrutanListAngka.add(33);
        obj.UrutanListAngka.add(47);
        obj.UrutanListAngka.add(59);

        System.out.println("Struktur Baris: "+ obj.StrukturBaris);
        System.out.println("Susunan Kata Baru : "+ obj.SusunanKataBaru);
        System.out.println("Delapan Angka: "+ obj.DelapanAngka[0]);
        System.out.println("Dua Angka: "+ obj.DuaAngka[1][2]);
        System.out.println("Urutan List Angka: "+ obj.UrutanListAngka);
    }
}