package jp.ac.jec.cm0105.android_springboot_wyq;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Method;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create account 注册 按钮监视器
        Button BtnPostRequest = findViewById(R.id.btn_PostRequest);
        BtnPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //点击获取输入框 userName 内容
                EditText input_userName = findViewById(R.id.input_userName);  //input_userName:ID-> view型
                String userName_string = input_userName.getText().toString();   //input_userName:View -> String

                //点击获取输入框 password
                EditText input_password = findViewById(R.id.input_password);  //input_password:ID-> view型
                String password_string = input_password.getText().toString();   //input_password:View -> String

                //点击获取输入框 email
                EditText input_email = findViewById(R.id.input_email);  //input_email:ID-> view型
                String email_string = input_email.getText().toString();   //input_email:View -> String


                //判断三个输入框都不能为空
                if(!userName_string.isEmpty() && !password_string.isEmpty() && !email_string.isEmpty()){
                    //子线程发送网络请求 --> copy
                    //APP发送网络请求时间不定，容易崩溃，所以创建子线程.start();
                    //增：post(注册register)
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //网络通信发送不一定成功，所以要用try-catch
                            try{
                                //网络通信语句
                                String json ="{\n" +
                                        "    \"userName\": \""+userName_string+"\",\n" +
                                        "    \"password\": \""+password_string+"\",\n" +
                                        "    \"email\": \""+email_string+"\"\n" +
                                        "}";
                                OkHttpClient client = new OkHttpClient();   //创建okhttp客户端
                                Request request =new Request.Builder()
                                        .url("http://10.220.6.35:8088/user/user")//服务器IP地址：(现在本级作为后台服务器)本机IP：192.168.1.2
                                        .post(RequestBody.create(MediaType.parse("application/json"),json))
                                        .build();//创建http请求
                                Response response = client.newCall(request).execute(); //向后端发送求情会返回信息，这里接受后端的回复,execute:执行发送的指令
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"Sent successfully！",Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }catch (Exception e){
                                e.printStackTrace();
                                //正常子线程不可以操作Ui，所以用runOnUiThread函数
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //如果通信失败弹出提示
                                        Toast.makeText(MainActivity.this,"Failed to connect to the network",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }//try-catch end
                        }//run() end
                    }).start();//start：运行创建的子线程  Thread end 子线程结束

                    //--------------子线程网络通信成功结束，跳转下一界面------------------

                }
                else if (userName_string.isEmpty()) { // 并且 密码 >= 6 位 //* 输入错误类型提示 *
                    Toast.makeText(MainActivity.this,"Please enter your username！",Toast.LENGTH_SHORT).show();
                } else if (password_string.isEmpty() || password_string.length()<6 || password_string.length()>12) {
                    Toast.makeText(MainActivity.this,"Please enter a password between 6 and 12 characters！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Please enter your email！",Toast.LENGTH_SHORT).show();
                }



                //判断两框都不能为空
                // 并且 密码 >= 6 位
                //* 输入错误类型提示 *
                //* 输入成功提示 *

                //APP发送网络请求时间不定，容易崩溃，所以创建子线程.start();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //网络通信发送不一定成功，所以要用try-catch
//                        try{
//                            //网络通信语句
//                            String json ="{\n" +
//                                    "    \"userName\": \"Tanaka\",\n" +
//                                    "    \"password\": \"Tanaka123\",\n" +
//                                    "    \"email\": \"Tanaka123@icloud.com\"\n" +
//                                    "}";
//                            OkHttpClient client = new OkHttpClient();   //创建okhttp客户端
//                            Request request =new Request.Builder()
//                                    .url("http://192.168.1.2:8080/user/user")//服务器IP地址：(现在本级作为后台服务器)本机IP：10.220.16.0
//                                    .post(RequestBody.create(MediaType.parse("application/json"),json))
//                                    .build();//创建http请求
//                            Response response = client.newCall(request).execute(); //向后端发送求情会返回信息，这里接受后端的回复,execute:执行发送的指令
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(MainActivity.this,"Sent successfully！",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            //正常子线程不可以操作Ui，所以用runOnUiThread函数
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    //如果通信失败弹出提示
//                                    Toast.makeText(MainActivity.this,"Failed to connect to the network",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }//try-catch end
//                    }
//                }).start();//start：运行创建的子线程  Thread end 子线程结束


            }//onClick end
        });//注册 Create account button setOnClickListener end

    }//onCreate end
}