package personal.demo.nodesview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    private String[] nodes={"提交申请","带传材料","待审核","待面签","待放款","已放款"};
    private String[] times={"2015/8/14 0:0:0","2015/8/14 0:0:0","2015/8/14 0:0:0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoanNodesView lnv=(LoanNodesView)findViewById(R.id.lnv);
        lnv.setNodes(nodes,4);
        lnv.setTimes(times);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
