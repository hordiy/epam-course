package ua.nure.hordiienko.practice5;

class NonSynchronizedThread extends AbstractClass {
    
	private int fCounter;
    private int sCounter;

    public void aOperation() {
        t();
    }
    
    @Override
    protected void incrFirstCounter() {
        fCounter++;
    }
    
    @Override
    protected void incrSecondCounter() {
        sCounter++;
    }

    @Override
    protected Number getFirstCounter() {
        return fCounter;
    }

    @Override
    protected Number getSecondCounter() {
        return sCounter;
    }
}