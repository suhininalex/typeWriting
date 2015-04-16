package typeinput;

    public class Chronometer{
        private long lastUsedTime = 0;

        public synchronized long getDeltaTime(){
            long lastTime = lastUsedTime;
            lastUsedTime = System.currentTimeMillis();
            if (lastTime==0) return 0;
            else return lastUsedTime-lastTime;
        }
    }
