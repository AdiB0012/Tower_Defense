public interface InputSystem {
    public UserInput getUserInput();

    public void command(GameObject userObject, UserInput userInput);
}
