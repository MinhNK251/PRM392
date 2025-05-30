package com.example.lab5_2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_2.adapter.BookAdapter;
import com.example.lab5_2.model.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Book> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView rvBook = findViewById(R.id.rv_book);
        bookList = new ArrayList<>();
        bookList.add(new Book(R.drawable.ta_la_ta_de, "Ta là tà đế", "Hệ thống của anh ấy cho phép anh ấy tu luyện hiệu quả bằng cách sử dụng nguyên điểm từ cảm xúc của người khác và cũng cho phép anh ấy du hành đến các thế giới khác nhau.", "Manhua"));
        bookList.add(new Book(R.drawable.dai_tuong_vo_hinh, "Đại tượng vô hình", "Thì ra thế giới này có yêu linh, sinh vật trong cơ thể cô ta là yêu linh tên \"Nhân Tiêu\", không cách nào đẩy yêu linh ra ngoài được, cùng đồng hành trên con đường trừ yêu.", "Manhua"));
        bookList.add(new Book(R.drawable.tinh_giap_hon_tuong, "Tinh giáp hồn tướng", "Vị hoàng hồn sư cuối cùng của tộc người--Tống Vân Tường, dẫn theo hệ thống trọng sinh quay về lúc còn đi học.", "Manhua"));
        bookList.add(new Book(R.drawable.tu_chan_lieu_thien_quan, "Tu chân liêu thiên quần", "Tống Thư Hàng trong một ngày đẹp trời đã tham gia vào một nhóm những người thích truyện tiên hiệp.", "Novel"));
        bookList.add(new Book(R.drawable.na_kang_lim, "Na Kang Lim", "Na Kang Lim, nam sinh trung học bình trường rất thích đọc webtoon. Nữ chính từ bộ webtoon cậu hay đọc đã xuất hiện ngoài đợi thật", "Manhwa"));

        BookAdapter bookAdapter = new BookAdapter(bookList);
        rvBook.setAdapter(bookAdapter);
        rvBook.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}