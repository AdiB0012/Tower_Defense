public class UserInput {
    public int  mousePressedX, mousePressedY, mouseMovedX, mouseMovedY, mouseButton;
    public char keyPressed;

    public UserInput(int mpx,int mpy,int mmx,int mmy,int mb,char kp) {
        mousePressedX = mpx;
        mouseMovedX = mmx;
        mouseButton = mb;
        keyPressed = kp;
        mousePressedY = mpy;
        mouseMovedY = mmy;
    }
}
