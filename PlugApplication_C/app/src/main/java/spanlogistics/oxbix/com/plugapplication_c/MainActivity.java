package spanlogistics.oxbix.com.plugapplication_c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ryg.dynamicload.DLBasePluginActivity;
import com.ryg.dynamicload.internal.DLIntent;
/**
 * @author cjy
 * @version V1.0
 * @Description 插件程序 C
 * @date 2016/5/30
 */
public class MainActivity extends DLBasePluginActivity implements View.OnClickListener {

    private TextView mTitleText;
    private Button mEditBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
    }

    public void findView() {
        mTitleText = (TextView) that.findViewById(R.id.id_title_text);
        mEditBtn = (Button) that.findViewById(R.id.id_exit_btn);
        mEditBtn.setOnClickListener(this);
    }

    public void init() {
        String content = getIntent().getStringExtra("text");
        mTitleText.setText(content);
    }

    @Override
    public void onClick(View v) {
        DLIntent dlIntent = new DLIntent();
        dlIntent.putExtra("content", "子程序 " + that.getPackageName() + "回调");
        that.setResult(0, dlIntent);
        that.finish();

    }
}
