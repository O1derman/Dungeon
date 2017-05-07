package com.example.olderman.dungeon;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OS {

    private Activity activity;
    private TextView textView;
    private LinearLayout buttons;

    public OS() {

    }

    public OS(Activity activity, TextView textView, LinearLayout buttons) {
        set(activity, textView, buttons);
    }

    private CharSequence text = "";

    public void set(Activity activity, TextView textView, LinearLayout buttons) {
        if (this.activity != null) {
            throw new IllegalStateException();
        }
        activity.getClass();//null check
        buttons.getClass();
        textView.setText(text);//also a null check
        this.activity = activity;
        this.textView = textView;
        this.buttons = buttons;
        createButtons();
    }

    public void onActivityDestroy() {
        if (activity == null) {
            throw new IllegalStateException();
        }
        text = textView.getText();
        activity = null;
        textView = null;
        buttons = null;
    }

    public void clear() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("");
            }
        });
    }

    private volatile int result;
    private String[] buttonLabels;

    private void createButtons() {
        if (buttonLabels != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < buttonLabels.length; i++) {
                        createButton(i + 1, buttonLabels[i]);
                    }
                }
            });
        }
    }

    private void createButton(final int i, String text) {
        Button button = new Button(activity);
        button.setText(text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons.removeAllViews();
                buttonLabels = null;
                synchronized (OS.this) {
                    result = i;
                    OS.this.notifyAll();
                }
            }
        });
        buttons.addView(button, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void stop() {
        synchronized (this) {
            result = -2;
            notifyAll();
        }
    }

    public int uzivatVolba(final String... options) {

        result = -1;

        buttonLabels = options;
        createButtons();

        synchronized (this) {
            while (result == -1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
        if (result == -2) {
            throw new ExitException();
        }
        clear();
        return result;
    }

    public void print(final String string) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(string);
                textView.requestLayout();
            }
        });
    }

    public void println(String string) {
        print(string);
        println();
    }

    public void println() {
        print("\n");
    }

    public void printf(String string, Object... args) {
        print(String.format(string, args));
    }

}
