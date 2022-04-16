package com.itcast.bmwhome;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyHelper myHelper;
    private EditText et_account;    //账号输入框
    private EditText et_password;   //密码输入框
    private Button btn_register;   //“登陆”按钮
    private Button btn_login;   //“登陆”按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        initView();

        Map<String,String > userInfo= SPSaveUser.getUserInfo(this);
        if(userInfo!=null){
            et_account.setText(userInfo.get("account"));    //将获取到账号显示在界面上
            et_password.setText(userInfo.get("password"));  //将获取到密码显示到界面上
        }
    }
    private void initView(){
        et_account= findViewById(R.id.et_password);
        et_password = findViewById(R.id.et_password);
        btn_register= findViewById(R.id.btn_register);
        btn_login=findViewById(R.id.btn_login);
        //设置按钮点击监听事件
        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String account,password;
        SQLiteDatabase db;
        ContentValues values;
        switch(v.getId()){
            case R.id.btn_register:
                //点击登陆获取界面输入的账号与密码
                account=et_account.getText().toString().trim();
                password=et_password.getText().toString();
                //检验输入的账号与密码是否为空
                if(TextUtils.isEmpty(account)){
                    Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                //保存用户信息
                //获取可读写的SQLiteDatabase对象
                db=myHelper.getWritableDatabase();
                //创建ContentValue
                values=new ContentValues();
                //将数据写入ContentValues
                values.put("account",account);
                values.put("password",password);
                db.insert("information2",null,values);
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                System.out.println(account);
                System.out.println(password);
                db.close();
                break;


            case R.id.btn_login:
                account=et_account.getText().toString().trim();
                password=et_password.getText().toString();
                //检验输入的账号与密码是否为空
                if(TextUtils.isEmpty(account)){
                    Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                db=myHelper.getReadableDatabase();
                Cursor cursor=db.query("information2",null,null,null,null,null,null);
                if(cursor.getCount()!=0){
                    Toast.makeText(this,"账户不存在",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CarActivity.class);
                    startActivityForResult(intent,1);
                }
                System.out.println(cursor.getCount());
                System.out.println(account);
                System.out.println(password);
                cursor.close();
                db.close();
                break;
        }
    }

    class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context){
            super(context,"itcast.db",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE information2(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20))");
        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}