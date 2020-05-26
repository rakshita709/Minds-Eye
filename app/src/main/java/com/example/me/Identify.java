package com.example.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.applozic.mobicommons.commons.core.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.kommunicate.KmChatBuilder;
import io.kommunicate.Kommunicate;
import io.kommunicate.callbacks.KmCallback;

public class Identify extends Fragment {


    private View rootLayout;
    String APP_ID = "24949b7827ca1adafad26ba9beb5cf346";
    String bot = "mindseye-ltkox";
    Button chatBotBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootLayout = inflater.inflate(R.layout.fragment_identify, container, false);
        chatBotBtn = rootLayout.findViewById(R.id.ChatBotBtn);
        Kommunicate.init(rootLayout.getContext(), APP_ID);

        chatBotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> botList = new ArrayList(); botList.add(bot); //enter your integrated bot Ids
                new KmChatBuilder(rootLayout.getContext()).setChatName("Support")
                        .setBotIds(botList)
                        .launchChat(new KmCallback() {
                            @Override
                            public void onSuccess(Object message) {
                                Utils.printLog(rootLayout.getContext(), "ChatTest", "Success : " + message);
                            }

                            @Override
                            public void onFailure(Object error) {
                                Utils.printLog(rootLayout.getContext(), "ChatTest", "Failure : " + error);
                            }
                        });
            }
        });



        return rootLayout;
    }
}

