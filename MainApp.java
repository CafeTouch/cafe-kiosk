import controller.AppController;

public class MainApp {
    public static void main(String[] args) {
        // 앱 첫 화면 실행, 나머지 진행은 컨트롤러에서
        new AppController().newScreen();
    }
}
