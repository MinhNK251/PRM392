package com.communityuni.hocalertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moCuaSoAlertDialog(View view) {
      taoCuaSoHoiThoat();
    }
    private void taoCuaSoHoiThoat()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        //thiết lập tiêu đề:
        builder.setTitle("Xác nhận thoát");
        //thiết lập icon:
        builder.setIcon(android.R.drawable.ic_dialog_info);
        //thiết lập nội dung cho dialog:
        builder.setMessage("Bạn muốn thoát hả?");
        //thiết lập các nút lệnh để người dùng tương tác:
        builder.setPositiveButton("Có chứ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //tạo cửa sổ Dialog:
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        //hiển thị cửa sổ này lên:
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        taoCuaSoHoiThoat();
    }
}
