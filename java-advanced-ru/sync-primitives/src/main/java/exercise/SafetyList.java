package exercise;

class SafetyList {
    // BEGIN
    private int[] array;
    private int size;

    public SafetyList() {
        this.array = new int[10];
        this.size = 0;
    }

    public synchronized void add(int item) {
        if (size >= array.length) {
            int newLength = (array.length * 3) / 2 + 1;
            int[] newArray = new int[newLength];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = item;
        size++;

    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return size;
    }

    // END
}
