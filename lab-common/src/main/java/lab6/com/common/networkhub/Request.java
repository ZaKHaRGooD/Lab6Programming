package lab6.com.common.networkhub;

import java.util.List;
import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private List<Object> args;

    public Request(String commandName) {
        this.commandName = commandName;
    }

    public Request(String commandName, List<Object> args) {
        this.commandName = commandName;
        this.args = args;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<Object> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", args=" + args +
                '}';
    }

}
