package demonstrations;

import org_structure.Employee;
import org_structure.OrgStructureImpl;
import org_structure.OrgStructureParser;

import java.io.IOException;
import java.nio.file.Paths;

public class OrgStructureTest {
    public static void main(String[] args) {
        try {
            OrgStructureParser org = new OrgStructureImpl();
            Employee empl = org.parseStructure(Paths.get("src/org_structure/src_file.csv").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
