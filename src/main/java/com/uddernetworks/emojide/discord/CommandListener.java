package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.EditableTextFrame;
import com.uddernetworks.emojide.gui.EmptyFrame;
import com.uddernetworks.emojide.gui.StaticTextFrame;
import com.uddernetworks.emojide.gui.WindowFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandListener extends ListenerAdapter {

    private EmojIDE emojIDE;
    private Displayer displayer;

    public CommandListener(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        var message = event.getMessage().getContentRaw().toLowerCase();
        var emojiManager = emojIDE.getEmojiManager();;

        switch (message) {
            case "!start":
                event.getMessage().delete().queue();
                System.out.println("Creating displayer...");

                displayer = new Displayer(emojIDE, event.getTextChannel());

                var window = new WindowFrame(displayer, 58, 10); // 58 max
                var textEdit = new EditableTextFrame(displayer, 34, 6);
//                var text = new StaticTextFrame(displayer, 34, 6, "Rawr x3 *nuzzles* how are you *pounces on you* you're so warm o3o *notices you have a bulge* o: someone's happy ;) *nuzzles your necky wecky~* murr~ hehehe *rubbies your bulgy wolgy* you're so big :oooo *rubbies more on your bulgy wolgy* it doesn't stop growing ·///· *kisses you and lickies your necky* daddy likies (; *nuzzles wuzzles* I hope daddy really likes $: *wiggles butt and squirms* I want to see your big daddy meat~ *wiggles butt* I have a little itch o3o *wags tail* can you please get my itch~ *puts paws on your chest* nyea~ its a seven inch itch *rubs your chest* can you help me pwease *squirms* pwetty pwease *sad face* I need to be punished *runs paws down your chest and bites lip* like I need to be punished really good~ *paws on your bulge as I lick my lips* I'm getting thirsty. I can go for some milk *unbuttons your pants as my eyes glow* you smell so musky :v *licks shaft* mmmm~ so musky *drools all over your cock* your daddy meat I like *fondles* Mr. Fuzzy Balls hehe *puts snout on balls and inhales deeply* oh god im so hard~ *licks balls* punish me daddy~ nyea~ *squirms more and wiggles butt* I love your musky goodness *bites lip* please punish me *licks lips* nyea~ *suckles on your tip* so good *licks pre of your cock* salty goodness~ *eyes role back and goes balls deep* mmmm~ *moans and suckles* o3o");
                window.addChild(textEdit, 2, 2);

                var emptyPane = new EmptyFrame(displayer, 19, 6)
                        .setBackground(emojiManager.getEmoji("green2"));

                window.addChild(emptyPane, 37, 2);

                displayer.setChild(window);
                displayer.update();

                this.emojIDE.getKeyboardInputManager().createKeyboard(event.getTextChannel());
                break;
            case "!stop":
                event.getMessage().delete().queue();
                this.emojIDE.getKeyboardInputManager().removeKeyboard();
                this.displayer.stop();
                break;
        }
    }
}
