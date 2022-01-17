package com.example.mainproduce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mainproduce.R;

import java.util.ArrayList;

  public class MainActivity extends AppCompatActivity {
      ArrayList<Material> al = new ArrayList<Material>();  // Top10 곡명을 담을 리스트

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

        // 인기가요 순위 데이터 (다량의 데이터 준비)
          al.add(new Material("홍삼",R.drawable.img1,"면역력 증강/원기 회복/자양 강장/항산화"));
          al.add(new Material("노니",R.drawable.img02,"항산화/염증개선/피부미용/뇌혈관 관리/손상된 세포 생성"));

          MyAdapter adapter = new MyAdapter(
                  getApplicationContext(), // 현재화면의 제어권자
                 R.layout.row,
                  al);         // 데이터

          ListView lv = (ListView)findViewById(R.id.listView1);
          lv.setAdapter(adapter);

          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                                      int position, long id) {
                // 상세정보 화면으로 이동하기(인텐트 날리기)
                // 1. 다음화면을 만든다
                // 2. AndroidManifest.xml 에 화면을 등록한다
                // 3. Intent 객체를 생성하여 날린다
                  Intent intent = new Intent(
                          getApplicationContext(), // 현재화면의 제어권자
                          materialdetail.class); // 다음넘어갈 화면

                // intent 객체에 데이터를 실어서 보내기
                // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                  intent.putExtra("material", al.get(position).material);
                  intent.putExtra("img", al.get(position).img);
                  intent.putExtra("effect", al.get(position).effect);

                  startActivity(intent);
              }
          });
      } // end of onCreate
  } // end of class

  class MyAdapter extends BaseAdapter { // 리스트 뷰의 아답타
      Context context;
      int layout;
      ArrayList<Material> al;
      LayoutInflater inf;
      public MyAdapter(Context context, int layout, ArrayList<Material> al) {
          this.context = context;
          this.layout = layout;
          this.al = al;
          inf = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
      }
      @Override
      public int getCount() {
          return al.size();
      }
      @Override
      public Object getItem(int position) {
          return al.get(position);
      }
      @Override
      public long getItemId(int position) {
          return position;
      }
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
          if (convertView==null) {
              convertView = inf.inflate(layout, null);
          }
          ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
          TextView tvName = (TextView)convertView.findViewById(R.id.textView1);
          TextView tvInfo = (TextView)convertView.findViewById(R.id.textView2);

          Material m = al.get(position);
          iv.setImageResource(m.img);
          tvName.setText(m.material);
          tvInfo.setText(m.effect);

          return convertView;
      }
}

  class Material {
      String material = ""; // 원료 material
      int img; // 원료 이미지
      String effect = ""; // 효과
      public Material(String material, int img, String effect) {
          super();
          this.material = material;
          this.img = img;
          this.effect = effect;
      }
      public Material() {}
    }

