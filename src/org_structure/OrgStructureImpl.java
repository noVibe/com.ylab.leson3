package org_structure;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OrgStructureImpl implements OrgStructureParser {

    Map<Long, Employee> employees = new HashMap<>();
    @Override
    public Employee parseStructure(File csvFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fieldCollector = line.split(";");
                Employee employee = new Employee();
                employee.setId(Long.parseLong(fieldCollector[0]));
                employee.setBossId(fieldCollector[1].isBlank() ? null : Long.parseLong(fieldCollector[1]));
                employee.setName(fieldCollector[2]);
                employee.setPosition(fieldCollector[3]);
                employees.put(employee.getId(), employee);
            }
        }
        Employee boss = null;
        for (Employee employee : employees.values()) {
            if (employee.getBossId() == null) {
                boss = employee;
                continue;
            }
            employee.setBoss(employees.get(employee.getBossId()));
            employee.getBoss().getSubordinate().add(employee);
        }
        if (boss == null) {
            throw new RuntimeException("No boss found!");
        }
        return boss;
    }

}
