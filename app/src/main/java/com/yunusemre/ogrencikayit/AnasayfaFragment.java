package com.yunusemre.ogrencikayit;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunusemre.ogrencikayit.databinding.FragmentAnasayfaBinding;

import java.util.ArrayList;


public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private ArrayList<Ogrenci> ogrenciArrayList; // öğrenci sınıfından bir liste oluşturdum. verileri sıralamak için
    private OgrenciAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentAnasayfaBinding.inflate(inflater,container,false);

        ogrenciArrayList = new ArrayList<>(); // öğrenci listemi tanımlıyorum




        binding.rvAnasayfa.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OgrenciAdapter(ogrenciArrayList);
        binding.rvAnasayfa.setAdapter(adapter);



        binding.btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.kayitGidis);
        });



            veriAl(); // veri al metodumu burada çağırmazsam kodlar çalışmaz




        return binding.getRoot();
    }

    public void veriAl() {
        try {
            SQLiteDatabase database = getActivity().openOrCreateDatabase("Öğrenciler",Context.MODE_PRIVATE,null); // adı aynı olmalı kayıt fragment taki ile
            Cursor cursor = database.rawQuery("SELECT * FROM öğrenci",null); // cursor imleç demek. Bu kodun anlamı öğrenci tablosundaki her bir elemanı dolaş.
            int adIndex = cursor.getColumnIndex("ad"); // öğrenci tablosundaki ad değerim kaçıncı index de ise bana onu verir.
            int soyadIndex = cursor.getColumnIndex("soyad");
            int idIndex = cursor.getColumnIndex("id"); // burda ek olarak id değerininde bilgisini alıyoruz..

            while (cursor.moveToNext()) { // while döngüsünde cursor sql veri tabanım içinde gezsin ve bana istediğim index teki elemanları getirsin demek. burda for neden kullanmadık ? Çünkü while döngüsü hiçbir koşul sağlamazsa bile en az bir defa çalışır ancak for koşul sağlanmazsa hiç çalışmaz.
                 String ad = cursor.getString(adIndex); //burda string ad değişkenime yukardaki ad index ini veriyorum
                 String soyad = cursor.getString(soyadIndex);
                int id = cursor.getInt(idIndex);
                Ogrenci ogrenci = new Ogrenci(ad,soyad,id);
                ogrenciArrayList.add(ogrenci); // listemin içine öğrenci sınıfımı ekliyorum ve bu her veri eklendiğinde sürekli tekrar edecek bir yapının içinde olduğu için sürekli güncellenecek
            }
            adapter.notifyDataSetChanged(); // adapter a yeni veri gelince kendini güncellemesi için

                cursor.close(); // cursor ı kapatıyoruz.
        }catch (Exception e) {
            e.getLocalizedMessage();
        }


    }
}