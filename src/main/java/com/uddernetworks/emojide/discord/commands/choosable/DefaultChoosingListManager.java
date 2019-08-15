package com.uddernetworks.emojide.discord.commands.choosable;

import com.uddernetworks.emojide.discord.font.Font;
import com.uddernetworks.emojide.main.ChoosableEnum;
import com.uddernetworks.emojide.main.EmojIDE;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DefaultChoosingListManager implements ChoosingListManager {

    private EmojIDE emojIDE;
    private List<ChoosingList> choosingLists = new ArrayList<>();

    public DefaultChoosingListManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;

        emojIDE.getWebCallbackHandler().registerCommandCallback("apply", Arrays.asList("id", "ordinal", "originating"), (member, channel, query) -> {
            var currentListOptional = getChoosingListByName(query.get("id"));
            if (currentListOptional.isEmpty()) return;

            var activatingString = query.get("ordinal");
            int activating;
            if (!StringUtils.isNumeric(activatingString) || (activating = Integer.parseInt(activatingString)) < 0 || activating >= Font.values().length) return;

            var originatingString = query.get("originating");
            if (!StringUtils.isNumeric(originatingString)) return;
            currentListOptional.get().activateEnum(member, channel, Long.parseLong(originatingString), activating);
        });
    }

    @Override
    public <T extends ChoosableEnum> ChoosingList<T> createChoosingList(EmojIDE emojIDE, Supplier<T> active, Consumer<T> onSelect, Class<T> choosableEnum) {
        var choosingList = new DefaultChoosingList<T>(emojIDE, active, onSelect, choosableEnum);
        choosingLists.add(choosingList);
        return choosingList;
    }

    @Override
    public Optional<ChoosingList> getChoosingListByName(String name) {
        return choosingLists.stream().filter(choosingList -> choosingList.getName().equals(name)).findFirst();
    }
}
