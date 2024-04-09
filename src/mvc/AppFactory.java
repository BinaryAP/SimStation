package mvc;

public interface AppFactory {

    public Model makeModel();

    public View makeView(Model model);

    Command makeEditCommand(Model model, String type, Object source);

    public String getTitle();
    public String[] getHelp();
    public String about();
    public String[] getEditCommands();

}
