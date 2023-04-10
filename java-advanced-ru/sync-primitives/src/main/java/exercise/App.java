package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList list = new SafetyList();

        ListThread listThread1 = new ListThread(list);
        ListThread listThread2 = new ListThread(list);

        listThread1.start();
        listThread2.start();

        try {

            listThread1.join();
            listThread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SIZE: " + list.getSize());
        // END
    }
}

