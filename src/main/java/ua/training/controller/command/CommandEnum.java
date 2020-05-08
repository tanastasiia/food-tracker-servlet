package ua.training.controller.command;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    HOME(new HomeCommand());

    private Command command;
    private String path;
    CommandEnum(Command command){
        this.command = command;
        this.path = "/" + this.name().toLowerCase();
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
