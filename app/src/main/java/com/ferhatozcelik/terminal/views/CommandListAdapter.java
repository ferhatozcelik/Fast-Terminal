package com.ferhatozcelik.terminal.views;

import java.util.ArrayList;
import java.util.Collections;

import com.ferhatozcelik.terminal.R;
import com.ferhatozcelik.terminal.data.ListClickListener;
import com.ferhatozcelik.terminal.model.FastButton;
import com.ferhatozcelik.terminal.views.helper.ItemTouchHelperAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommandListAdapter extends RecyclerView.Adapter<CommandListAdapter.ViewHolder> {

	ArrayList<FastButton> buttonArrayList;
	LayoutInflater layoutInflater;
	ListClickListener onClickListenerDelete;
	ListClickListener onClickListenerEdit;
	Context context;

	public CommandListAdapter(ArrayList<FastButton> buttonArrayList, Context context,
			ListClickListener onClickListenerDelete,
			ListClickListener onClickListenerEdit) {

		this.buttonArrayList = buttonArrayList;
		this.onClickListenerDelete = onClickListenerDelete;
		this.onClickListenerEdit = onClickListenerEdit;
		this.context = context;
	}

	@NonNull
	@Override
	public CommandListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		layoutInflater = LayoutInflater.from(context);
		View view = LayoutInflater.from(context).inflate(R.layout.item_command,parent,false);
		return new ViewHolder(view);
	}

	@SuppressLint("NotifyDataSetChanged")
	@Override
	public void onBindViewHolder(@NonNull CommandListAdapter.ViewHolder holder, int position) {

		FastButton commands = buttonArrayList.get(position);
		holder.itemName.setText(commands.getTitle());
		holder.itemDes.setText(commands.getId());
		holder.itemIcon.setImageResource(R.drawable.ic_code);
		holder.itemDelete.setOnClickListener(view -> onClickListenerDelete.onItemClick(commands));
		holder.itemEdit.setOnClickListener(view -> onClickListenerEdit.onItemClick(commands));

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

		TextView itemName, itemDes;
		ImageView itemIcon;
		ImageButton itemDelete, itemEdit;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			itemName = itemView.findViewById(R.id.itemName);
			itemDes = itemView.findViewById(R.id.itemDes);
			itemIcon = itemView.findViewById(R.id.itemIcon);
			itemDelete = itemView.findViewById(R.id.itemDelete);
			itemEdit = itemView.findViewById(R.id.itemEdit);
		}
	}
}
