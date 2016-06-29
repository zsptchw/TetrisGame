package com.chw.tetrisgame.ui;


import com.chw.tetrisgame.R;
import com.chw.tetrisgame.view.TetrisView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

public class TetrisActivity extends Activity {
	private TetrisView tetrisView;
	private TextView gameStatusText;
	private TextView scoreText;
	private TextView maxScoreText;
	private static String ICICLE_KEY = "tetris-view";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        tetrisView = (TetrisView)findViewById(R.id.tetris);
        gameStatusText = (TextView)findViewById(R.id.game_status_text);
		tetrisView.setStatusTextView(gameStatusText);
		scoreText = (TextView)findViewById(R.id.score_text);
		maxScoreText = (TextView) findViewById(R.id.maxScore);
		tetrisView.setScoreTextView(scoreText);
		tetrisView.setMaxScoreTextView(maxScoreText);
        if (savedInstanceState == null) {
            //没有可供恢复的状态，�?始新的游�?
            tetrisView.setMode(TetrisView.READY);
        } else {
            //恢复上一次被中断的游戏（如Home键�??出）
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                tetrisView.restoreState(map);
            } else {
                tetrisView.setMode(TetrisView.PAUSE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        tetrisView.setMode(TetrisView.PAUSE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBundle(ICICLE_KEY, tetrisView.saveState());
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_DPAD_UP ||
			keyCode == KeyEvent.KEYCODE_DPAD_DOWN ||
			keyCode == KeyEvent.KEYCODE_DPAD_LEFT ||
			keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			return tetrisView.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}
}
