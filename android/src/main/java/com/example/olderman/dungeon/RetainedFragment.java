package com.example.olderman.dungeon;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

/**
 *
 */
public class RetainedFragment extends Fragment {

    private AndroidOS os = new AndroidOS();
    private Dungeon dungeon = new Dungeon(os);
    private boolean running = false;

    public void set(Activity activity, TextView textView, LinearLayout buttons) {
        os.set(activity, textView, buttons);
    }

    public void start() {
        if (!running) {
            running = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        dungeon.run();
                    } catch (ExitException e) {
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }

    @Override
    public void onDetach() {
        os.onActivityDestroy();
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        os.stop();
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

}
