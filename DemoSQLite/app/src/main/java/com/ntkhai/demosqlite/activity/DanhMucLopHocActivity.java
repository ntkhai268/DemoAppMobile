package com.ntkhai.demosqlite.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ntkhai.demosqlite.R;
import com.ntkhai.demosqlite.adapter.LopHocAdapter;
import com.ntkhai.demosqlite.model.LopHoc;
import com.ntkhai.demosqlite.sqlite.LopHocDAO;

import java.util.List;

public class DanhMucLopHocActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTenLopHoc;
    private ListView lvDanhSachLopHoc;
    private List<LopHoc> lopHocList;
    private LopHocAdapter lopHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_lop_hoc);
        findViewById(R.id.btnLuuLopHoc).setOnClickListener(this);
        findViewById(R.id.btnThoatLopHoc).setOnClickListener(this);
        edtTenLopHoc = findViewById(R.id.edtTenLopHoc);
        lvDanhSachLopHoc = findViewById(R.id.lvdanhsachlophoc);
        fillLopHocListView();
    }

    private void fillLopHocListView() {
        LopHocDAO lopHocDAO = new LopHocDAO(this);
        lopHocList = lopHocDAO.getAll();
        lopHocAdapter = new LopHocAdapter(this, lopHocList);
        lvDanhSachLopHoc.setAdapter(lopHocAdapter);
        lvDanhSachLopHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                LopHocDAO lopHocDAO1 = new LopHocDAO(DanhMucLopHocActivity.this);
                LopHoc lopHoc = lopHocList.get(i);
                lopHocDAO1.delete(lopHoc.getId());
                fillLopHocListView();
                Toast.makeText(DanhMucLopHocActivity.this, "Đã xóa lớp học", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLuuLopHoc) {
            LopHoc lopHoc = new LopHoc();
            lopHoc.setTenlophoc(edtTenLopHoc.getText().toString());
            LopHocDAO lopHocDAO = new LopHocDAO(this);
            lopHocDAO.insert(lopHoc);
            edtTenLopHoc.setText("");
            fillLopHocListView();
            Toast.makeText(this, "Đã lưu lớp học", Toast.LENGTH_LONG).show();
        } else if (id == R.id.btnThoatLopHoc) {
            finish();
        }
    }
}
