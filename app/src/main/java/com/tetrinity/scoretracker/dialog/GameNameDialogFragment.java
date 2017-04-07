package com.tetrinity.scoretracker.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.tetrinity.scoretracker.R;
import com.tetrinity.scoretracker.databinding.DialogGameNameBinding;
import com.tetrinity.scoretracker.game.Game;

public class GameNameDialogFragment extends DialogFragment {

    public interface OnGameNameSetListener {
        void onGameNameSet(String newGameName);
    }

    private OnGameNameSetListener listener;
    private DialogGameNameBinding binding;
    private Game game;

    private static final String GAME = "game";

    public static GameNameDialogFragment newInstance(Game game){
        GameNameDialogFragment fragment = new GameNameDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME, game);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){
        game = (Game)getArguments().getSerializable(GAME);

        final Activity activity = getActivity();
        initListeners(activity);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(binding.getRoot());

        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newGameName = binding.gameName.getText().toString();
                listener.onGameNameSet(newGameName);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    private void initListeners(Activity activity){
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_game_name, null, false);
        binding.setDataSource(game);

        listener = (OnGameNameSetListener)activity;
    }
}
