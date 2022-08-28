package com.ferhatozcelik.terminal.views;

import java.util.ArrayList;

import com.ferhatozcelik.terminal.R;
import com.ferhatozcelik.terminal.data.ListClickListener;
import com.ferhatozcelik.terminal.model.FastButton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FastButtonListAdapter extends RecyclerView.Adapter<FastButtonListAdapter.ViewHolder>{

	ArrayList<FastButton> buttonArrayList;
	LayoutInflater layoutInflater;
	ListClickListener onClickListener;
	Context context;

	public FastButtonListAdapter(ArrayList<FastButton> buttonArrayList, Context context, ListClickListener onClickListener) {
		this.buttonArrayList = buttonArrayList;
		this.onClickListener = onClickListener;
		this.context = context;
	}

	@NonNull
	@Override
	public FastButtonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		layoutInflater = LayoutInflater.from(context);
		View view = LayoutInflater.from(context).inflate(R.layout.item_button,parent,false);
		ViewHolder viewHolder = new ViewHolder(view);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull FastButtonListAdapter.ViewHolder holder, int position) {

		FastButton fastButton = buttonArrayList.get(position);
		if (fastButton.getTitle() != null){
			holder.itemTitle.setText(fastButton.getTitle());
			holder.itemIcon.setVisibility(View.GONE);
			holder.itemTitle.setVisibility(View.VISIBLE);
		}else{
			holder.itemIcon.setImageResource(fastButton.getIcon());
			holder.itemTitle.setVisibility(View.GONE);
			holder.itemIcon.setVisibility(View.VISIBLE);
		}

		holder.itemView.setOnClickListener(view -> onClickListener.onItemClick(fastButton));
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

	@Override
	public int getItemCount() {
		return buttonArrayList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		TextView itemTitle;
		ImageView itemIcon;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			itemTitle = itemView.findViewById(R.id.itemTitle);
			itemIcon = itemView.findViewById(R.id.itemIcon);
		}
	}
}
