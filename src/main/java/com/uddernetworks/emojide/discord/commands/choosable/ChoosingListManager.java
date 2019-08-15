package com.uddernetworks.emojide.discord.commands.choosable;

import com.uddernetworks.emojide.main.ChoosableEnum;
import com.uddernetworks.emojide.main.EmojIDE;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ChoosingListManager {

    /**
     * Creates a {@link ChoosingList} with the given parameters.
     *
     * @param emojIDE The {@link EmojIDE} instance
     * @param active The supplier to get the active Enum
     * @param onSelect The consumer to be invoked when a new item is selected
     * @param choosableEnum The class of the Enum
     * @param <T> The {@link ChoosableEnum} Enum being selected
     * @return The {@link ChoosableEnum} created
     */
    <T extends ChoosableEnum> ChoosingList<T> createChoosingList(EmojIDE emojIDE, Supplier<T> active, Consumer<T> onSelect, Class<T> choosableEnum);

    /**
     * Gets a {@link ChoosingList} by its unique name, if existant.
     *
     * @param name The unique name of the {@link ChoosingList}
     * @return The {@link ChoosingList} if existent
     */
    Optional<ChoosingList> getChoosingListByName(String name);

}
