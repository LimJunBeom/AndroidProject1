package com.example.androidproject1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MonthViewActivity extends AppCompatActivity {
    GridView gv_calendar; //그리드뷰
    CalendarAdapter monthViewAdapter; //캘린더어댑터
    TextView year_month; //텍스트뷰
    int curYear; //현재년도
    int curMonth; //현재달

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv_calendar = findViewById(R.id.gv_calendar);
        monthViewAdapter = new CalendarAdapter(this);
        gv_calendar.setAdapter(monthViewAdapter);

        // gv_calendar 에 클릭리스너 설정
        gv_calendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();
                // 토스트메세지 설정
                // monthViewAdapter 에서 getCurYear(), getCurMonth() 받아오기
                // final 의 기능 : 지역변수를 상수화 시켜준다
                // 해당 월에 맞는 토스트 메시지를 보여줘야 하기때문에
                // String day_full 에서 (month+1)을 해준다
                curYear = monthViewAdapter.getCurYear();
                curMonth = monthViewAdapter.getCurMonth();
                final int month = curMonth;
                final int year = curYear;
                String day_full = year + "년 " + (month + 1) + "월 " + day + "일 ";
                if (day != 0) {
                    Toast myToast = Toast.makeText(MonthViewActivity.this, day_full,
                            Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });

        // 년, 월 텍스트 가져오기
        year_month = findViewById(R.id.year_month);
        setMonthText();

        // 이전 월로 넘어가는 버튼이벤트 처리
        // setPreviousMonth(), notifyDataSetChanged(), setMonthText() 사용
        Button prevBtn = findViewById(R.id.previous);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();
                setMonthText();
            }
        });

        // 다음 월로 넘어가는 버튼이벤트 처리
        // setNextMonth(), notifyDataSetChanged(), setMonthText() 사용
        Button nextBtn = findViewById(R.id.next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();
                setMonthText();
            }
        });
    }

    // year_month 에 현재년도 + "년" + (현재달+1) + "월" 입력
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();
        year_month.setText(curYear + "년" + (curMonth + 1) + "월");
    }
}