package com.epam.training.ticketservice;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MainPromptProvider implements PromptProvider {
    Logger log = Logger.getLogger("PROMPT LOGGER");

    @Override
    public AttributedString getPrompt() {
        //log.warning("Creating prompt");
        return new AttributedString("Ticket service>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
