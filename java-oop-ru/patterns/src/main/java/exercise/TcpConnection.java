package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private final String ipAddress;
    private final int portAddress;
    private Connection connectionState;

    public TcpConnection(String ipAddress, int portAddress) {
        this.ipAddress = ipAddress;
        this.portAddress = portAddress;
        this.connectionState = new Disconnected(this);
    }

    public String getCurrentState() {
        return this.connectionState.getCurrentState();
    }

    public void setConnectionState(Connection connectionState) {
        this.connectionState = connectionState;
    }

    public void connect() {
        this.connectionState.connect();
    }

    public void disconnect() {
        this.connectionState.disconnect();
    }

    public void write(String data) {
        this.connectionState.write(data);
    }
}
// END
