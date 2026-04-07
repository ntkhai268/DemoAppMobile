package com.ptithcm.demosqlite.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ptithcm.demosqlite.R;
import com.ptithcm.demosqlite.adapter.LopHocAdapter;
import com.ptithcm.demosqlite.adapter.SinhVienAdapter;
import com.ptithcm.demosqlite.helper.DateTimeHelper;
import com.ptithcm.demosqlite.model.LopHoc;
import com.ptithcm.demosqlite.model.SinhVien;
import com.ptithcm.demosqlite.sqlite.LopHocDAO;
import com.ptithcm.demosqlite.sqlite.SinhVienDAO;
import java.util.List;

public class QuanLySinhVienActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtMaSV, edtHotenSV, edtNgaySinhSV;
    ListView lvDanhsachSinhvien;
    Spinner spLopHoc;
    SinhVienAdapter sinhVienAdapter;
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
        lvDanhsachSinhvien = findViewById(R.id.lvDanhsachSinhvien);

        fillLopHocToSpinner();

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

    private void fillLopHocToSpinner() {
        LopHocDAO lopHocDAO = new LopHocDAO(this);
        lopHocList = lopHocDAO.getAll();
        LopHocAdapter lopHocAdapter = new LopHocAdapter(this, lopHocList);
        spLopHoc.setAdapter(lopHocAdapter);
    }

    private void fillLopHocToListView() {
        if (lopHocList == null || lopHocList.isEmpty()) return;

        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        try {
            int lopHocid = lopHocList.get(spLopHoc.getSelectedItemPosition()).getId();
            sinhVienList = sinhVienDAO.getAllByLophoc(lopHocid);
            sinhVienAdapter = new SinhVienAdapter(this, sinhVienList);
            lvDanhsachSinhvien.setAdapter(sinhVienAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        int id = view.getId();

        if (id == R.id.btnLuuSinhVien) {
            try {
                if (lopHocList == null || lopHocList.isEmpty()) {
                    Toast.makeText(this, "Vui lòng tạo Lớp học trước!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SinhVien sinhVien = new SinhVien();
                sinhVien.setId(edtMaSV.getText().toString());
                sinhVien.setHoten(edtHotenSV.getText().toString());
                sinhVien.setNgaysinh(DateTimeHelper.toDate(edtNgaySinhSV.getText().toString()));

                int lopHoc = spLopHoc.getSelectedItemPosition();
                sinhVien.setLophocid(lopHocList.get(lopHoc).getId());

                sinhVienDAO.insert(sinhVien);
                Toast.makeText(this, "Sinh viên đã được lưu", Toast.LENGTH_LONG).show();

                fillLopHocToListView();
            } catch (Exception ex) {
                Toast.makeText(this, "Vui lòng nhập đúng định dạng ngày (dd/MM/yyyy)", Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        } else if (id == R.id.btnThoatSinhVien) {
            finish();
        }
    }
}