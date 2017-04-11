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
import com.tetrinity.scoretracker.databinding.DialogPlayerCountBinding;
import com.tetrinity.scoretracker.game.Game;

public class PlayerCountDialogFragment extends DialogFragment {

    public interface OnPlayerCountSetListener {
        void onPlayerCountSet(Integer playerCount);
    }

    private OnPlayerCountSetListener listener;
    private DialogPlayerCountBinding binding;
    private Game game;

    private static final String GAME = "game";

    public static PlayerCountDialogFragment newInstance(Game game){
        PlayerCountDialogFragment fragment = new PlayerCountDialogFragment();

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
                Integer newPlayerCount = Integer.parseInt(binding.playerCount.getText().toString());
                listener.onPlayerCountSet(newPlayerCount);
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
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_player_count, null, false);
        binding.setDataSource(game);

        listener = (OnPlayerCountSetListener)activity;
    }
}
