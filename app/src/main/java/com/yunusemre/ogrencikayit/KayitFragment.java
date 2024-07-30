package com.yunusemre.ogrencikayit;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yunusemre.ogrencikayit.databinding.FragmentKayitBinding;

import java.util.ArrayList;


public class KayitFragment extends Fragment {
    private FragmentKayitBinding binding;
    private SQLiteDatabase database; // veritabanımıza burada değişken ismi atıyoruz. aşağıda tanımlamasını yapacağız!


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentKayitBinding.inflate(inflater,container,false);



        binding.btnPaylas.setOnClickListener(v -> { // paylaş butonuna basınca girdiğim verileri değişkene atayıp sql veritabanına kaydedeceğim. Sonra navigation ile anasayfa fragment a gideceğim

            String ad = binding.editTextAd.getText().toString();
            String soyad = binding.editTextSoyad.getText().toString();

                // try - catch fonksiyonu dene yap demektir. yani try'ın içerisine yazılan kodlarda eğer hata çıkarsa catch'in içerisinde bize hata mesajını verecek ve uygulama çökmeyecek.
                // neden burada kullanıyoruz çünkü veritabanında sık hatalarla karşılaşabiliriz. Uygulama veritabanına bağlanmaz, istediğimiz veri olmaz vs vs ..
            try {

                database = getActivity().openOrCreateDatabase("Öğrenciler",Context.MODE_PRIVATE,null); // sql veri tabanımı kurdum. Adı "Öğrenciler"
                database.execSQL("CREATE TABLE IF NOT EXISTS öğrenci (id INTEGER PRIMARY KEY, ad VARCHAR, soyad VARCHAR)"); // Öğrenciler veritabanımda öğrenci tablosu oluşturuyorum ve id,ad,soyad sütunlarını ekliyorum.

                // şimdi sütunlarıma değer giricem
                String sqlDeger = "INSERT INTO öğrenci (ad,soyad) VALUES (?,?)"; // öğrenci tablosunda ki değer vereceğim sütunları seçtim ve onların values yani değer kısımlarına soru işareti koydum. Çünkü onları kullanıcıdan aldığımız değişkenler ile bağlayacağız.
                SQLiteStatement sqLiteStatement = database.compileStatement(sqlDeger); // bu kod yukarıda ki sqlDeger değişkenimi sql içinde çalıştırdı.
                sqLiteStatement.bindString(1,ad); // benden değer için index istiyor. Sql de indexler dizilerdeki gibi 0'dan başlamaz. 1'den başlar. 1de ad sütunum var ve ona yukarıda kullanıcıdan aldığım ad değişkenini atıyorum.
                sqLiteStatement.bindString(2,soyad); // aynı işlem ...
                sqLiteStatement.execute(); // çalıştırma kodu. Bu olmazsa yukarıdaki eklemelerin bir anlamı yok!!!!

            }catch (Exception e) { // exception hata demek oluyor
                e.getLocalizedMessage();  // burada hatayı String türünde yazdırıyoruz
            }

            // kayıt olayı bitti. Anasayfa fragment a gideceğiz.
            Navigation.findNavController(v).navigate(R.id.anasayfaGidis);

            // ... Yukarda öğrenci tablosunda id de tanımladım ama ona değer vermedim. O arka planda otomatik veri eklendikçe artacak. Sonrasında sql veritabanında ekleme silme güncelleme için bize avantaj sağlar !!

            // burası bitti. verileri kayıt ettik. şimdi anasayfa fragment a gidiyoruz orda kaydettiğimiz verileri alıcaz ve adapter yardımıyla recycler view da göstericez..




        });















        return binding.getRoot();
    }
}