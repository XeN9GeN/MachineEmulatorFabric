package Utils;

import java.util.LinkedList;
import java.util.Queue;

//Аналог ExecuteService
public class ThreadPool {
    private final Queue<Runnable> taskQ = new LinkedList<>();

    public ThreadPool(int threadcount){
        for(int i=0;i<threadcount;i++){
            new Thread(new ExecutorRunTask(),"PoolWorker-" + i).start();
        }
    }


    public void submit(Runnable task){
        synchronized (taskQ){
            taskQ.add(task);
            taskQ.notify();
        }
    }

    //объект, постоянно выполняющий Runnable tasks
    private class ExecutorRunTask implements Runnable{
        @Override
        public void run(){
            while(true){

                Runnable t;
                //взять задачу с очереди
                synchronized (taskQ){
                    while (taskQ.isEmpty()){
                        try{
                            taskQ.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    t=taskQ.poll();
                }

                if(t!=null){
                    try {
                        t.run();//метод Runnable// inf
                    } catch (RuntimeException e) {
                        return;
                    }
                }
            }
        }
    }


}