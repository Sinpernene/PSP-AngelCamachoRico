package exercice3;

import exercice2.Exercice2Runnable;

public record ThreadWithRunnable(Thread thread, Runnable runnable) {
    public String getThreadName() {
        if (runnable instanceof Exercice2Runnable) {
            return ((Exercice2Runnable) runnable).getThreadName();
        }
        return null;
    }
}