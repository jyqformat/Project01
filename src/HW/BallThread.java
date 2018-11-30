package HW;

public class BallThread extends Thread{
    private Ball b ;
    public BallThread(Ball b2){
        this.b = b2;
    }
    public void run(){
        while(true){
            b.move();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
