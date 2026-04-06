package com.ntkhai.demosqlite.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ntkhai.demosqlite.R;
import com.ntkhai.demosqlite.adapter.LopHocAdapter;
import com.ntkhai.demosqlite.adapter.SinhVienAdapter;
import com.ntkhai.demosqlite.helper.DateTimeHelper;
import com.ntkhai.demosqlite.model.LopHoc;
import com.ntkhai.demosqlite.model.SinhVien;
import com.ntkhai.demosqlite.sqlite.LopHocDAO;
import com.ntkhai.demosqlite.sqlite.SinhVienDAO;

import java.util.List;

public class QuanLySinhVienActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtMaSV, edtHotenSV, edtNgaySinhSV;
    private ListView lvDanhsachSinhvien;
    private Spinner spLopHoc;
    private SinhVienAdapter sinhVienAdapter;
    private List<LopHoc> lopHocList;
    private List<SinhVien> sinhVienList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sinhvien);
        findViewById(R.id.btnLuuSinhVien).setOnClickListener(this);
        findViewById(R.id.btnThoatSinhVien).setOnClickListener(this);
        edtMaSV = findViewById(R.id.edtMaSV);
        edtHotenSV = findViewById(R.id.edtHotenSV);
        edtNgaySinhSV = findViewById(R.id.edtNgaySinhSV);
        spLopHoc = findViewById(R.id.spLopHoc);
        fillLopHocToSpinner();
        lvDanhsachSinhvien = findViewById(R.id.lvDanhsachSinhvien);
        fillLopHocToListView();
        spLopHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillLopHocToListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void fillLopHocToListView() {
        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        try {
            if (lopHocList == null || lopHocList.isEmpty()) return;
            int position = spLopHoc.getSelectedItemPosition();
            if (position < 0) return;
            int lopHocid = lopHocList.get(position).getId();
            sinhVienList = sinhVienDAO.getAllByLophoc(lopHocid);
            sinhVienAdapter = new SinhVienAdapter(this, sinhVienList);
            lvDanhsachSinhvien.setAdapter(sinhVienAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error loading list", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillLopHocToSpinner() {
        LopHocDAO lopHocDAO = new LopHocDAO(this);
        lopHocList = lopHocDAO.getAll();
        LopHocAdapter lopHocAdapter = new LopHocAdapter(this, lopHocList);
        spLopHoc.setAdapter(lopHocAdapter);
    }

    @Override
    public void onClick(View view) {
        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        int id = view.getId();
        if (id == R.id.btnLuuSinhVien) {
            try {
                if (lopHocList == null || lopHocList.isEmpty()) {
                    Toast.makeText(this, "Chưa có lớp học nào", Toast.LENGTH_SHORT).show();
                    return;
                }
                SinhVien sinhVien = new SinhVien();
                sinhVien.setId(edtMaSV.getText().toString());
                sinhVien.setHoten(edtHotenSV.getText().toString());
                sinhVien.setNgaysinh(DateTimeHelper.toDate(edtNgaySinhSV.getText().toString()));
                
                int lopHocPos = spLopHoc.getSelectedItemPosition();
                sinhVien.setLophocid(lopHocList.get(lopHocPos).getId());
                
                sinhVienDAO.insert(sinhVien);
                Toast.makeText(this, "Sinh viên đã được lưu", Toast.LENGTH_LONG).show();
                edtMaSV.setText("");
                edtHotenSV.setText("");
                edtNgaySinhSV.setText("");
                fillLopHocToListView();
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Có lỗi xảy ra: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btnThoatSinhVien) {
            finish();
        }
    }
}
