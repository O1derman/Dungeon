package olderman.dungeon;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.SwitchPreference;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AndroidOS implements OS {

    private static final int ACTION_APPEND = 0;
    private static final int ACTION_CLEAR = 1;
    private static final int ACTION_RESET = 2;
    private static final int ACTION_COLOR = 3;
    private static final int ACTION_ATTRIBUTE = 4;
    private static final int ACTION_PRINT_ASCII_ART = 5;
    private static final int ACTION_FILL_LINE = 6;

    private static class Action {
        public final int type;
        public final Object data;

        public Action(int type, Object data) {
            this.type = type;
            this.data = data;
        }
    }

    private List<Action> actions = new ArrayList<>(); // confined to game thread

    private static final int RESULT_EXIT = -1;

    // in our case, main thread should be also the ui thread
    // https://developer.android.com/studio/write/annotations.html#thread-annotations
    private final MyHandler handler = new MyHandler(Looper.getMainLooper());

    private static class MyHandler extends Handler {

        public static final int MSG_CREATE_BUTTONS = 0;
        public static final int MSG_DO_ACTIONS = 1;

        // all confined to ui thread
        private final List<Action> savedActions = new ArrayList<>();
        private MainActivity activity;
        private TextView textView;
        private LinearLayout buttons;
        private Switch changeMap;
        private final SpannableStringBuilder sb = new SpannableStringBuilder();
        private String[] buttonLabels;

        public final FutureValue<Integer> result = new FutureValue<>(); // final and thread-safe

        public MyHandler(Looper looper) {
            super(looper);
        }


        public void hideSwitch() {
            Looper.prepare();
        }

        public void visibleSwitch() {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    changeMap.setVisibility(changeMap.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });
        }

        public boolean checkSwitch() {
            return changeMap.isChecked();
        }


        @UiThread
        public void set(MainActivity activity, TextView textView, LinearLayout buttons, Switch changeMap) {
            if (this.activity != null) {
                throw new IllegalStateException();
            }
            Objects.requireNonNull(activity);
            Objects.requireNonNull(buttons);
            Objects.requireNonNull(textView);
            Objects.requireNonNull(changeMap);
            this.activity = activity;
            this.textView = textView;
            this.buttons = buttons;
            this.changeMap = changeMap;
            createButtonsImpl();
            doActionsImpl(savedActions); // will also call flushImpl()
            savedActions.clear();


            changeMap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
        }

        @UiThread
        public void onActivityDestroy() {
            if (activity == null) {
                throw new IllegalStateException();
            }
            activity = null;
            textView = null;
            buttons = null;
            changeMap = null;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CREATE_BUTTONS:
                    buttonLabels = (String[]) msg.obj;
                    if (activity != null)
                        createButtonsImpl();
                    break;
                case MSG_DO_ACTIONS:
                    @SuppressWarnings("unchecked")
                    List<Action> actions = (List<Action>) msg.obj;
                    if (activity != null) {
                        doActionsImpl(actions);
                    } else {
                        savedActions.addAll(actions);
                    }
                    break;
            }
        }

        private void createButtonsImpl() {
            if (buttonLabels != null) {
                for (int i = 0; i < buttonLabels.length; i++) {
                    createButton(i + 1, buttonLabels[i]);
                }
            }
        }

        private void createButton(final int i, final String text) {
            Button button = new Button(activity);
            button.setText(text);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (text.equals("Play video to get full energy")) {
                        activity.startVideoAd();
                        buttons.removeAllViews();
                        buttonLabels = null;
                    } else {
                        buttons.removeAllViews();
                        buttonLabels = null;
                    }
                    result.set(i);
                }
            });
            buttons.addView(button, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        private void doActionsImpl(List<Action> actions) {
            for (Action action : actions) {
                switch (action.type) {
                    case ACTION_APPEND:
                        appendImpl((CharSequence) action.data);
                        break;
                    case ACTION_CLEAR:
                        clearImpl();
                        break;
                    case ACTION_RESET:
                        resetImpl();
                        break;
                    case ACTION_COLOR:
                        colorImpl((Style.ColorStyle) action.data);
                        break;
                    case ACTION_ATTRIBUTE:
                        attributeImpl((Style.AttributeStyle) action.data);
                        break;
                    case ACTION_PRINT_ASCII_ART:
                        printAsciiArtImpl((String) action.data);
                        break;
                    case ACTION_FILL_LINE:
                        fillLineImpl((String) action.data);
                        break;
                }
            }
            flushImpl();
        }

        private void flushImpl() {
            textView.setText(sb);
            textView.requestLayout();
        }

        private void appendImpl(CharSequence text) {
            sb.append(text);
        }

        private void clearImpl() {
            sb.clear();
        }

        private final Object[] spans = new Object[NUM_SPANS];
        private final int[] spanStarts = new int[NUM_SPANS];

        private static final int BG = 0;
        private static final int FG = 1;
        private static final int CENTER = 2;
        private static final int NUM_SPANS = 3;

        private void reset(int i) {
            if (spans[i] != null) {
                sb.setSpan(spans[i], spanStarts[i], sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spans[i] = null;
            }
            spanStarts[i] = sb.length();
        }

        public void resetImpl() {
            for (int i = 0; i < spans.length; i++) {
                reset(i);
            }
        }

        public void colorImpl(Style.ColorStyle style) {
            int i = style.isBg() ? BG : FG;
            reset(i);
            if (style.getColor() != null) {
                int color = ContextCompat.getColor(activity, mapColor(style));
                if (style.isBg()) {
                    spans[i] = new BackgroundColorSpan(color);
                } else {
                    spans[i] = new ForegroundColorSpan(color);
                }
            }
        }

        public void attributeImpl(Style.AttributeStyle style) {
            int i;
            switch (style.getAttribute()) {
                case CENTER:
                    i = CENTER;
                    break;
                default:
                    return;
            }
            reset(i);
            if (style.isOn()) {
                switch (style.getAttribute()) {
                    case CENTER:
                        spans[i] = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER);
                        break;
                }
            }
        }

        private static final Pattern NEWLINE = Pattern.compile("[\\r\\n]+");

        public void printAsciiArtImpl(String asciiArt) {
            Paint paint = textView.getPaint();
            int textSize = (int) paint.getTextSize();
            int width = textView.getWidth();
            Matcher matcher = NEWLINE.matcher(asciiArt);
            StringBuilder str = new StringBuilder(asciiArt);
            float asciiArtWidth = 0;
            int longestLane = 0;
            int indexOfEnter = 0;
            int indexAfterEnter = 0;
            float space = paint.measureText(" ");
            while (matcher.find()) {
                float lineWidth = paint.measureText(asciiArt, longestLane, matcher.start());
                longestLane = matcher.end();
                if (lineWidth > asciiArtWidth) {
                    asciiArtWidth = lineWidth;
                }
            }
            if (asciiArtWidth > width) {
                textSize = (int) (textSize * width / asciiArtWidth);
            }
            if (asciiArtWidth < width) {
                Float numberOfSpaces = (width - asciiArtWidth) / 2 / space;
                int roundNumberOfSpaces = Math.round(numberOfSpaces);
                while (indexOfEnter >= 0) {
                    for (int i = 0; i < roundNumberOfSpaces; i++) {
                        str.insert(indexOfEnter + 1, " ");
                    }
                    indexOfEnter = str.indexOf("\n", indexAfterEnter);
                    indexAfterEnter = indexOfEnter + 13;
                }
            }
            int start = sb.length();
            sb.append(str.toString());
            sb.setSpan(new AbsoluteSizeSpan(textSize), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        public void fillLineImpl(String text) {
            float textWidth = textView.getPaint().measureText(text);
            int textViewWidth = textView.getWidth();
            int count = (int) (textViewWidth / textWidth);
            for (int i = 0; i < count; i++) {
                appendImpl(text);
            }
        }

    }

    @UiThread
    public void set(MainActivity activity, TextView textView, LinearLayout buttons, Switch changeMap) {
        handler.set(activity, textView, buttons, changeMap);
    }

    @UiThread
    public void onActivityDestroy() {
        handler.onActivityDestroy();
    }

    @UiThread
    public void stop() {
        handler.result.set(RESULT_EXIT);
    }

    @Override
    @WorkerThread
    public int uzivatVolba(final String... options) {
        try {
            // non-atomic check-then-act
            /*
             * the worst thing that can happen is that we create buttons when exiting but
             * maybe this may change with new features (probably not)
             *
             * maybe we can use client-side locking?
             *
             * or use getIfSet() - that wouldn't work because someone could set the result
             * when calling createButtons()
             */
            if (!handler.result.isSet()) {
                createButtons(options);
            }
            int r = handler.result.get();
            // END non-atomic check-then-act

            if (r == RESULT_EXIT) {
                throw new ExitException();
            }
            clear();
            return r;
        } catch (InterruptedException e) {
            throw new ExitException(e); // TODO maybe some better interrupt handling? or is this fine?
        } finally {
            handler.result.unset();
        }
    }

    @WorkerThread
    private void createButtons(String[] buttonLabels) {
        handler.sendMessage(handler.obtainMessage(MyHandler.MSG_CREATE_BUTTONS, buttonLabels));
    }

    @Override
    @WorkerThread
    public void flush() {
        handler.sendMessage(handler.obtainMessage(MyHandler.MSG_DO_ACTIONS, actions));
        actions = new ArrayList<>();
    }

    private void action(int type, Object data) {
        actions.add(new Action(type, data));
    }

    private void action(int type) {
        action(type, null);
    }

    @Override
    public void clear() {
        action(ACTION_CLEAR);
        flush();
    }

    @Override
    public void print(String string) {
        action(ACTION_APPEND, string);
    }

    @Override
    public void println() {
        print("\n");
    }

    @Override
    public void reset() {
        action(ACTION_RESET);
    }

    @Override
    public void color(Style.ColorStyle style) {
        action(ACTION_COLOR, style);
    }

    @Override
    public void attribute(Style.AttributeStyle style) {
        action(ACTION_ATTRIBUTE, style);
    }

    @Override
    public void printAsciiArt(String asciiArt) {
        action(ACTION_PRINT_ASCII_ART, asciiArt);
    }

    @Override
    public void fillLine(String text) {
        action(ACTION_FILL_LINE, text);
    }

    @Override
    public void createSwitch() {
        handler.visibleSwitch();
    }

    @Override
    public void hideSwitch() {
        handler.hideSwitch();

    }

    @Override
    public boolean checkSwitch() {
        return handler.checkSwitch();
    }

    @Override
    public void startVideoAd() {
        // MainActivity.
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded() {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void beep() {

    }

    @WorkerThread
    @Override
    public void trySaveData(ArrayList<Object> data) throws FileNotFoundException {
        FileOutputStream fos = handler.activity.openFileOutput(Constants.saveFileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(fos);
            os.writeObject(data);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WorkerThread
    @Override
    public FileInputStream tryLoadData() throws FileNotFoundException {
        FileInputStream fis = handler.activity.openFileInput(Constants.saveFileName);
        return fis;
    }

    @WorkerThread
    @Override
    public boolean saveFileExists() {
        File file = new File(handler.activity.getFilesDir(), Constants.saveFileName);
        return file.exists();
    }

    private static int mapColor(Style.ColorStyle color) {
        if (color.isBright()) {
            switch (color.getColor()) {
                case BLACK:
                    return R.color.brightBlack;
                case RED:
                    return R.color.brightRed;
                case GREEN:
                    return R.color.brightGreen;
                case YELLOW:
                    return R.color.brightYellow;
                case BLUE:
                    return R.color.brightBlue;
                case MAGENTA:
                    return R.color.brightMagenta;
                case CYAN:
                    return R.color.brightCyan;
                case WHITE:
                    return R.color.brightWhite;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            switch (color.getColor()) {
                case BLACK:
                    return R.color.black;
                case RED:
                    return R.color.red;
                case GREEN:
                    return R.color.green;
                case YELLOW:
                    return R.color.yellow;
                case BLUE:
                    return R.color.blue;
                case MAGENTA:
                    return R.color.magenta;
                case CYAN:
                    return R.color.cyan;
                case WHITE:
                    return R.color.white;
                default:
                    throw new IllegalArgumentException();
            }
        }

    }

}
