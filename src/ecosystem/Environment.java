import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Environment {

    private int envID;
    private String location;
    private double temp;
    private double depth;
    private double salinity;

    public Environment(int envID, String location, double temp, double depth, double salinity) {
        super();
        this.envID = envID;
        this.location = location;
        this.temp = temp;
        this.depth = depth;
        this.salinity = salinity;
    }

    public int getEnvID() {
        return envID;
    }

    public void setEnvID(int envID) {
        this.envID = envID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getSalinity() {
        return salinity;
    }

    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }

    public String toString() {
        return "Environment [envID=" + envID + ", location=" + location + ", temp=" + temp + ", depth=" + depth
                + ", salinity=" + salinity + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Environment)) {
            return false;
        }
        Environment e = (Environment) obj;

        return e.depth == this.depth && e.temp == this.temp && e.salinity == this.salinity;
    }

    
    public static Environment getEnvironmentByIDAndLocation(String location, int ID, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove BOM or other non-printable characters
                line = line.replaceAll("^[\\uFEFF\\u200B]+", ""); // Remove BOM and zero-width space if present
                line = line.trim(); // Trim any leading/trailing whitespace
                
                //System.out.println("Reading line: '" + line + "'"); // Debugging line
                
                String[] parts = line.split(" ");
                if (parts.length >= 5) {
                    String fileLocation = parts[0].trim(); // Trim whitespace
                    int fileID;
                    try {
                        fileID = Integer.parseInt(parts[1].trim()); // Trim whitespace
                    } catch (NumberFormatException e) {
                        continue; 
                    }
                    // Compare in a case-insensitive manner
                    if (fileLocation.equalsIgnoreCase(location) && fileID == ID) {
                        try {
                            double temp = Double.parseDouble(parts[2].trim()); // Trim whitespace
                            double depth = Double.parseDouble(parts[3].trim()); // Trim whitespace
                            double salinity = Double.parseDouble(parts[4].trim()); // Trim whitespace
                            return new Environment(fileID, fileLocation, temp, depth, salinity);
                        } catch (NumberFormatException e) {
                            continue; 
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Environment file: " + e.getMessage());
        }
        return null; 
    }
}
