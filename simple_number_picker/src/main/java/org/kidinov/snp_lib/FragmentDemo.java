package org.kidinov.snp_lib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FragmentDemo extends Fragment {

  private RecyclerView recycler_view;
  private TextView tv_selected_item;
  private LinearLayoutManager llm;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_demo, container, false);
    recycler_view = rootView.findViewById(R.id.recycler_view);
    tv_selected_item = rootView.findViewById(R.id.tv_selected_item);
    initRecyclerView();
    return rootView;
  }

  public void initRecyclerView() {
    llm = new LinearLayoutManager(requireContext());
    recycler_view.setLayoutManager(llm);
    SimplePickerAdapter adapter = new SimplePickerAdapter(requireContext(), params());
    recycler_view.setAdapter(adapter);
    recycler_view.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            newValueSelected(adapter.getData(selectedIndex()));
          }
        });
  }

  private void newValueSelected(String data) {
    tv_selected_item.setText(data);
  }

  private List<String> params() {
    List<String> result = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      result.add("Tier" + i);
    }
    return result;
  }

  private int selectedIndex() {
    return llm.findFirstVisibleItemPosition()
        + (llm.findLastVisibleItemPosition() - llm.findFirstVisibleItemPosition()) / 2;
  }
}
