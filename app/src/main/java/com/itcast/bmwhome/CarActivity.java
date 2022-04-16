package com.itcast.bmwhome;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LeftFragment leftFragment;
    private RightFragment rightFragment;
    private TextView tv_m3,tv_m4,tv_m5;

    //M3
    private String[] names1={"M3标准型","M3运动型","M3豪华型","M3豪华运动型","M3尊贵型"};
    private String[] prices1={"26.9","27.9","28.9","29.9","36.9"};
    private String[] power1={"汽油","汽油","油电混合","油电混合","全电动"};
    private String[] date1={"2021年1月","2021年2月","2021年3月","2021年4月","2021年5月"};
    private int[] imgs1={R.drawable.coverm3,R.drawable.coverm3,R.drawable.coverm3,R.drawable.coverm3,R.drawable.coverm3};

    //M4
    private String[] names2={"M4标准型","M4运动型","M4豪华型","M4豪华运动型","M4尊贵型"};
    private String[] power2={"汽油","汽油","油电混合","油电混合","全电动"};
    private String[] prices2={"39.9","41.9","42.9","43.9","44.9"};
    private String[] date2={"2021年1月","2021年2月","2021年3月","2021年4月","2021年5月"};
    private int[] imgs2={R.drawable.coverm4,R.drawable.coverm4,R.drawable.coverm4,R.drawable.coverm4,R.drawable.coverm4,};

    //M4
    private String[] names3={"M5标准型","M5运动型","M5豪华型","M5豪华运动型","M5尊贵型"};
    private String[] power3={"汽油","汽油","油电混合","油电混合","全电动"};
    private String[] prices3={"50.9","51.9","52.9","53.9","54.9"};
    private String[] date3={"2021年1月","2021年2月","2021年3月","2021年4月","2021年5月"};
    private int[] imgs3={R.drawable.coverm5,R.drawable.coverm5,R.drawable.coverm5,R.drawable.coverm5,R.drawable.coverm5,};

    private Map<String,List<CarBean>> map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_activity);
        setData();
        init();
        clickEvent();
    }
    private void init(){
        //获取FragmentManager
        fragmentManager=getSupportFragmentManager();
        //通过findFragmentById()获取leftFragment
        leftFragment=(LeftFragment)fragmentManager.findFragmentById(R.id.left);
        //获取左侧菜单栏中的控件
        tv_m3=leftFragment.getView().findViewById(R.id.tv_M3);
        tv_m4=leftFragment.getView().findViewById(R.id.tv_M4);
        tv_m5=leftFragment.getView().findViewById(R.id.tv_M5);

    }
    private void setData(){
        map=new HashMap<>();
        List<CarBean> list1=new ArrayList<>();
        List<CarBean> list2=new ArrayList<>();
        List<CarBean> list3=new ArrayList<>();
        for(int i=0;i<names1.length;i++){
            CarBean bean=new CarBean();
            bean.setName(names1[i]);
            bean.setPrice(prices1[i]+"万元");
            bean.setPower(power1[i]);
            bean.setDate(date1[i]);
            bean.setImg(imgs1[i]);
            list1.add(bean);
        }
        //将列表信息添加到Map集合中
        map.put("1",list1);

        for (int i=0;i<names2.length;i++){
            CarBean bean=new CarBean();
            bean.setName(names2[i]);
            bean.setPower(power2[i]);
            bean.setPrice(prices2[i]+"万元");
            bean.setDate(date2[i]);
            bean.setImg(imgs2[i]);
            list2.add(bean);
        }
        map.put("2",list2);

        for (int i=0;i<names3.length;i++){
            CarBean bean=new CarBean();
            bean.setName(names3[i]);
            bean.setPower(power3[i]);
            bean.setPrice(prices3[i]+"万元");
            bean.setDate(date3[i]);
            bean.setImg(imgs3[i]);
            list3.add(bean);
        }
        map.put("3",list3);
    }
    private void clickEvent(){
        tv_m3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                //switchData()填充Rf中的数据
                switchData(map.get("1"));

            }
        });

        tv_m4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                switchData(map.get("2"));

            }
        });

        tv_m5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                switchData(map.get("3"));

            }
        });
        //设置默认界面
        switchData(map.get("1"));
    }

    //填充右侧Fragment并传递列表数据list
    public void switchData(List<CarBean> list){
        fragmentManager=getSupportFragmentManager();
        //开启一个事务
        fragmentTransaction=fragmentManager.beginTransaction();
        //通过调用getInstance()方法实例化RightFragment
        rightFragment = new RightFragment().getInstance(list);
        //调用replace()方法
        fragmentTransaction.replace(R.id.right, rightFragment);
        fragmentTransaction.commit();
    }
}
