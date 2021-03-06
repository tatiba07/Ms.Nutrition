package com.example.eiyoukun.mealFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eiyoukun.MySQLiteOpenHelper;
import com.example.eiyoukun.Product;
import com.example.eiyoukun.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PageFragment2 extends Fragment {

    private AutoCompleteTextView foodlist1;
    private AutoCompleteTextView foodlist2;
    private AutoCompleteTextView foodlist3;
    private AutoCompleteTextView foodlist4;
    private TextView calorieForm1;
    private TextView calorieForm2;
    private TextView calorieForm3;
    private TextView calorieForm4;
    private TextView protainForm1;
    private TextView protainForm2;
    private TextView protainForm3;
    private TextView protainForm4;
    private TextView carbonForm1;
    private TextView carbonForm2;
    private TextView carbonForm3;
    private TextView carbonForm4;
    private TextView fatForm1;
    private TextView fatForm2;
    private TextView fatForm3;
    private TextView fatForm4;
    private EditText foodGramForm1;
    private EditText foodGramForm2;
    private EditText foodGramForm3;
    private EditText foodGramForm4;
    private TextView calorieTotalForm;
    private TextView protainTotalForm;
    private TextView carbonTotalForm;
    private TextView fatTotalForm;

    private double foodgram = 0.0d;
    private double calorie = 0.0d;
    private double protain = 0.0d;
    private double carbon = 0.0d;
    private double fat = 0.0d;

    private double msg1_0;
    private double msg1_1;
    private double msg1_2;
    private double msg1_3;
    private double msg2_0;
    private double msg2_1;
    private double msg2_2;
    private double msg2_3;
    private double msg3_0;
    private double msg3_1;
    private double msg3_2;
    private double msg3_3;
    private double msg4_0;
    private double msg4_1;
    private double msg4_2;
    private double msg4_3;
    private String msg2_0Total;
    private String msg2_1Total;
    private String msg2_2Total;
    private String msg2_3Total;

    private static int REQUEST_CODE = 1000;
    private SharedPreferences mealInf;
    private static final String SHARED_PREF_NAME = "mealInf";
    private static final String KEY_MEAL1 = "meal1";
    private static final String KEY_MEAL2 = "meal2";
    private static final String KEY_MEAL3 = "meal3";
    private static final String KEY_MEAL4 = "meal4";
    private static final String KEY_MEALGRAM1 = "mealGram1";
    private static final String KEY_MEALGRAM2 = "mealGram2";
    private static final String KEY_MEALGRAM3 = "mealGram3";
    private static final String KEY_MEALGRAM4 = "mealGram4";
    private static final String KEY_CALORIE1 = "calorie1";
    private static final String KEY_PROTEIN1 = "protein1";
    private static final String KEY_CARBON1 = "carbon1";
    private static final String KEY_FAT1 = "fat1";
    private static final String KEY_CALORIE2 = "calorie2";
    private static final String KEY_PROTEIN2 = "protein2";
    private static final String KEY_CARBON2 = "carbon2";
    private static final String KEY_FAT2 = "fat2";
    private static final String KEY_CALORIE3 = "calorie3";
    private static final String KEY_PROTEIN3 = "protein3";
    private static final String KEY_CARBON3 = "carbon3";
    private static final String KEY_FAT3 = "fat3";
    private static final String KEY_CALORIE4 = "calorie4";
    private static final String KEY_PROTEIN4 = "protein4";
    private static final String KEY_CARBON4 = "carbon4";
    private static final String KEY_FAT4 = "fat4";
    private static final String KEY_CALORIETOTAL = "calorieTotal";
    private static final String KEY_PROTEINTOTAL = "proteinTotal";
    private static final String KEY_CARBONTOTAL = "carbonTotal";
    private static final String KEY_FATTOTAL = "fatTotal";

    private String meal1;
    private String meal2;
    private String meal3;
    private String meal4;
    private String mealGram1;
    private String mealGram2;
    private String mealGram3;
    private String mealGram4;
    private String calorie1;
    private String protein1;
    private String carbon1;
    private String fat1;
    private String calorie2;
    private String protein2;
    private String carbon2;
    private String fat2;
    private String calorie3;
    private String protein3;
    private String carbon3;
    private String fat3;
    private String calorie4;
    private String protein4;
    private String carbon4;
    private String fat4;
    private String calorieTotal;
    private String proteinTotal;
    private String carbonTotal;
    private String fatTotal;


    private MySQLiteOpenHelper mydb;
    private SQLiteDatabase db;

    private ArrayList<Product> products;
    private MySQLiteOpenHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup cotainer,
                             @Nullable Bundle saveInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page_food2,cotainer
                        , false);


        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodlist1 = (AutoCompleteTextView)view.findViewById(R.id.foodForm1_1);
        foodlist2 = (AutoCompleteTextView)view.findViewById(R.id.foodForm1_2);
        foodlist3= (AutoCompleteTextView)view.findViewById(R.id.foodForm1_3);
        foodlist4= (AutoCompleteTextView)view.findViewById(R.id.foodForm1_4);
        calorieForm1 = view.findViewById(R.id.calorieForm1_1);
        calorieForm2 = view.findViewById(R.id.calorieForm1_2);
        calorieForm3 = view.findViewById(R.id.calorieForm1_3);
        calorieForm4 = view.findViewById(R.id.calorieForm1_4);
        protainForm1 = view.findViewById(R.id.proteinForm1_1);
        protainForm2 = view.findViewById(R.id.proteinForm1_2);
        protainForm3 = view.findViewById(R.id.proteinForm1_3);
        protainForm4 = view.findViewById(R.id.proteinForm1_4);
        carbonForm1 = view.findViewById(R.id.carbonForm1_1);
        carbonForm2 = view.findViewById(R.id.carbonForm1_2);
        carbonForm3 = view.findViewById(R.id.carbonForm1_3);
        carbonForm4 = view.findViewById(R.id.carbonForm1_4);
        fatForm1 = view.findViewById(R.id.fatForm1_1);
        fatForm2 = view.findViewById(R.id.fatForm1_2);
        fatForm3 = view.findViewById(R.id.fatForm1_3);
        fatForm4 = view.findViewById(R.id.fatForm1_4);
        foodGramForm1 = view.findViewById(R.id.foodGramForm1_1);
        foodGramForm2 = view.findViewById(R.id.foodGramForm1_2);
        foodGramForm3 = view.findViewById(R.id.foodGramForm1_3);
        foodGramForm4 = view.findViewById(R.id.foodGramForm1_4);
        calorieTotalForm = view.findViewById(R.id.calorieSumForm1);
        protainTotalForm = view.findViewById(R.id.proteinSumForm1);
        carbonTotalForm = view.findViewById(R.id.carbonSumForm1);
        fatTotalForm = view.findViewById(R.id.fatSumForm1);

        mydb = new MySQLiteOpenHelper(requireActivity());
        db = mydb.getReadableDatabase();

        String sql = "SELECT * FROM Products";
        Cursor cr = db.rawQuery(sql, null);
        cr.moveToFirst();
        final String [] mydata = new String[cr.getCount()];
        int i = 0;
        while (cr.moveToNext()) {
            mydata[i] = cr.getString(1);
            i ++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.requireActivity(),
                android.R.layout.simple_dropdown_item_1line, mydata);
        foodlist1.setAdapter(adapter);
        foodlist2.setAdapter(adapter);
        foodlist3.setAdapter(adapter);
        foodlist4.setAdapter(adapter);




    Button foodButton1 = view.findViewById(R.id.foodButton1_1);
    //ボタンがクリックされた時の処理
        foodButton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = foodlist1.getText().toString();
            Cursor cr = db.query(
                    "Products",
                    new String[] {
                            "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                    },
                    "foodName = ?",
                    new String[]{ name },
                    null, null, null
            );
            boolean exist = cr.moveToNext();
            if (exist){
                foodgram = cr.getDouble(2);
                calorie = cr.getDouble(3);
                protain = cr.getDouble(4);
                carbon = cr.getDouble(5);
                fat = cr.getDouble(6);
            } else {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            }

            if (foodlist1.length() == 0 || foodGramForm1.length() == 0) {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            } else {
                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg1_0 = calorie * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                msg1_1 = protain * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                msg1_2 = carbon * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                msg1_3 = fat * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                calorieForm1.setText(String.valueOf(msg1_0)+ "cal");
                protainForm1.setText(String.valueOf(msg1_1)+ "g");
                carbonForm1.setText(String.valueOf(msg1_2)+ "g");
                fatForm1.setText(String.valueOf(msg1_3)+ "g");
            }

            EiyouTotal();
        }
    });

    Button foodButton2 = view.findViewById(R.id.foodButton1_2);
    //ボタンがクリックされた時の処理
        foodButton2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = foodlist2.getText().toString();
            Cursor cr = db.query(
                    "Products",
                    new String[] {
                            "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                    },
                    "foodName = ?",
                    new String[]{ name },
                    null, null, null
            );
            boolean exist = cr.moveToNext();
            if (exist){
                foodgram = cr.getDouble(2);
                calorie = cr.getDouble(3);
                protain = cr.getDouble(4);
                carbon = cr.getDouble(5);
                fat = cr.getDouble(6);
            } else {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            }

            if (foodlist2.length() == 0 || foodGramForm2.length() == 0) {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            } else {
                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg2_0 = calorie * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                msg2_1 = protain * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                msg2_2 = carbon * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                msg2_3 = fat * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                calorieForm2.setText(String.valueOf(msg2_0)+ "cal");
                protainForm2.setText(String.valueOf(msg2_1)+ "g");
                carbonForm2.setText(String.valueOf(msg2_2)+ "g");
                fatForm2.setText(String.valueOf(msg2_3)+ "g");
            }
            EiyouTotal();
        }
    });

    Button foodButton3 = view.findViewById(R.id.foodButton1_3);
    //ボタンがクリックされた時の処理
        foodButton3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = foodlist3.getText().toString();
            Cursor cr = db.query(
                    "Products",
                    new String[] {
                            "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                    },
                    "foodName = ?",
                    new String[]{ name },
                    null, null, null
            );
            boolean exist = cr.moveToNext();
            if (exist){
                foodgram = cr.getDouble(2);
                calorie = cr.getDouble(3);
                protain = cr.getDouble(4);
                carbon = cr.getDouble(5);
                fat = cr.getDouble(6);
            } else {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            }

            if (foodlist3.length() == 0 || foodGramForm3.length() == 0) {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            } else {
                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg3_0 = calorie * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                msg3_1 = protain * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                msg3_2 = carbon * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                msg3_3 = fat * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                calorieForm3.setText(String.valueOf(msg3_0)+ "cal");
                protainForm3.setText(String.valueOf(msg3_1)+ "g");
                carbonForm3.setText(String.valueOf(msg3_2)+ "g");
                fatForm3.setText(String.valueOf(msg3_3)+ "g");
            }

            EiyouTotal();
        }
    });

    Button foodButton4 = view.findViewById(R.id.foodButton1_4);
    //ボタンがクリックされた時の処理
        foodButton4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = foodlist4.getText().toString();
            Cursor cr = db.query(
                    "Products",
                    new String[] {
                            "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                    },
                    "foodName = ?",
                    new String[]{ name },
                    null, null, null
            );
            boolean exist = cr.moveToNext();
            if (exist){
                foodgram = cr.getDouble(2);
                calorie = cr.getDouble(3);
                protain = cr.getDouble(4);
                carbon = cr.getDouble(5);
                fat = cr.getDouble(6);
            } else {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            }

            if (foodlist4.length() == 0 || foodGramForm4.length() == 0) {
                foodgram = 0;
                calorie = 0;
                protain = 0;
                carbon = 0;
                fat = 0;
            } else {
                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg4_0 = calorie * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                msg4_1 = protain * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                msg4_2 = carbon * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                msg4_3 = fat * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                calorieForm4.setText(String.valueOf(msg4_0)+ "cal");
                protainForm4.setText(String.valueOf(msg4_1)+ "g");
                carbonForm4.setText(String.valueOf(msg4_2)+ "g");
                fatForm4.setText(String.valueOf(msg4_3)+ "g");
            }

            EiyouTotal();
        }
    });
}

    public void EiyouTotal (){

        msg2_0Total = String.valueOf(msg1_0 + msg2_0 + msg3_0 + msg4_0);
        calorieTotalForm.setText(msg2_0Total + "cal");
        msg2_1Total = String.valueOf(msg1_1 + msg2_1 + msg3_1 + msg4_1);
        protainTotalForm.setText(msg2_1Total + "g");
        msg2_2Total = String.valueOf(msg1_2 + msg2_2 + msg3_2 + msg4_2);
        carbonTotalForm.setText(msg2_2Total + "g");
        msg2_3Total = String.valueOf(msg1_3 + msg2_3 + msg3_3 + msg4_3);
        fatTotalForm.setText(msg2_3Total + "g");
        Bundle result0 = new Bundle();
        result0.putString("totalCalorie2", msg2_0Total);
        result0.putString("totalProtein2", msg2_1Total);
        result0.putString("totalCarbon2", msg2_2Total);
        result0.putString("totalFat2", msg2_3Total);
        getParentFragmentManager().setFragmentResult("requestKey2_0", result0);
    }


    @Override
    public void onResume() {
        super.onResume();

        mydb = new MySQLiteOpenHelper(requireActivity());
        db = mydb.getReadableDatabase();
        final String [] mydata;
        ArrayList<String> array = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        Cursor cr = db.rawQuery(sql, null);
        if(cr.moveToFirst()) {
            mydata = new String[cr.getCount()];
            int i = 0;
            do {
                mydata[i] = cr.getString(1);
                i++;
            } while (cr.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.requireActivity(),
                    android.R.layout.simple_dropdown_item_1line, mydata);
            foodlist1.setAdapter(adapter);
            foodlist2.setAdapter(adapter);
            foodlist3.setAdapter(adapter);
            foodlist4.setAdapter(adapter);
        }
    }
}

