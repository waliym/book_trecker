package com.example.booktracker;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {
    private EditText user_text;
    private MaterialButton book0, book2, book1, book3, book4, book5, book6, book7, book8, book9, book10;
    private int pink, green, purple, grey, red, brown;
    Dialog dialog;
    SharedPreferences sPref;
    SharedPreferences sTXT;

    int SIZE_BOOK = 10;
    String[] content_book = new String[SIZE_BOOK];
    MaterialButton[] boook = new MaterialButton[SIZE_BOOK];
    int[] color = new int[SIZE_BOOK];






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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


            pink =  getResources().getColor(R.color.pink);
            green = getResources().getColor(R.color.green);
            purple = getResources().getColor(R.color.purple);
            grey = getResources().getColor(R.color.grey);
            red = getResources().getColor(R.color.red);
            brown = getResources().getColor(R.color.brown);
            dialog = new Dialog(MainActivity.this);



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
                    : book1))))))))));
            color[i] = 3;
        }


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

        saveColor();
    }
    public void saveColor () {


        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        for (int i = 0; i < SIZE_BOOK; i++)
        {
            ed.putInt("kc"+i, color[i]);
        }
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
        book.setTextSize(20);

        if (book.length() >= 8) {
            book.setTextSize(17);
            if (book.length() >= 13) {
                book.setTextSize(14);
                if (book.length() >= 22) {
                    book_str = book_str.substring(0,22);
                    book.setText(book_str);
                }}}

        sTXT = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor txt = sTXT.edit();
        for (int j = 0; j < SIZE_BOOK; j++)
        {
            txt.putString("save_nameBook"+j,content_book[j]);
        }
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
        }
        for (int i = 0; i < SIZE_BOOK; i++)
        {
            String str_book = content_book[i];
            setnameBook(str_book, boook[i]);
        }
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

        String book_btn = book1.getText().toString();
        if (!book_btn.isEmpty())
        {
            user_text.setText(content_book[i]);

        }

        Click_color(purple_btn, green_btn, pink_btn, grey_btn, red_btn, brown_btn, colorn, i);

        purple_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorn[0] = 0;
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
                Click_add_book(book1);
                dialog.cancel();
            }
        });
        dialog.show();



    }




}