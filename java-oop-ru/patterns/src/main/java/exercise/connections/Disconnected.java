package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        TcpConnection tcp = this.tcpConnection;
        tcp.setConnectionState(new Connected(tcp));
        System.out.println("OK, Connection is connected");
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection has already been disconnected");
    }

    @Override
    public void write(String data) {
        System.out.printf("Error! The %s couldn't be sent, the connection is disconnected", data);
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }
}
// END
