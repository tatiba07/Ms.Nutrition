package com.example.eiyoukun;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import java.lang.Math;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstFragment extends Fragment {

    private TextView useridForm;
    private TextView ageForm;
    private TextView weightForm;
    private TextView heightForm;
    private TextView sexForm;
    private TextView activityLevelForm;
    private TextView purposeForm;
    private TextView calorieForm;
    private TextView proteinForm;
    private TextView carbonForm;
    private TextView fatForm;

    private static int REQUEST_CODE = 1000;
    private SharedPreferences accountInf;
    private static final String SHARED_PREF_NAME = "accountInf";
    public static final String KEY_USERID = "userid";
    public static final String KEY_AGE = "age";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_SEX = "sex";
    private static final String KEY_ACTIVITYLEVEL = "activityLevel";
    private static final String KEY_PURPOSE = "purpose";


    private SharedPreferences EiyouInf;
    private static final String SHARED_PREF_NAME1 = "EiyouInf";
    private static final String KEY_CALORIE = "calorie";
    private static final String KEY_PROTAIN = "protain";
    private static final String KEY_CARBON = "carbon";
    private static final String KEY_FAT = "fat";
    private static final String KEY_PURPOSE2 = "purpose2";
    private static final String KEY_WEIGHT2 = "weight2";

    private String strUserid;
    private String strAge;
    private String strWeight;
    private String strHeight;
    private String strSex;
    private String strActivityLevel;
    private String strPurpose;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public firstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment firstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static firstFragment newInstance(String param1, String param2) {
        firstFragment fragment = new firstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        useridForm = view.findViewById(R.id.useridForm);
        ageForm = view.findViewById(R.id.ageForm);
        weightForm = view.findViewById(R.id.weightForm);
        heightForm = view.findViewById(R.id.heightForm);
        sexForm = view.findViewById(R.id.sexForm);
        activityLevelForm = view.findViewById(R.id.activityLevelForm);
        purposeForm = view.findViewById(R.id.purposeForm);

        calorieForm = view.findViewById(R.id.calorieForm);
        proteinForm = view.findViewById(R.id.proteinForm);
        carbonForm = view.findViewById(R.id.carbonForm);
        fatForm = view.findViewById(R.id.fatForm);

        // activity_main内のregistButtonを取得
        Button goRegistButton = view.findViewById(R.id.regist);
        //ボタンがクリックされた時の処理を追加
        goRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(getActivity(), RegistActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        loadData();
        setData();
        try {
            calNutrition();
        } catch (Exception e){

        }
        saveData();
    }


    public void loadData() {
        //fragmentはsharedPreferenceを直接呼び出せないので、親のactivityを呼んでそこから取得する
        accountInf = requireActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strUserid = accountInf.getString(KEY_USERID, "");
        strAge = accountInf.getString(KEY_AGE, "");
        strWeight = accountInf.getString(KEY_WEIGHT, "");
        strHeight = accountInf.getString(KEY_HEIGHT, "");
        strSex = accountInf.getString(KEY_SEX, "");
        strActivityLevel = accountInf.getString(KEY_ACTIVITYLEVEL, "");
        strPurpose = accountInf.getString(KEY_PURPOSE, "");
    }

    public void setData() {
        useridForm.setText(strUserid);
        ageForm.setText(strAge);
        weightForm.setText(strWeight);
        heightForm.setText(strHeight);
        sexForm.setText(strSex);
        activityLevelForm.setText(strActivityLevel);
        purposeForm.setText(strPurpose);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        useridForm = getView().findViewById(R.id.useridForm);
        ageForm = getView().findViewById(R.id.ageForm);
        weightForm = getView().findViewById(R.id.weightForm);
        heightForm = getView().findViewById(R.id.heightForm);
        sexForm = getView().findViewById(R.id.sexForm);
        activityLevelForm = getView().findViewById(R.id.activityLevelForm);
        purposeForm = getView().findViewById(R.id.purposeForm);

        calorieForm = getView().findViewById(R.id.calorieForm);
        proteinForm = getView().findViewById(R.id.proteinForm);
        carbonForm = getView().findViewById(R.id.carbonForm);
        fatForm = getView().findViewById(R.id.fatForm);

        loadData();
        setData();
        try {
            calNutrition();
        } catch (Exception e){
        }
        saveData();
    }



    public void calNutrition() {

        double protein, carbon, fat, age, weight, height;
        age = Double.parseDouble(strAge);
        weight = Double.parseDouble(strWeight);
        height = Double.parseDouble(strHeight);

        // case文で処理したい
        //18~29歳向けのアクティビティレベル計算
        double calorie = 0;
        if (strSex.equals("男性") && strActivityLevel.equals("自主的な運動なし")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.5; //  String msg0　時に四捨五入
        } else if (strSex.equals("男性") && strActivityLevel.equals("週１～２で運動")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.625;
        } else if (strSex.equals("男性") && strActivityLevel.equals("週３～４で運動")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.75;
        } else if (strSex.equals("男性") && strActivityLevel.equals("週５～６で運動")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.875;
        } else if (strSex.equals("男性") && strActivityLevel.equals("毎日運動をしている")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 2.0;
        } else if (strSex.equals("女性") && strActivityLevel.equals("自主的な運動なし")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.5) * 10) / 10.0; // この時点で四捨五入
        } else if (strSex.equals("女性") && strActivityLevel.equals("週１～２で運動")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.625) * 10) / 10.0;
        } else if (strSex.equals("女性") && strActivityLevel.equals("週３～４で運動")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.75) * 10) / 10.0;
        } else if (strSex.equals("女性") && strActivityLevel.equals("週５～６で運動")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.875) * 10) / 10.0;
        } else if (strSex.equals("女性") && strActivityLevel.equals("毎日運動をしている")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 2.0) * 10) / 10.0;
        }

        protein = weight * 2.4;
        fat = weight * 0.9;
        carbon = (calorie - (protein * 4) - (fat * 9)) / 4;

        String msg0 = String.format("%.1f", calorie); // 四捨五入
        String msg1 = String.format("%.1f", protein);
        String msg2 = String.format("%.1f", carbon);
        String msg3 = String.format("%.1f", fat);

        calorieForm.setText(msg0);
        proteinForm.setText(msg1);
        carbonForm.setText(msg2);
        fatForm.setText(msg3);
    }


    public void saveData() {
        EiyouInf = requireActivity().getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = EiyouInf.edit();
        editor.putString(KEY_CALORIE,calorieForm.getText().toString());
        editor.putString(KEY_PROTAIN,proteinForm.getText().toString());
        editor.putString(KEY_CARBON,carbonForm.getText().toString());
        editor.putString(KEY_FAT,fatForm.getText().toString());
        editor.putString(KEY_PURPOSE2,purposeForm.getText().toString());
        editor.putString(KEY_WEIGHT2,weightForm.getText().toString());
        editor.apply();
    }

}