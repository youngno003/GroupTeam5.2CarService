package d14cqcp01.group5.carservices;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by T420 on 3/14/2018.
 */

public class PageChoXuLiOfHistoryLayoutModel extends Fragment {
        ArrayListItemAdapterModel adapter=null;

    public void setAdapter(ArrayListItemAdapterModel adapter) {
        this.adapter = adapter;
    }

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_tabpage_lich_su_dat_ve, null);

            ListView lv = (ListView) view.findViewById(R.id.listView);
            lv.setAdapter(adapter);
            return view;
        }
}
