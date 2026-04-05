package Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Аналог ExecuteService
public class ThreadPool {
    private final Queue<Runnable> taskQ = new LinkedList<>();

    public ThreadPool(int threadcount,List<Thread> t){//list для сохранения
        // всех трэдов и их остановки
        for(int i=0;i<threadcount;i++){
            Thread thr = new Thread(new ExecutorRunTask(),"PoolWorker-" + i);
            t.add(thr);
            thr.start();
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