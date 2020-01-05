package fragment.read.pattern.proxy.one;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class GumballMachineTestDrive {
    public static void main(String[] args) {
        try {
            GumballMachine gumballMachine = new GumballMachine("test1", 50);
            LocateRegistry.createRegistry(8080);
            Naming.rebind("//127.0.0.1:8080/gumball/machine/test1", gumballMachine);
            System.out.println("service bind already");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
