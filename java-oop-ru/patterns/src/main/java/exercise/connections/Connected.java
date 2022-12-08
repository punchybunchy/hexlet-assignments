package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection has already been connected");
    }

    @Override
    public void disconnect() {
        TcpConnection tcp = this.tcpConnection;
        tcp.setConnectionState(new Disconnected(tcp));
        System.out.println("OK, The connection is disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println(data + "sent");
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }
}
// END
