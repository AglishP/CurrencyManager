package main;

import java.util.List;

public enum WorkType {

    ADD("add"),
    ADD_ONE_LINE("a"),
    REMOVE("minus"),
    VIEW("view"),
    SAVE("save"),
    HELP("help"),
    SUMMARY("summary"),
    EMPTY(""),
    EXIT("exit");

    private String name;

    private List<String> args;

    WorkType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setArgs(List<String> value) {
        args = value;
    }

    public List<String> getArgs() {
        return args;
    }

}
