class HelloThread extends Thread {

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello thread!");
        }
    }
}

public class Main {
    public static void main(String args[]) {
        Thread myThread = new HelloThread();
        myThread.start();
        
    }
}