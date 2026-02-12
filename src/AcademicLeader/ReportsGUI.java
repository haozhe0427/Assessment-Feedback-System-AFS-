package AcademicLeader;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ReportsGUI extends JFrame {

    public ReportsGUI() {
        setTitle("Analyzed Reports");
        setSize(800, 700); // Taller to fit two charts like the sample image
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use a ScrollPane in case the window is too small
        JScrollPane scrollPane = new JScrollPane(new ReportPanel());
        add(scrollPane);
    }

    // --- INNER CLASS: The Panel that draws the charts ---
    private class ReportPanel extends JPanel {

        // Data for the charts
        private Map<String, Integer> lecturerData;
        private Map<String, Integer> locationData;

        // Colors for slices (Gold, Blue, Cyan, Green, Dark Blue, Orange, Red)
        private final Color[] colors = {
                new Color(220, 180, 50),  // Gold/Yellow
                new Color(50, 120, 220),  // Blue
                new Color(40, 180, 180),  // Teal/Cyan
                new Color(100, 200, 100), // Green
                new Color(30, 60, 120),   // Dark Blue
                new Color(220, 100, 50),  // Orange
                new Color(200, 50, 50)    // Red
        };

        public ReportPanel() {
            setPreferredSize(new Dimension(750, 800)); // Ensure scrolling works
            calculateData();
        }

        private void calculateData() {
            List<TextFileUtils.Module> modules = TextFileUtils.getAllModules();
            lecturerData = new HashMap<>();
            locationData = new HashMap<>();

            for (TextFileUtils.Module m : modules) {
                // 1. Count by Lecturer
                String lec = m.lec.trim();
                if (lec.equalsIgnoreCase("NULL") || lec.isEmpty()) lec = "Unassigned";
                lecturerData.put(lec, lecturerData.getOrDefault(lec, 0) + 1);

                // 2. Count by Location Block (First letter: A, B, E)
                String loc = m.loc.trim();
                String block = "Unknown";
                if (!loc.equalsIgnoreCase("NULL") && !loc.isEmpty()) {
                    // Extract just the block (e.g. "A" from "A-1-4")
                    if (loc.contains("-")) {
                        block = "Block " + loc.split("-")[0];
                    } else {
                        block = loc;
                    }
                }
                locationData.put(block, locationData.getOrDefault(block, 0) + 1);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // --- CHART 1: Modules by Lecturer (Top) ---
            drawPieChart(g2, "Modules Assigned by Lecturer", lecturerData, 50);

            // --- CHART 2: Modules by Location (Bottom) ---
            drawPieChart(g2, "Modules by Location (Block)", locationData, 400);
        }

        private void drawPieChart(Graphics2D g, String title, Map<String, Integer> data, int yPosition) {
            // 1. Draw Title
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            int titleWidth = fm.stringWidth(title);
            g.drawString(title, (getWidth() - titleWidth) / 2, yPosition + 30);

            // Calculate Total
            int total = 0;
            for (int count : data.values()) total += count;

            // Chart Coordinates
            int chartX = 150;
            int chartY = yPosition + 60;
            int diameter = 250;

            // Legend Coordinates
            int legendX = 450;
            int legendY = yPosition + 80;

            // 2. Draw Slices
            int startAngle = 90;
            int colorIndex = 0;

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String label = entry.getKey();
                int value = entry.getValue();

                // Calculate Angle size
                int arcAngle = (int) Math.round((double) value / total * 360);

                // Fix for tiny rounding errors leaving gaps
                // If it's the last slice, fill remaining space
                // (Simplified here: just standard draw)

                g.setColor(colors[colorIndex % colors.length]);
                g.fillArc(chartX, chartY, diameter, diameter, startAngle, arcAngle);

                // Draw White Donut Hole (to match your sample image style)
                // If you want a full pie, remove this part later
                // But sample has a hole in the middle.

                // Draw Legend for this slice
                g.fillRect(legendX, legendY, 15, 15); // Color box
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.PLAIN, 12));

                // Calculate percentage
                int percent = (int) Math.round((double) value / total * 100);
                String legendText = label + " (" + percent + "%)";
                g.drawString(legendText, legendX + 25, legendY + 12);

                // Update positions for next slice
                startAngle += arcAngle;
                legendY += 30; // Move legend down
                colorIndex++;
            }

            // 3. Draw "Donut" Hole (White circle in center)
            g.setColor(Color.WHITE);
            int holeSize = diameter / 3;
            int holeX = chartX + (diameter - holeSize) / 2;
            int holeY = chartY + (diameter - holeSize) / 2;
            g.fillOval(holeX, holeY, holeSize, holeSize);
        }
    }
}
