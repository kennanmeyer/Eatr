package eatr;

public class runEnv extends Thread{

	    protected boolean isRunning;
	    protected boolean isFinished;
	    protected int epoch =0;
	    Environment env;

	    protected DrawPanel dPanel;

	    public runEnv(DrawPanel tsPanel,Environment env) {
	    	this.env = env;
	        this.dPanel = tsPanel;
	        this.isRunning = true;
	        this.isFinished = false;
	    }

	    @Override
	    public void run() {
	        while (isRunning) {
				env.update();
//				render.draw(env);
				epoch++;
				if(epoch % 10000 == 0) {
					System.out.printf("%d: MaxGen: %d\n",epoch,env.getMax_generation());
					env.saveOrganisms();
				}
	        }
	        this.isFinished = true;
	    }

	    public void stopRunning() {
	        this.isRunning = false;
	        while (!isFinished) {
	            try {
	                Thread.sleep(10L);
	            } catch (InterruptedException e) {
	            }
	        }
	    }

}
