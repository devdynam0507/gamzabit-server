package com.gamzabit.engine;

import java.util.List;

public interface OrderPostProcessor<Message> {

    void handle(List<Message> messages);
}
