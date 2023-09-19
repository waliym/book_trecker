package com.example.booktracker;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity2 extends AppCompatActivity {
    private EditText user_text, author_text;
    private TextView name;
    private MaterialButton book0, book2, book1, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12, book13, book14, book15;
    private int pink, green, purple, grey, red, brown, bg, bg1, bg2;
    Dialog dialog, setting;
    SharedPreferences sPref;
    SharedPreferences sTXT;
    ConstraintLayout bg_act1;

    int SIZE_BOOK = 15;
    String[] content_book = new String[SIZE_BOOK];
    String[] author_book = new String[SIZE_BOOK];
    MaterialButton[] boook = new MaterialButton[SIZE_BOOK];
    int[] color = new int[SIZE_BOOK];
    int colorBg = 0;

    private Animation left_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        name = findViewById(R.id.name);
        bg_act1 = findViewById(R.id.bg_act2);




        book0 = findViewById(R.id.book0);
        book2 = findViewById(R.id.book2);
        book3 = findViewById(R.id.book3);
        book4 = findViewById(R.id.book4);
        book5 = findViewById(R.id.book5);
        book6 = findViewById(R.id.book6);
        book7 = findViewById(R.id.book7);
        book8 = findViewById(R.id.book8);
        book9 = findViewById(R.id.book9);
        book10 = findViewById(R.id.book10);
        book11 = findViewById(R.id.book11);
        book12 = findViewById(R.id.book12);
        book13 = findViewById(R.id.book13);
        book14 = findViewById(R.id.book14);
        book15 = findViewById(R.id.book15);


        pink =  getResources().getColor(R.color.pink);
        green = getResources().getColor(R.color.green);
        purple = getResources().getColor(R.color.purple);
        grey = getResources().getColor(R.color.grey);
        red = getResources().getColor(R.color.red);

        bg = getResources().getColor(R.color.bg);
        bg1 = getResources().getColor(R.color.bg1);
        bg2 = getResources().getColor(R.color.bg2);

        brown = getResources().getColor(R.color.brown1);
        dialog = new Dialog(MainActivity2.this);
        setting = new Dialog(MainActivity2.this);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.bg));



        for (int i = 0; i < SIZE_BOOK; i++)
        {
            boook[i] = (i == 0 ? book0
                    :(i == 1 ? book2
                    :(i == 2 ? book3
                    :(i == 3 ? book4
                    :(i == 4 ? book5
                    :(i == 5 ? book6
                    :(i == 6 ? book7
                    :(i == 7 ? book8
                    :(i == 8 ? book9
                    :(i == 9 ? book10
                    :(i == 10 ? book11
                    :(i == 11 ? book12
                    :(i == 12 ? book13
                    :(i == 13 ? book14
                    :(i == 14 ? book15

                    : book1)))))))))))))));
            color[i] = 3;
        }

        left_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_right_in);


        loadColor();
        loadText();
    }

    public void Click_color(MaterialButton purple_btn, MaterialButton green_btn, MaterialButton pink_btn,
                            MaterialButton grey_btn, MaterialButton red_btn, MaterialButton brown_btn,  int[] color, int i)
    {
        purple_btn.getIcon().setAlpha(0);
        green_btn.getIcon().setAlpha(0);
        pink_btn.getIcon().setAlpha(0);
        grey_btn.getIcon().setAlpha(0);
        red_btn.getIcon().setAlpha(0);
        brown_btn.getIcon().setAlpha(0);
        (color[i] == 0 ? purple_btn
                :(color[i] == 1 ? green_btn
                :(color[i] == 2 ? pink_btn
                :(color[i] == 3 ? grey_btn
                :(color[i] == 4 ? red_btn
                :(color[i] == 5 ? brown_btn
                : grey_btn)))))).getIcon().setAlpha(255);

        saveColor(i);
    }
    public void saveColor (int i) {


        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        ed.putInt("kc"+i, color[i]);

        ed.commit();


    }
    public void setColor(int[] color, MaterialButton book1, int i)
    {
        switch (color[i])
        {
            case 0: book1.setBackgroundColor(purple);
                break;
            case 1: book1.setBackgroundColor(green);
                break;
            case 2: book1.setBackgroundColor(pink);
                break;
            case 3: book1.setBackgroundColor(grey);
                break;
            case 4: book1.setBackgroundColor(red);
                break;
            case 5: book1.setBackgroundColor(brown);
                break;
        }

    }
    public void loadColor()
    {
        sPref = getPreferences(MODE_PRIVATE);

        for(int i = 0; i < SIZE_BOOK; i++)
        {
            color[i] = sPref.getInt("kc"+i, 3);
            MaterialButton bk;
            bk = boook[i];

            setColor(color, bk, i);
        }
        colorBg = sPref.getInt("sbg", 0);
        setBgColor(colorBg);
    }

    public void setnameBook(String book_str, MaterialButton book)
    {

        int i = 0;
        for (; i < SIZE_BOOK; i++)
        {

            if(book.getId() == boook[i].getId())
            {
                break;
            }
        }

        content_book[i] = book_str;
        book.setText(book_str);
        book.setTextSize(19);

        if (book.length() >= 8) {
            book.setTextSize(16);
            if (book.length() >= 13) {
                book.setTextSize(13);
                if (book.length() >= 22) {
                    book_str = book_str.substring(0,22);
                    book.setText(book_str);
                }}}

        sTXT = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor txt = sTXT.edit();

        txt.putString("save_nameBook"+i,content_book[i]);
        txt.putString("save_autor"+i, author_book[i]);

        txt.commit();

    }



    public void Click_add_book(MaterialButton book) {
        String book_str = user_text.getText().toString();
        setnameBook(book_str, book);
    }
    public void loadText()
    {
        sTXT = getPreferences(MODE_PRIVATE);
        for (int i = 0; i < SIZE_BOOK; i++)
        {
            content_book[i] = sTXT.getString("save_nameBook"+i, "");
            author_book[i] = sTXT.getString("save_autor"+i, "");
        }
        for (int i = 0; i < SIZE_BOOK; i++)
        {
            String str_book = content_book[i];
            setnameBook(str_book, boook[i]);
        }
        name.setText(sTXT.getString("save_name1", ""));
    }

    public void click_book(View v)
    {
        MaterialButton bk;
        bk = (MaterialButton)((Button) v);
        for (int i = 0; i < SIZE_BOOK; i++)
        {
            if(bk.getId() == boook[i].getId())
            {


                Click_bookn(color, i, bk);
            }

        }

    }

    public void Click_bookn(int[] colorn, int i, MaterialButton book1) {

        dialog.setContentView(R.layout.book_new);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        MaterialButton add_book = dialog.findViewById(R.id.add_book);
        MaterialButton brown_btn = dialog.findViewById(R.id.c6);
        MaterialButton red_btn = dialog.findViewById(R.id.c5);
        MaterialButton grey_btn = dialog.findViewById(R.id.c4);
        MaterialButton pink_btn = dialog.findViewById(R.id.c3);
        MaterialButton green_btn = dialog.findViewById(R.id.c2);
        MaterialButton purple_btn = dialog.findViewById(R.id.c1);

        user_text = dialog.findViewById(R.id.user_text);
        author_text = dialog.findViewById(R.id.author_text);

        String book_btn = book1.getText().toString();
        if (!book_btn.isEmpty())
        {
            user_text.setText(content_book[i]);

        }
        author_text.setText(author_book[i]);

        Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, colorn, i);

        purple_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color[i] = 0;
                Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, color, i);
                book1.setBackgroundColor(purple);

            }
        });
        green_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color[i] = 1;
                Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, color, i);
                book1.setBackgroundColor(green);
            }
        });
        pink_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color[i] = 2;
                Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, color, i);
                book1.setBackgroundColor(pink);

            }
        });

        grey_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color[i] = 3;
                Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, color, i);
                book1.setBackgroundColor(grey);
            }
        });
        red_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color[i] = 4;
                Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, color, i);
                book1.setBackgroundColor(red);
            }
        });
        brown_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color[i] = 5;
                Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, color, i);
                book1.setBackgroundColor(brown);
            }
        });

        add_book.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                author_book[i] = author_text.getText().toString();
                Click_add_book(book1);
                dialog.cancel();
            }
        });
        dialog.show();



    }


    public void click_setting(View view) {
        setting.setContentView(R.layout.setting);
        setting.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText text_name1 = setting.findViewById(R.id.text_name1);
        MaterialButton btn_set_name1 = setting.findViewById(R.id.btn_set_name1);
        MaterialButton cbg = setting.findViewById(R.id.cbg);
        MaterialButton cbg1 = setting.findViewById(R.id.cbg1);
        MaterialButton cbg2 = setting.findViewById(R.id.cbg2);
        ImageButton exit_setting = setting.findViewById(R.id.exit_setting);
        MaterialButton btn_cleaner = setting.findViewById(R.id.btn_cleaner);
        text_name1.setText(name.getText().toString());
        ClickBgColor(colorBg, cbg, cbg1, cbg2);

        btn_set_name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText(text_name1.getText().toString());
                sTXT = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor txt = sTXT.edit();
                txt.putString("save_name1", text_name1.getText().toString());
                txt.commit();
            }
        });
        exit_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting.cancel();
            }
        });

        btn_cleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < SIZE_BOOK; i++)
                {
                    content_book[i] = "";
                    author_book[i] = "";
                    color[i] = 3;
                    setColor(color, boook[i], i);
                    setnameBook("", boook[i]);
                    saveColor(i);
                }
            }
        });

        cbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBg = 0;
                ClickBgColor(colorBg, cbg, cbg1, cbg2);
                bg_act1.setBackgroundColor(bg);
                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg));
                getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg));

            }
        });
        cbg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBg = 1;
                ClickBgColor(colorBg, cbg, cbg1, cbg2);
                bg_act1.setBackgroundColor(bg1);
                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg1));
                getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg1));
            }
        });
        cbg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBg = 2;
                ClickBgColor(colorBg, cbg, cbg1, cbg2);
                bg_act1.setBackgroundColor(bg2);
                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg2));
                getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg2));
            }
        });


        setting.show();
    }

    public void ClickBgColor(int colorBg, MaterialButton cbg, MaterialButton cbg1, MaterialButton cbg2)
    {
        cbg.getIcon().setAlpha(0);
        cbg1.getIcon().setAlpha(0);
        cbg2.getIcon().setAlpha(0);
        (colorBg == 0 ? cbg
                : (colorBg == 1 ? cbg1
                : (colorBg == 2 ? cbg2
                : cbg))).getIcon().setAlpha(255);

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        ed.putInt("sbg", colorBg);

        ed.commit();

    }
    public void setBgColor (int colorBg)
    {
        switch (colorBg)
        {
            case 0:
                bg_act1.setBackgroundColor(bg);
                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg));
                getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg));
                break;
            case 1:
                bg_act1.setBackgroundColor(bg1);
                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg1));
                getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg1));
                break;
            case 2:
                bg_act1.setBackgroundColor(bg2);
                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg2));
                getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity2.this,R.color.bg2));
                break;
        }
    }


    public void click_back(View view) {

        Intent myIntent = new Intent(MainActivity2.this, MainActivity.class);
        myIntent.putExtra("keyy", 0);




        MainActivity2.this.startActivity(myIntent);
        overridePendingTransition(R.anim.to_right_in, R.anim.to_right_out);





    }
}