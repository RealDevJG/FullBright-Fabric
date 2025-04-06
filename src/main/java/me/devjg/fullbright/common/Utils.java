package me.devjg.fullbright.common;

import java.net.URI;
import java.net.URISyntaxException;

import net.minecraft.text.MutableText;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Text;

public class Utils {
    public static MutableText getChatPrefix() {
        return MutableText.of(PlainTextContent.of("§8[§eFullbright§8] "));
    }

    public static Text formChatMessage(String appendee) {
        return getChatPrefix().append(appendee);
    }

    public static Text formChatMessage(Text appendee) {
        return getChatPrefix().append(appendee);
    }

    public static Text formChatMessage(String... appendees) {
        MutableText chatMessage = getChatPrefix();

        for (String message : appendees)
            chatMessage.append(message + " ");

        return chatMessage;
    }

    public static URI getUri(String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
