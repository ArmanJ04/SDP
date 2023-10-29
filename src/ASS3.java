interface Lighting {
    void connectLightning();
}

class LightingDevice implements Lighting {
    @Override
    public void connectLightning() {
        System.out.println("Connected via Lightning port");
    }
}

interface MiniJack {
    void connectMiniJack();
}

class MiniJackDevice implements MiniJack {
    @Override
    public void connectMiniJack() {
        System.out.println("Connected via Mini Jack port");
    }
}

class LightingToMiniJackAdapter implements MiniJack {
    private final Lighting lightingDevice;

    public LightingToMiniJackAdapter(Lighting lightingDevice) {
        this.lightingDevice = lightingDevice;
    }

    @Override
    public void connectMiniJack() {
        System.out.print("Using Adapter: ");
        lightingDevice.connectLightning();
        System.out.println("Converted to Mini Jack");
    }
}

public class ASS3 {
    public static void main(String[] args) {
        LightingDevice lightingDevice = new LightingDevice();
        MiniJackDevice miniJackDevice = new MiniJackDevice();

        System.out.println("Connecting a Lighting device directly:");
        lightingDevice.connectLightning();

        System.out.println("\nConnecting a Mini Jack device directly:");
        miniJackDevice.connectMiniJack();

        // Using the adapter to connect a Lighting device to a Mini Jack port
        LightingToMiniJackAdapter adapter = new LightingToMiniJackAdapter(lightingDevice);
        System.out.println("\nConnecting a Lighting device using the adapter:");
        adapter.connectMiniJack();
    }
}
