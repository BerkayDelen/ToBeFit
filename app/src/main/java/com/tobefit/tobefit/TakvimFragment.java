package com.tobefit.tobefit;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tobefit.tobefit.Models.Exercise;
import com.tobefit.tobefit.Models.Step;
import com.tobefit.tobefit.presenter.MainPresenter;
import com.tobefit.tobefit.presenter_view.MainView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.blackbox_vision.materialcalendarview.view.CalendarView;


/**
 * Created by Admin on 18.3.2018.
 */

public class TakvimFragment extends Fragment implements MainView {
    private static final String DATE_TEMPLATE = "dd/MM/yyyy";

    @BindView(R.id.calendar_view)
    CalendarView calendarView;

    @BindView(R.id.StartGorev)
    Button StartGorev;

    @BindView(R.id.ll_takvim)
    LinearLayout ll_takvim;

    Context context;

    private final MainPresenter presenter = new MainPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.takvim_fragment,container,false);
        ButterKnife.bind(this,view);

        context=view.getContext();
        presenter.addCalendarView();








        ll_takvim.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // ignore all touch events
                return true;
            }
        });

        StartGorev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goGorev =new Intent(view.getContext(),Gorev.class);



                Lister lister =new Lister();

                goGorev.putExtra("exercise", lister.ExerciseRandom());


                startActivity(goGorev);
            }
        });
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });

        return view;
    }
    private String formatDate(@NonNull String dateTemplate, @NonNull Date date) {
        return new SimpleDateFormat(dateTemplate, Locale.getDefault()).format(date);
    }

    @Override
    public void prepareTextView() {
    }

    @Override
    public void prepareCalendarView() {
        //calendarView = view.findViewById(R.id.calendar_view);
        Calendar disabledCal = Calendar.getInstance();

        disabledCal.set(Calendar.DATE, disabledCal.get(Calendar.DATE) - 1);
        calendarView
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setOnDateClickListener(new CalendarView.OnDateClickListener() {

                    @Override
                    public void onDateClick(@NonNull Date date) {
                        String dat=String.format("Today is %s", formatDate(DATE_TEMPLATE, new Date(System.currentTimeMillis())));
                        //Toast.makeText(context.getApplicationContext(),"Gün:"+String.format("Selected Date %s",formatDate(DATE_TEMPLATE, date)),Toast.LENGTH_LONG).show();

                    }
                });


        calendarView.update(Calendar.getInstance(Locale.getDefault()));
    }

    @Override
    public void prepareNavigationDrawer() {
    }

    @Override
    public void animateViews() {
    }


}
