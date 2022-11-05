package com.cts.fse.command;

import com.cts.fse.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id){
        super(id);
    }
}
