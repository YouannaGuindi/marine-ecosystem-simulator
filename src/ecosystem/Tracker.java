import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tracker {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMarine Ecosystem Simulator");
            System.out.println("1. Display Marine Data");
            System.out.println("2. Add New Marine Data");
            System.out.println("3. Remove Marine Data");
            System.out.println("4. Threat Check"); // obj wa7ed wel file beyt3adel kol shahr mn el users 
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = s.nextInt();
            s.nextLine(); /* input new line */

            switch (choice) {
                case 1:
                    displayMarineData();
                    break;
                case 2:
                    addNewMarineData(s);
                    break;
                case 3:
                    removeMarineData(s);
                    break;
                case 4:
                    checkThreatsFromConsole(s);
                    break;
                case 5:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        s.close();
    }

    public static void displayMarineData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Marine Organisms.txt"))) {
            String line;
            System.out.println("\nMarine Data:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to open the file.");
        }
    }

    public static void addNewMarineData(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Marine Organisms.txt", true))) {
            System.out.print("Enter new marine data (format: name size type diet reproduction growth_rate habitat ID): ");
            String newData = scanner.nextLine();
            writer.write(newData);
            writer.newLine();
            System.out.println("New marine data added successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to open the file.");
        }
    }

    public static void removeMarineData(Scanner scanner) {
        System.out.print("Enter the data to remove: ");
        String dataToRemove = scanner.nextLine();
        File inputFile = new File("Marine Organisms.txt");
        File removedFile = new File("Removed Organisms.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(removedFile))) {

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase(dataToRemove.trim())) {
                    found = true;
                    continue; // Skip the line to remove it
                }
                writer.write(line);
                writer.newLine();
            }

            if (found) {
                System.out.println("Data removed successfully.");
            } else {
                System.out.println("Data not found.");
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to process the file.");
            return;
        }

        if (!inputFile.delete()) {
            System.out.println("Error: Unable to delete the original file.");
            return;
        }
        if (!removedFile.renameTo(inputFile)) {
            System.out.println("Error: Unable to rename the temporary file.");
        }
    }
    public static void checkThreatsFromConsole(Scanner scanner) {
        try {
            System.out.print("Enter temperature: ");
            double temperatureValue = scanner.nextDouble();
            System.out.print("Enter salinity: ");
            double salinityValue = scanner.nextDouble();
            System.out.print("Enter depth: ");
            double depthValue = scanner.nextDouble();
            scanner.nextLine(); // Consume new line
            System.out.print("Enter ebb/flow condition: ");
            String ebb = scanner.nextLine().trim().toLowerCase();

            // Read scanned organisms
            ArrayList<MarineOrganism> scannedOrganisms = new ArrayList<>();
            System.out.println("Enter scanned organisms (type 'end' to finish):");
            while (true) {
                String organismName = scanner.nextLine().trim().toLowerCase();
                if (organismName.equalsIgnoreCase("end")) break;
                MarineOrganism organism = MarineOrganism.getOrganismByName(organismName, "Marine Organisms.txt");
                if (organism != null) {
                    System.out.println("Found organism: " + organismName);
                    scannedOrganisms.add(organism);
                } else {
                    System.out.println("Warning: Organism not found: " + organismName);
                }
            }

            // Read oil spill value
            System.out.print("Enter oil spill value: ");
            double oilSpillValue = scanner.nextDouble();

            // Read fishing data
            ArrayList<SubMarineFish> fishedOrganisms = new ArrayList<>();
            ArrayList<Double> actualFishing = new ArrayList<>();
            System.out.println("Enter fishing data (format: quantity organism, type 'end' to finish):");
            scanner.nextLine(); // Consume new line
            while (true) {
                String line = scanner.nextLine().trim().toLowerCase();
                if (line.equalsIgnoreCase("end")) break;

                String[] parts = line.split(" ", 2);
                if (parts.length < 2) {
                    System.out.println("Warning: Invalid fishing data format: " + line);
                    continue;
                }

                try {
                    double quantity = Double.parseDouble(parts[0]);
                    MarineOrganism organism = MarineOrganism.getOrganismByName(parts[1], "Marine Organisms.txt");
                    if (organism instanceof SubMarineFish) {
                        fishedOrganisms.add((SubMarineFish) organism);
                        actualFishing.add(quantity);
                    } else {
                        System.out.println("Warning: Invalid or non-fish organism in fishing data: " + parts[1]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing fishing quantity: " + e.getMessage());
                }
            }

            // Environment input
            System.out.print("Enter environment location and ID: ");
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String location = parts[0];
            int envID = Integer.parseInt(parts[1]);
            Environment environment = Environment.getEnvironmentByIDAndLocation(location, envID, "Environment.txt");
            if (environment == null) {
                System.out.println("Warning: Environment not found for location: " + location);
                return;
            }

            
            PotentialThreats2 threat = new PotentialThreats2(
                temperatureValue, salinityValue, depthValue, ebb,
                scannedOrganisms.toArray(new MarineOrganism[0]),
                oilSpillValue, actualFishing.stream().mapToDouble(Double::doubleValue).toArray(),
                fishedOrganisms.toArray(new SubMarineFish[0]), environment
            );

        
            SubNaturalWaterConditions naturalThreat = new SubNaturalWaterConditions(
                temperatureValue, salinityValue, depthValue, ebb,
                scannedOrganisms.toArray(new MarineOrganism[0]),
                oilSpillValue, actualFishing.stream().mapToDouble(Double::doubleValue).toArray(),
                fishedOrganisms.toArray(new SubMarineFish[0]), environment
            );

            SubHumanPollution humanPollution = new SubHumanPollution(
                temperatureValue, salinityValue, depthValue, ebb,
                scannedOrganisms.toArray(new MarineOrganism[0]),
                oilSpillValue, actualFishing.stream().mapToDouble(Double::doubleValue).toArray(),
                fishedOrganisms.toArray(new SubMarineFish[0]), environment
            );

            SubHumanOverfishing humanOverfishing = new SubHumanOverfishing(
                temperatureValue, salinityValue, depthValue, ebb,
                scannedOrganisms.toArray(new MarineOrganism[0]),
                oilSpillValue, actualFishing.stream().mapToDouble(Double::doubleValue).toArray(),
                fishedOrganisms.toArray(new SubMarineFish[0]), environment
            );
            
            
            System.out.println("Evaluating threats...");
            System.out.println(threat.findthreat(humanPollution, humanOverfishing, naturalThreat));

        } catch (Exception e) {
            System.out.println("Error processing entry: " + e.getMessage());
        }
    }
    public static void exitProgram() {
        System.out.println("Exiting the program. Goodbye!");
    }
}
