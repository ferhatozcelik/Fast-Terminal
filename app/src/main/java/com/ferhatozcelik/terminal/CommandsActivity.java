package com.ferhatozcelik.terminal;

import java.util.ArrayList;
import java.util.Collections;

import com.ferhatozcelik.terminal.data.ListClickListener;
import com.ferhatozcelik.terminal.model.FastButton;
import com.ferhatozcelik.terminal.views.CommandDatabase;
import com.ferhatozcelik.terminal.views.CommandListAdapter;
import com.ferhatozcelik.terminal.views.FastButtonListAdapter;
import com.ferhatozcelik.terminal.views.helper.SimpleItemTouchHelperCallback;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommandsActivity extends AppCompatActivity {

	private ImageButton headerBackButton;
	private RecyclerView commandsList;
	private Context context = CommandsActivity.this;
	private ImageView empty;
	private ItemTouchHelper mItemTouchHelper;
	private ImageButton addNewCommand;
	private CommandListAdapter customAddCommandsadapter;
	private boolean isSaved = false;

	@SuppressLint("NotifyDataSetChanged")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_commandlist);

		headerBackButton = findViewById(R.id.headerBackButton);
		commandsList = findViewById(R.id.commandsList);
		empty = findViewById(R.id.empty);
		addNewCommand = findViewById(R.id.addNewCommand);
		headerBackButton.setOnClickListener(view -> finish());
		addNewCommand.setOnClickListener(view -> ShowSaveCommandDialog());

		getCommandList();

	}

	@SuppressLint("NotifyDataSetChanged")
	private void getCommandList() {

		CommandDatabase commandDatabase = new CommandDatabase(context);
		ArrayList<FastButton> commandsButtons = commandDatabase.getCommandList();

		if (commandsButtons.size() > 0) {
			empty.setVisibility(View.GONE);
		} else {
			empty.setVisibility(View.VISIBLE);
		}

		ListClickListener listItemClickCommandsListennerDelete = commands -> {

			commandDatabase.deleteCommand(commands.getId());
			Toast.makeText(context, "Silindi!", Toast.LENGTH_SHORT).show();
			getCommandList();

		};

		ListClickListener listItemClickCommandsListennerEdit = this::ShowEditCommandDialog;

		LinearLayoutManager linearLayoutCommandsManager = new LinearLayoutManager(context);
		linearLayoutCommandsManager.setOrientation(RecyclerView.VERTICAL);
		commandsList.setLayoutManager(linearLayoutCommandsManager);

		customAddCommandsadapter = new CommandListAdapter(commandsButtons, context, listItemClickCommandsListennerDelete, listItemClickCommandsListennerEdit);
		commandsList.setAdapter(customAddCommandsadapter);
		customAddCommandsadapter.notifyDataSetChanged();
	}

	private void ShowSaveCommandDialog() {

		LayoutInflater factory = LayoutInflater.from(this);
		final View commandDialogView = factory.inflate(R.layout.command_add_dialog, null);
		final AlertDialog addcommandDialog = new AlertDialog.Builder(this).create();
		addcommandDialog.setView(commandDialogView);

		EditText dialog_command_title = commandDialogView.findViewById(R.id.dialog_command_title);
		EditText dialog_command = commandDialogView.findViewById(R.id.dialog_command);

		commandDialogView.findViewById(R.id.btn_ok).setOnClickListener(v -> {

			String ti = dialog_command_title.getText().toString();
			String co = dialog_command.getText().toString();

			if (!ti.isEmpty() || !co.isEmpty()) {
				SaveCommand(ti, co);
				getCommandList();
			} else {
				Toast.makeText(context, "Boş Alanları Doldurunuz", Toast.LENGTH_SHORT).show();
			}

			addcommandDialog.dismiss();
		});
		commandDialogView.findViewById(R.id.btn_cancel).setOnClickListener(v -> addcommandDialog.dismiss());
		addcommandDialog.show();
		addcommandDialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_dark_bg);

	}

	private void ShowEditCommandDialog(FastButton commands) {
		LayoutInflater factory = LayoutInflater.from(this);
		final View commandDialogView = factory.inflate(R.layout.command_add_dialog, null);
		final AlertDialog addcommandDialog = new AlertDialog.Builder(this).create();
		addcommandDialog.setView(commandDialogView);

		EditText dialog_command_title = commandDialogView.findViewById(R.id.dialog_command_title);
		EditText dialog_command = commandDialogView.findViewById(R.id.dialog_command);

		dialog_command_title.setText(commands.getTitle());
		dialog_command.setText(commands.getId());

		commandDialogView.findViewById(R.id.btn_ok).setOnClickListener(v -> {

			String ti = dialog_command_title.getText().toString();
			String co = dialog_command.getText().toString();

			if (!ti.isEmpty() || !co.isEmpty()) {
				EditCommand(ti, co, commands.getId());
				getCommandList();
			} else {
				Toast.makeText(context, "Boş Alanları Doldurunuz", Toast.LENGTH_SHORT).show();
			}

			addcommandDialog.dismiss();
		});

		commandDialogView.findViewById(R.id.btn_cancel).setOnClickListener(v -> addcommandDialog.dismiss());


		addcommandDialog.show();
		addcommandDialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_dark_bg);

	}

	private void SaveCommand(String title, String command) {
		CommandDatabase commandDatabase = new CommandDatabase(context);
		ArrayList<FastButton> commandsButtons = commandDatabase.getCommandList();

		for (int i = 0; i < commandsButtons.size(); i++) {
			if (commandsButtons.get(i).getId().equals(command)){
				isSaved = true;
				break;
			}else {
				isSaved = false;
			}
		}

		if (!isSaved){
			ArrayList<FastButton> commandsButtonsTemp = new ArrayList<>(commandsButtons);
			commandsButtonsTemp.add(new FastButton(command, title, null));
			commandDatabase.saveCommandList(commandsButtonsTemp);
		}else{
			Toast.makeText(context, "Komut Zaten Kayıtlı!", Toast.LENGTH_SHORT).show();
		}
	}

	private void EditCommand(String title, String newId, String oldId) {

		CommandDatabase commandDatabase = new CommandDatabase(context);
		ArrayList<FastButton> commandsButtons = commandDatabase.getCommandList();

		for (int i = 0; i < commandsButtons.size(); i++) {
			if (commandsButtons.get(i).getId().equals(newId)){
				isSaved = true;
				break;
			}else {
				isSaved = false;
			}
		}

		if (!isSaved){
			commandDatabase.editCommand(title, newId, oldId);
		}else{
			Toast.makeText(context, "Komut Zaten Kayıtlı!", Toast.LENGTH_SHORT).show();
		}

	}

}
