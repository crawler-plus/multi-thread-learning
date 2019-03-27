package demo66;

/**
 * 自定义除法任务类
 */
public class DivTask implements Runnable {

    int a, b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double real = a / b;
        System.out.println(real);
    }
}
