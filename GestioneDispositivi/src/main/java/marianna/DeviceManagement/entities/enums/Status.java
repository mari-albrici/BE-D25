package marianna.DeviceManagement.entities.enums;

public enum Status {
    AVAILABLE("Available"),
    ASSIGNED("Assigned"),
    MAINTENANCE("Maintenance"),
    DISMISSED("Dismissed");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
